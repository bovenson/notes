# Scala: 隐式
- 隐式是Scala的一个强大的特性
- 能够减少代码
- 能够想已有类型中注入新的方法
- 能够创建领域特定语言(DSL)

## 隐式参数
**使用`implicit`关键字标记那些用户无需现实提供的方法参数.调用方法时,如果为输入隐式参数且代码作用域中存在类型兼容值是,类型兼容值会从作用域中调出并被使用;如果用户没有显示给出且作用域内不存在该兼容值,系统会抛出编译器错误.**

### 示例一
定义一个计算销售税的方法,税率被设置为隐式参数
#### 代码
```scala
def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate
```
调用时,系统会把所在局部作用域中的某一隐式值传入此方法.
```scala
// file: calcTax.scala
implicit val currentTaxRate = 0.08F
val tax = calcTax(50000F)
println(tax)
```
运行输出
```shell
scala calctax.scala
4000.0
```
### 示例二
隐式对象本身不具有任何参数,除非参数同样被标示为隐式参数
#### 代码
```scala
def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

object SimpleStateSalesTax {
    implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData (
                                baseRate: Float,
                                isTaxHoliday: Boolean,
                                storeId: Int
                                )

object ComplicatedSalesTax {
    private def extraTaxRateForStore(id: Int): Float = {
        0.0F
    }

    implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float =
        if (cstd.isTaxHoliday) 0.0F
        else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
}
// 在可插入字符串(interpolated string)中调用`calcTax`方法,但该方法仍然会将隐式值应用到rate参数上
{
    import SimpleStateSalesTax.rate

    val amount = 100F
    println(s"Tax on $amount = ${calcTax(amount)}")
}
// 定义一个包含隐式参数的隐式方法,该隐式参数将接受方法所需的数据
{
    import ComplicatedSalesTax.rate	// calcRate(... rate)
    implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)	// implicit def rate(...)

    val amount = 100F
    println(s"Tax on $amount = ${calcTax(amount)}")
}
```
#### 运行及输出
```bash
$ scala CalcTax.scala
Tax on 100.0 = 5.0
Tax on 100.0 = 6.0
```
### 调用implicitly方法
Predef对象定义了一个名为implicity的方法,如果将implicity方法与附加类型签名相结合,便能以一种有用快捷的方式定义一个接受参数化类型隐式参数的函数
#### 示例三
使用implicity方法对List的sortBy方法进行封装
```scala
// file: ImplicitlyArgs.scala
import math.Ordering
case class MyList[A](list: List[A]) {
    def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
        list.sortBy(f)(ord)

    def sortBy2[B: Ordering](f: A => B): List[A] =
        list.sortBy(f)(implicitly[Ordering[B]])
}

val list1 = MyList(List(1, 3, 5, 2, 4))
val list2 = MyList(List(1, 3, 5, 2, 4))

println(list1 sortBy1 (i => -i))
println(list2 sortBy2 (i => -i))
```
#### 运行及输出
```bash
$ scala ImplicitylyArgs.scala
List(5, 4, 3, 2, 1)
List(5, 4, 3, 2, 1)
```
#### 说明
有些集合提供了一些排序方法,List.sortBy便是其中之一.List.sortBy方法的第一个参数类型是函数,该输入函数能够将函数的输入参数转化为另一个满足math.Ordering条件的类型.第另一个参数是隐式参数,该参数知道如何对类型B的实例进行排序.
MyList类提供了两种方式编写像sortBy类型的方法.
- 第一种实现,sortBy1方法应用了我们已知的语法,该方法接受一个额外的类型为Ordering[B]的隐式值作为其输入.调用sortBy1方法时,在当前作用域中一定存在某一Ordering[B]的对象实例,该实例清楚地知道如何对B类型进行排序.我们认为B的界限被"上下文"所限定.
- sortBy2方法是sortBy1方法的简化版.类型参数`B: Ordering`被称为上下文定界.它暗指第二个参数列表(隐式参数列表)将接受Ordering[B]实例.
- 当需要类型为参数化类型的隐式参数时,且类型参数属于当前作用域的其他一些类型时,可以将上下文定界与implicitly方法结合使用,以简洁的方式解决问题.

## 隐式参数使用的场景
### 执行上下文
Future对象的apply方法的第二个参数列表被设为隐式参数,该参数用于将ExecutionContext对象传递给Future.apply方法:
```scala
apply[T](body: => T)(implicit executor: ExecutionContext): Future[T]
```
- 在调用这些方法时并未指定ExecutionContext对象,但是我们导入了可供编译器使用的全局默认值:
	`import scala.concurrent.ExecutionContext.Implicits.global`
- 使用隐式参数传入"执行上下文"是一个值得推荐的用法.另外编写失误,数据库连接,线程池,以及用户会话时隐式参数上下文也同样适合使用.

### 功能控制
除了能够传递上下文对象之外,隐式参数还具有控制系统功能的作用.
#### 示例
实现登录用户和未登录用户展示不同菜单.
```scala
def createMenu(implicit session: Session): Menu = {
	val defaultItems = List(helpItem, searchItem)
    val accountItem =
    	if (session.loggedin()) List(viewAccountItem, editAccountItem)
        else List(loginItem)
    Menu(deafultItems ++ accountItem)
}
```
### 限定可用实例
想要对具有参数化类型方法的类型参数进行限定,使该参数只能接受某些类型的输入.
假如允许输入的所有参数类型均为某一公共超类的子类型,可以使用面向对象技术解决.
#### 示例
```scala
trait Closable {
	def close(): Unit
}
object manage {
	def apply[R <: Closable, T](resource: => R)(f: R => T) = {...}
}
```
上述代码,R必须是实现了Closable的trait.如果这些类型并无公共超类,则不能使用面向对象技术实现,可以使用Scala集合API解决.

#### 应用 Scala API
Scala API 运用一种常见的方法,将一个构建器作为隐式参数传入map方法中,该构建器知道如何构造一个同种类型的新容器.这实际上与定义在`TraversableLike`的map方法的签名很相似.`TraversableLike`是一个trait,被混入到了那些"可遍历(traversable)"的容器类型中.
```scala
trait TravarsableLike[+A, +Repr] extends ... {
	...
	def map[B, That](f: A => B)(
    	implicit bf: CanBuildFrom[Repr, B, That]): That = {...}
}
```
- +A意味着`TraversableLike[A]`类型是类型A的协变(covariant):假如B是A的子类型,那么`TraversableLike[B]`类型也是`TraversableLike[A]`类型的子类型.
- `CanBuildFrom`是我们所使用的构建器,只要存在一个隐式构建器对象,便能构建出任意想要的新容器.