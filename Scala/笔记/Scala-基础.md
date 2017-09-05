[TOC]
# 编译运行

```shell
$ scalac Test.scala
$ scala Test
```

# 保留字

| 保留字       | 说明                                       |
| --------- | ---------------------------------------- |
| abstract  | 抽象声明                                     |
| case      | match表达式中的case子句;定义一个case类               |
| catch     | 捕捉抛出的异常                                  |
| class     | 声明一个类                                    |
| def       | 定义一个方法                                   |
| do        | 用于do...while循环                           |
| else      | 与if配对的else语句                             |
| extends   | 表示接下来的class或trait是所声明的class或trait的父类型    |
| false     | Boolean的false值                           |
| final     | 用于class或trait,表示不能派生子类型;用于类型成员,则表示派生的class或trait不能覆写它 |
| for       | for循环                                    |
| forSome   | 用在已存在的类型声明中,限制其能够使用的具体类型                 |
| if        | if语句                                     |
| implicit  | 使得方法或变量值可以被用于隐含转换;将方法参数标记为可选的,只要在调用该方法时,作用域内有类型匹配的候选对象,就会使用该对象作为参数 |
| import    | 将一个或多个类型抑或类型的成员导入到当前作用域                  |
| lazy      | 推迟val变量的赋值                               |
| match     | 用于类型匹配语句                                 |
| new       | 创建类的一个实例                                 |
| null      | 尚未被赋值的引用变量的值                             |
| object    | 用于单例声明,单例是只用一个实例的类                       |
| override  | 当原始成员未被声明为final时,用override覆写类型中的一个具体成员   |
| package   | 声明包的作用域                                  |
| private   | 限制某个声明的可见性                               |
| protected | 限制某个声明的可见性                               |
| requires  | 停用,以前用于子类型                               |
| return    | 从函数返回                                    |
| sealed    | 用于父类型,要求所有派生的子类型必须在同一个源文件中声明             |
| super     | 类似this,单表示父类型                            |
| this      | 对象指向自身的引用;辅助构造函数的方法名                     |
| throw     | 抛出异常                                     |
| trait     | 这是一个混入模块,对类的实例添加额外的状态和行为;也可以用于声明而不实现方法.类似java的interface |
| try       | 将可能抛出异常的代码块包围起来                          |
| true      | Boolean的true值                            |
| type      | 声明类型                                     |
| val       | 声明一个"只读"变量                               |
| var       | 声明一个可读可写的变量                              |
| while     | 用于while循环                                |
| with      | 表示所声明的类或实例化的对象包括后面的trait                 |
| yield     | 在for循环中返回元素,这些元素会构成一个序列                  |
| _ (下划线)   | 占位符,使用imort,函数字面量中                       |
| :         | 分隔标识符和类型注解                               |
| =         | 赋值                                       |
| =>        | 在函数字面量中分隔参数列表与函数体                        |
| <-        | 在for循环中的生成表达式                            |
| <:        | 在参数化类型和抽象类型声明中,用于限制允许的类型                 |
| <%        | 在参数化类型和抽象类型的view bound生命中                |
| >:        | 在参数化类型和抽象类型生命中,用于限制允许的类型                 |
| #         | 在类型注入中使用                                 |
| @         | 注解                                       |

**Scala不存在`break`和`continue`关键字**

# 程序入口

`scala`程序从`main()`方法开始处理.

`def main(args: Array[String])`

# 注释

```scala
1.多行多行注释
/*
*/

2.单行注释
//
```

# 换行符

- Scala是面向行的语言
- 语句可以用分号（;）结束或换行符
- 语句末尾的分号通常是可选的

# 分号

- 分号是表达式之间的间隔
- 当一行结束时,Scala就认为表达式结束了,除非它可以判断出该表达式尚未结束

## 实例

```scala
val s = "ABCD"; println(s)
```

# 包

## 定义包

### 一

```scala
package cn.edu.neu
class HelloWorld
```

### 二

```scala
package cn.edu.neu {
  class HelloWorld
}
```

- 这种方式可以在一个文件中定义多个包

# 引用

- 使用`import`引用包
- import语句可以出现在任何地方，而不是只能在文件顶部
- import的效果从开始延伸到语句块的结束

```scala
import java.awt.Color  // 引入Color
import java.awt._  // 引入包内所有成员
def handler(evt: event.ActionEvent) { // java.awt.event.ActionEvent
  ...  // 因为引入了java.awt，所以可以省去前面的部分
}

/**** 使用选取器 ****/
import java.awt.{Color, Font}
import java.util.{HashMap => JavaHashMap}	// 重命名成员
import java.util.{HashMap => _, _} // 隐藏成员: 引入了util包的所有成员，但是HashMap被隐藏了
```


# 变量声明
- Scala允许声明变量是可变的还是不可变的
- 声明不可变变量使用val
- 声明可变变量使用var

```scala
// 声明不可变变量,这里只是array不可再更改,但是数组内容可以更改
val array:Array[String] = new Array(5)

// 可变
var price: Double = 1.1
```

# Range
- 生成从某个起点到某个终点的一个数字序列
- 支持`Int`, `Long`, `Float`, `Double`, `Char`, `BigInt`, `BigDecimal`
- 使用`to`包括区间上限
- 使用`until`不包括区间上限
- 使用`by`设置步长

```scala
scala> 1 to 5
res2: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)

scala> 1 until 5
res3: scala.collection.immutable.Range = Range(1, 2, 3, 4)
scala> 1 to 10 by 3
res0: scala.collection.immutable.Range = Range(1, 4, 7, 10)

scala> 0.1f to 5.3f by 1.0f
res1: scala.collection.immutable.NumericRange[Float] = NumericRange(0.1, 1.1, 2.1, 3.1, 4.1, 5.1)

scala> 'a' to 'g'
res2: scala.collection.immutable.NumericRange.Inclusive[Char] = NumericRange(a, b, c, d, e, f, g)

scala> BigInt(1) to BigInt(5) by 2
res3: scala.collection.immutable.NumericRange[BigInt] = NumericRange(1, 3, 5)
```

# 偏函数
- 不处理所有可能的输入,只处理那些能与至少一个`case`语句匹配的输入
- 在偏函数中,只能使用`case`语句
- 整个函数必须用花括号包围
- 如果偏函数被调用,而函数的输入却与所有语句都不匹配,系统就会抛出一个`MatchError`运行时错误
- 使用`isDefineAt`方法测试特定输入是否与偏函数匹配
- 偏函数链式连接: pf1 orElse pf2 orElse pf3 ... 只有所有偏函数都不匹配,才会抛出`MatchError`

## 示例
### 代码
```scala
// file: pf.scala
var pf1: PartialFunction[Any, String] = { case s: String => "YES"}
var pf2: PartialFunction[Any, String] = { case d: Double => "YES"}

val pf = pf1 orElse pf2

def tryPF(x: Any, f: PartialFunction[Any, String]): String =
    try {f(x).toString} catch {case _: MatchError => "ERROR!"}

def d(x: Any, f: PartialFunction[Any,String]) =
    f.isDefinedAt(x).toString

println("      |   pf1 - String |   pf2 - Double  |   pf - All")
println("x     | def?  | pf1(x) | def?   | pf2(x) | def?  |  pf(x)")
println("+" * 50)
List("str", 3.14, 10) foreach {
    x => printf("%-5s | %-5s | %-6s | %-6s | %-6s | %-5s | %-6s\n", x.toString, d(x, pf1), tryPF(x, pf1),
        d(x, pf2), tryPF(x, pf2), d(x, pf), tryPF(x, pf))
}
```
### 运行
`scala pf.scala`
### 运行结果
```
      |   pf1 - String |   pf2 - Double  |   pf - All
x     | def?  | pf1(x) | def?   | pf2(x) | def?  |  pf(x)
++++++++++++++++++++++++++++++++++++++++++++++++++
str   | true  | YES    | false  | ERROR! | true  | YES   
3.14  | false | ERROR! | true   | YES    | true  | YES   
10    | false | ERROR! | false  | ERROR! | false | ERROR!
```

# 方法声明
## 方法默认值和命名参数列表
- 可以为方法参数提供默认值
- 调用方法时可以只提供部分参数值,可以只传入指定参数

### 示例
#### 代码
```scala
case class Point(x: Double = 0.0, y: Double = 0.0) {
	def shift(deltax: Double = 0.0, deltay: Double = 0.0) = 
    	copy (x + deltax, y + deltay)
}

val p1 = new Point(x = 3.3, y = 4.4)
val p2 = p1.copy(y = 6.6)
```
## 方法具有多个参数列表
### 示例
#### 代码
```scala
abstract class Shape() {
    def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
        f(s"draw(offset = $offset), ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape
```
#### 调用有多个参数列表的`draw`方法
```scala
s.draw(Point(1.0, 2.0))(str => println(s"ShapesDrawingActor: $str"))
// 也可以将圆括号替换为花括号
s.draw(Point(1.0, 2.0)){str => println(s"ShapesDrawingActor: $str")}
// 或者这样
s.draw(Point(1.0, 2.0)){str =>
	println(s"ShapesDrawingActor: $str")
}
// 亦或这样
s.draw(Point(1.0, 2.0)){
	str => println(s"ShapesDrawingActor: $str")
}
```
### 优势
- 代码更清晰
- 在之后的参数列表中进行类型判断
- 可以用最后一个参数列表来推断隐含参数.隐含参数是用`implicit`关键字声明的参数.

## Future简介
- `scala.concurrent.Future`是Scala提供的一个并发工具,其中的API使用隐含参数来减少冗余代码
- 将任务封装在Future中执行时,该任务的执行是异步的

### 示例
#### 代码
并发发出5个任务,并在任务结束时处理任务返回的结果.
```scala
// file: future.scala
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def sleep(millis: Long) = {
    Thread.sleep(millis)
}

def doWork(index: Int) = {
    sleep((math.random * 1000).toLong)
    index
}

(1 to 5) foreach { index =>
    val future = Future(doWork(index))

    future onSuccess {
        case answer: Int => println(s"Success! returned: $answer")
    }
    future onFailure {
        case th: Throwable => println(s"Failure! returned: $th")
    }
}

sleep(3000)     // 等待足够长时间,确保工作进程结束
println("finito!")
```
#### 运行
`scala future.scala`
#### 运行结果
```
Success! returned: 2
Success! returned: 4
Success! returned: 1
Success! returned: 5
Success! returned: 3
finito!
```
#### 说明
- `用foreach`对一个从1到5的`Range`进行迭代,调用了`scala.concurrent.Futrue.apply`, 这是单例对象Future的工厂方法.
- `Future.apply`返回一个新的`Future`对象,然后控制权就交给循环了,该对象将在另一个线程中执行`doWork(index)`
- 用onSuccess注册一个回调函数,当future成功执行完毕后,该回调函数会被执行.这个回调函数是一个偏函数.
- Future API允许我们通过ExecutionContext来配置并发操作的执行.`import scala.concurrent.ExecutionContext.Implicits.global`语句导入了默认的ExecutionContext.示例中调用了3个方法,其中的这些方法的第二个参数列表具有隐含的ExecutionContext参数,由于没有指定,使用了默认的ExecutionContext.
- 使用`implicit`关键字声明变量为implicit.只有被声明为implicit的函数参数才允许调用时不给出实参.

## 嵌套方法的定义和递归
方法的定义可以嵌套.
### 代码示例
#### 代码
```scala
// file: factorial.scala
def factorial(i: Int): Long = {
    def fact(i: Int, accumulator: Int): Long = {
        if (i <= 1)
            accumulator
        else
            fact(i - 1, i * accumulator)
    }

    fact(i, 1)
}

(0 to 5) foreach ( i => println(factorial(i)) )
```
#### 运行
`scala factorial.scala`
#### 输出
```
1
1
2
6
24
120
```

# 字面量

## 整数字面量

-   整数字面量可以以十进制,八进制,十六进制的形式出现

| 类型   | 格式                             | 例子          |
| ---- | ------------------------------ | ----------- |
| 十进制  | 0或非零值,后面跟上0个活多个数字              | 0,1,321     |
| 十六进制 | 0x后面更上一个或多个十六进制数字(0-9,A-F,a-f) | oxFF,0x1a3b |
| 八进制  | 0后面跟上一个或多个八进制数字(0-7)           | 013,077     |

### 整数字面量

| 目标类型  | 下限    | 上限     |
| ----- | ----- | ------ |
| Long  | -2^63 | 2^63-1 |
| Int   | -2^31 | 2^31-1 |
| Short | -2^15 | 2^15-1 |
| Char  | 0     | 2^16   |
| Byte  | -2^7  | 2^7-1  |

-   如果整数字面量的值超出了以上表格中所示的范围,将会引发一个编译错误.
-   字面量类型默认推断为Int

### 浮点数字面量

-   默认推断类型为Double

#### 示例

```scala
.14
3.14
3.14F
3.14D
3E5
3.14e-5
3.14e+5
3.14e-4D
```

### 布尔型字面量

布尔型字面量可以为`true`或`false`

### 字符字面量

-   字符字面量要么是单引号内的一个可打印的Unicode字符,要么是一个转义序列.
-   值在0~255的Unicode字符可以用八进制数字的转义形式表示,即一个反斜杠后面跟上最多三个八进制数字字符

#### 字符常量示例

```scala
'A'
'\u0041'
'\n'
'012'
'\t'
```

#### 字符转义序列

| 转义序列 | 含义        |
| ---- | --------- |
| \b   | 退格(BS)    |
| \t   | 水平制表符(HT) |
| \n   | 换行(LF)    |
| \f   | 表格换行(FF)  |
| \r   | 回车(CR)    |
| \"   | 双引号(")    |
|      | 单引号(')    |
|      | 反斜杠(\)    |

**不可打印的Unicode字符是不允许的,如`\u0009`(水平制表符), 应该使用等价的转义形式`\t`**

### 字符串字面量

-   字符串字面量是被双引号和三重双引号包围的字符串序列

### 符号字面量

-   符号是一些规定的字符串
-   符号字面量是单引号后更上一个或多个数字,字母或下划线,但第一个字符不能是数字
-   符号字面量`'id`是表达式`scala.Symbol("id")`的简写形式
-   如果需要创建包含空格的符号,可以使用`Symbol.apply`, 如`Symbol.apply(" Programming Scala ")`

### 函数字面量

-   `(i: Int, s: String) => s+i` 是一个类型为 `Function2[Int, String, String]` (返回值类型为String)的函数字面量

-   我们可以使用函数字面量来声明变量,下面两种声明是等价的:

    ```scala
    val f1: (Int, String) => String 	= (i, s) => s+i
    val f2: Function2[Int, String, String]	= (i, s) => s+i
    ```

### 元组字面量

-   Scala库中包含TupleN类,如Tuple2, 用于组建N元素组

#### 定义

```scala
val tup = ("strig", 2016)	// 定义了一个Tuple2的实例
val t1: (Int, String) = (1, "one")
val t2: Tuple2[Int, String] = (2, "two")
```

#### 使用

```scala
// File: tuple.scala
val t = ("Hello", 1, 2.3)
println("print the whole tuple:" + t)
println("print the first item:" + t._1)
println("print the second item:" + t._2)
println("print the third item:" + t._3)

val (t1, t2, t3) = ("World", '!', 0x22)
println(t1 + ", " + t2 + ", " + t3)

val (t4, t5, t6) = Tuple3("World", '!', 0x22)
println(t4 + ", " + t5 + ", " + t6)
```

#### 运行

`scala tuple.scala`

#### 输出

```scala
print the whole tuple:(Hello,1,2.3)
print the first item:Hello
print the second item:1
print the third item:2.3
World, !, 34
World, !, 34
```

#### 两元素的元组

两元素的元组有时被简称为pair.其定义方法有多种:

-   `(1, "one")` 
-   `1 -> "one"`
-   `Tuple2(1, "one")`

示例:

```shell
scala> val t = 1 -> "one"
t: (Int, String) = (1,one)

scala> t._1
res0: Int = 1

scala> t._2
res1: String = one
```



# Option, Some,None: 避免使用null

-   `Option`有两个具体的子类,`Some`和`None`.	
-   `Some`表示有值
-   `None`表示没有值

# 封闭类的继承

-   Scala设计了关键字`sealed`,其告诉编译器所有的子类必须在同一个源文件中声明.在Scala库中,Some与None就是与Option声明在同一源文件中,这一技术有效防止了Option类派生其他子类型.
-   如果要防止用户派生任何子类, 可以用`final`关键字声明 

# 用文件和名空间组织代码

-   Scala沿用Java用包来表示命名空间的这一做法,但它更加灵活.
-   文件名不必与类名一致
-   包结构不一定要与目录结构一致
-   可以定义与文件的"物理"位置独立的包结构
-   Scala不允许在脚本中定义包,脚本被隐含包装在一个对象中.在对象中声明包是不允许的
-   不能再类或对象中定义包

# 导入类型及其成员

-   `_`被当做通配符, 因为在Scala中,`*`允许用作函数名

## 示例

Scala中导入Java类型.

```scala
import java.awt._				// 导入包内所有类型
import java.io.File				// 导入包中单独的Scala类型或Java类型
import java.io.File._			// 导入了java.io.File中所有的静态方法和属性.与之等价的Java import语句为:
							  // import static java.io.File.*
import java.util.{Map, HashMap}	// 选择性导入
```

-   导入是相对的




# 参考

- [菜鸟教程](http://www.runoob.com/scala/scala-basic-syntax.html)