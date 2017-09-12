[TOC]

# 标识符

## 可用的字符
- 处理括号类字符,分隔符之外,其他所有的可打印的ASCII字符,如字母,数字,下划线和美元符号($)均可出现在Scala标识符中
- 插入符包括了(,) [,] {,and}
- 分隔符包括 \` ' " . ; ,等
- Scala还允许在标识符中使用编码在 \u0020到\u007F之间的字符,如数学符号,想 / 和 < 这样的操作符字符以及其他的一些符号

**不能使用保留字.**
## 普通标识符
- 常见的标识符往往由字母或下划线开头,后面跟着一些字母,数字,下划线和$符
- Scala允许使用Unicode格式的字符
- $符在Scala内部会作为特定作用,尽量不要使用
- Scala编译器会将下划线之后,空格之前的所有字符视为标识符的一部分
- 假如在下划线后输入了操作符,那么不允许在操作符后输入字母或数字
- 假如某一标识符以操作符开头,那么后面的字符也必须是操作符字符

### 示例
```scala
val xyz_++= = 1
```
## 使用反引号定义标识符
可以通过反引号定义标识符,两个反引号内的任意长度的字符串便是定义的标识符.
```scala
def `test that addition work`= assert(1 + 1 = 2)
```

# 无参数方法

-   对于不包含参数的方法,除了可以选择使用中缀调用或后缀调用方式之外,Scala还允许用户灵活决定是否使用括号
-   定义无参方法时省略了括号,那么在调用这些方法时必须省略括号
-   定义无参方法时添加了空括号,那么调用该方法时可以选择省略或保留括号

### 几个等价表达式

```scala
List(1, 2, 3, 4).filter((i: Int) => isEven(i)).foreach((i: Int) => println(i))
List(1, 2, 3, 4).filter(i => isEven(i)).foreach(i => println(i))
List(1, 2, 3, 4).filter(isEven).foreach(println)
List(1, 2, 3, 4) filter isEven foreach println
```

# 优先级规则

```scala
1. 所有字母
2. |
3. ^
4. &
5. < >
6. = !
7. :
8. + -
9. * / %
10. 其他特殊字符
```

-   所有操作符都是方法
-   并不是所有方法都是左结合
-   任何名字以冒号(:)结尾的方法均与其右边的对象绑定

## cons操作

-   cons是constructor的缩写
-   cons操作指通过`::`方法将某一元素放置到列表前面

### 示例

```scala
scala> val list = List('c', 'd')
list: List[Char] = List(c, d)

scala> 'a' :: list
res1: List[Char] = List(a, c, d)

scala> val list = List('c', 'd')
list: List[Char] = List(c, d)

scala> 'b' :: list
res2: List[Char] = List(b, c, d)

scala> list.::('a')
res3: List[Char] = List(a, c, d)

scala> 'a' :: list
res4: List[Char] = List(a, c, d)

scala> 'b' :: list
res5: List[Char] = List(b, c, d)
```

**注意:**

-   `'a' :: list` 等价于 `list.::('a')`


-   不能使用该方法重复插入多个字符

# if语句

-   Scala的if语句可以向Java中一样使用

    ```scala
    if (2 + 2 = 5) {
    	println("if")
    } else if (2 + 2 = 3) {
    	println("else if")
    } else {
    	println("else")
    }
    ```

-   Scala中的if语句是有返回值的. 可以将if表达式的结果值赋值给其他变量. 以下面代码为例.

    ```scala
    val configFile = new java.io.File("someFile.txt")

    val configFilePath = if (configFile.exists()) {
    	configFile.getAbsolutePath()
    } else {
        configFile.createNewFile()
    	configFile.getAbsolutePath()
    }
    ```

    if语句返回值的类型也被称为所有条件分支的最小上界类型,也就是与每条each子句可能返回值类型最接近的父类型.在上面例子中,`configFilePath` 是if表达式的结果值,该if表达式将执行文件不存在的条件分支(假定),并返回新创建文件的绝对路径.将if语句的返回值赋予变量`configFilePath` .

**Scala不支持三元表达式**

# 循环

## for推导式

### for循环

#### 示例

##### 代码

```scala
val list = List("A", "B", "C")
  
for (item <- list)
    println(item)
```

##### 输出

```scala
A
B
C
```

### 生成器表达式

-   像`item <- list`这样的表达式被称为生成器表达式
-   该表达式会基于集合生成单独的数值
-   左箭头操作符(<-)用于对象列表这样的集合进行遍历

### 保护式: 筛选元素

-   for语句中可以加入if表达式来筛选元素,这些表达式被称为保护式(guard)

#### 示例

##### 代码

```scala
val list = List("ABC", "CDE", "EFG")

for (item <- list
    if item.contains("C")
    )
  println(item)
```

##### 输出

```scala
ABC
CDE
```

#### 添加多个保护式

##### 示例

```scala
val list = List("ABC", "BCD", "CDE")
  
for (item <- list
   if item.contains("B")
   if !item.startsWith("A")
    )
  println(item)
 
for (item <- list
   if item.contains("B") && !item.startsWith("A")
    )
  println(item)
```

##### 输出

```scala
BCD
BCD
```

-   分别为两个for循环的输出

### Yielding

-   使用`yielding`关键字能在for表达式中生成新的集合

#### 示例

##### 代码

```scala
val list = List("ABC", "BCD", "CDE")
val filterItems =  for {item <- list
	if item.contains("B")
 	if !item.startsWith("A")
	} yield item
filterItems
```

##### 运行

```scala
scala> val list = List("ABC", "BCD", "CDE")
list: List[String] = List(ABC, BCD, CDE)

scala> val filterItems =  for {item <- list
     | if item.contains("B")
     |  if !item.startsWith("A")
     | } yield item
filterItems: List[String] = List(BCD)
```

### 扩展作用域与值定义

-   能够在for表达式中的最初部分定义值,并可在以后的表达式中使用该值

#### 示例

##### 代码

```scala
val list = List("abc", "bcd", "cde")
  
val upcaseItems = for {
	item <- list
    upcaseItem = item.toUpperCase()
} yield upcaseItem
```

##### 运行

```scala
scala> val list = List("abc", "bcd", "cde")
list: List[String] = List(abc, bcd, cde)

scala>
     | val upcaseItems = for {
     | item <- list
     |     upcaseItem = item.toUpperCase()
     | } yield upcaseItem
upcaseItems: List[String] = List(ABC, BCD, CDE)
```

## while循环

### 示例

```scala
while (表达式) {
	do something
}
```

## do...while循环

### 示例

```scala
var count = 0
do {
	count += 1
} while(count < 10)
```

# 条件操作符

| 操作符  | 操作    |
| ---- | ----- |
| &&   | 和操作   |
| \|\| | 或操作   |
| >    | 大于    |
| >=   | 大于或等于 |
| <    | 小于    |
| <=   | 小于或等于 |
| ==   | 等于    |
| !=   | 不等于   |

-   `&&` 和 `||` 是短路操作符

-   在Java中`==` 只会对对象引用进行比较, 调用`equals`来比较字段值; Scala使用`==` 符执行逻辑上的相等检查. 即调用`equals`方法进行判断.

    ```java
    public class Person {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public boolean equals(Object obj) {
            return true;
        }

        public static void main(String args[]) {
            Person p1 = new Person("sun", 20);
            Person p2 = new Person("li", 20);
            System.out.println(p1 == p2);
        }
    }
    // 输出:
    false
    ```

    ```scala
    class Person(var name: String, var age: Int) {
        override def equals(obj: scala.Any): Boolean = true
    }

    val p1 = new Person("sun", 20)
    val p2 = new Person("sun", 20)

    println(p1 == p2)

    // 输出:
    true
    ```

    ​

# 使用try...catch...final子句

-   Scala使用模式匹配来捕获异常, 而Java使用单独的catch子句来捕获各个异常
-   使用`case NonFatal(ex) => ...` 子句使用`scala.util.control.NonFatal` 匹配了所有的非致命性异常
-   通过编写`throw new MyBadException(...)` 抛出异常
-   如果自定义异常是一个case类,那么抛出异常时可以省略new关键字

### 实例

```scala
try {
	...
} catch {
	case NonFatal(ex) => println(s"Non fatal exception! $ex")
	case ...
} finally {
	...
}
```

# 惰性赋值

-   惰性赋值是与传名参数相关的技术
-   如果希望以延迟的方式初始化某值, 并且表达式不会被重复计算, 则需要使用惰性赋值
-   常见应用场景
    -   由于表达式执行代价昂贵(比如打开一个数据库连接), 希望能推迟该操作, 直到确实需要表达式结果时才执行它
    -   为了缩短模块启动时间, 可以将当前不需要的某些工作推迟执行
    -   为了确保对象中其他字段的初始化过程能优先执行, 需要将某些字段惰性化

## 示例

``` scala
object ExpensiveResource {
	lazy val resource: Int = init()
	def init(): Int = {
		// 某些代价昂贵的操作
		0
	}
}
```

-   lazy关键字意味着求值过程会被推迟,只有需要时才会执行计算
-   首次使用惰性值时, 初始化代码才会被执行一次, 这种只能执行一次的计算对于可变字段而言没有任何意义.因此lazy关键字不能修饰var变量
-   通过**保护式来实现惰性值

# 枚举

-   Scala通过在标准库中专门定义Enumeration类实现枚举

## 示例一

### 代码

```scala
// file: enum.scala
object Breed extends Enumeration {
    type Breed  = Value
    val doberman    = Value("Doberman Pinscher")
    val yorkie      = Value("Yorkshire Terrier")
    val scottie     = Value("Scottish Terrier")
    val dane        = Value("Great Dane")
    val portie      = Value("Portuguese Water Dog")
}

import Breed._

println("ID\tBreed")
for (breed <- Breed.values) println(s"${breed.id}\t$breed")

println("\nJust Terriers:")
Breed.values filter (_.toString.endsWith("Terrier")) foreach println

def isTerrier(b: Breed) = b.toString.endsWith("Terrier")

println("\nJust Terriers:")
Breed.values filter isTerrier foreach println
```

###  运行

`scala enum.scala`

### 输出

```scala
ID      Breed
0       Doberman Pinscher
1       Yorkshire Terrier
2       Scottish Terrier
3       Great Dane
4       Portuguese Water Dog

Just Terriers:
Yorkshire Terrier
Scottish Terrier

Just Terriers:
Yorkshire Terrier
Scottish Terrier
```

# 可插入字符串

下面是一个简单示例:

```scala
val name = "Buck Trends"
println(s"Hello, $name")
```

## 格式化

下面是几个对可插入字符串进行格式化的示例.

```scala
scala> val gross = 100000F
gross: Float = 100000.0

scala> val net = 64000F
net: Float = 64000.0

scala> val percent = (net / gross) * 100
percent: Float = 64.0

scala> println(f"$$${gross}%.2f vs. $$${net}%.2f or ${percent}%.1f%%")
$100000.00 vs. $64000.00 or 64.0%

scala> val s = "%02d: name = %s".format(5, "Dean Wampler")
s: String = 05: name = Dean Wampler

// 原生插入器
scala> val name = "Dean Wampler"
name: String = Dean Wampler

scala> s"123\n$name\n456"
res1: String =
123
Dean Wampler
456

scala> raw"123\n$name\n456"
res2: String = 123\nDean Wampler\n456
```

-   使用`$$ ` 打印一个`$`
-   使用`%%` 打印一个`%`

