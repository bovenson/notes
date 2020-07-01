[TOC]
# Scala:模式匹配
- Scala的模式匹配可以使用类型,通配符,序列,正则表达式,甚至可以深入提取对象的状态
- 模式匹配可以用在许多代码场景中,最常用于match语句中

## 简单匹配
### 代码示例
```scala
// file: match-boolean.scala
val bools = Seq(true, false)

for (bool <- bools) {
	bool match {
		case true => println("Got heads")
		case false => println("Got tails")
	}
}
```
### 运行及结果
```shell
$ scala match-boolean.scala
Got heads
Got tails
```
- 如果注释掉`case false ...` 编译器会报错,因为case没有匹配所有可能的结果,如果只想匹配true,可以这样:

```scala
for (bool <- bools) {
	val which = if (bool) "heads" else "tails"
    println("Got" + which)
}
```
## 匹配值,变量,类型
### 示例一
#### 代码
```scala
for {
    x <- Seq(1, 2, 2.7, "one", "two", 'four)	// 序列元素包含不同类型,因此序列的类型为Seq[Any]
} {
    val str = x match {
        case 1 => "int 1"						// 匹配 1
        case i: Int => "other int: " + i		// 匹配除1外的整数
        case d: Double => "a double: " + x		// 匹配所有Double类型
        case "one" => "string one"
        case s: String => "other string: " + s
        case unexpected => "unexpected value: " + unexpected // 匹配其他任意输入.由于未给出任何类型说明,unexpected的类型被推断为Any,起到了C语言中default语句的作用
    }
    println(str)
}
```
#### 运行
```scala
$ scala MatchBoolean.scala
int 1
other int: 2
a double: 2.7
string one
other string: two
unexpected value: 'four
```
#### 说明
- 像所有表达式一样,match语句也有返回值.在示例一中,所有的子句都返回字符串,因此整个子句的返回值类型为String.编译器会推断所有case子句返回值类型的最近公共父类型
- 匹配是按顺序进行的,因此默认子句必须是最后一个子句,不然编译器会给出警告
- 注意浮点数的匹配

### 示例二
- 使用反引号表示真正想要匹配的是变量的值
#### 代码
```scala
val y: Int = 10
for {
    v <- Seq(1, 3, 10, 20)
} {
    val res = v match {
        case `y` => "Find 10"
        case other => "Find others"
    }
    println(res)
}val y: Int = 10
for {
    v <- Seq(1, 3, 10, 20)
} {
    val res = v match {
        case `y` => "Find 10"
        case other => "Find others"
    }
    println(res)
}
```
#### 运行及输出
```shell
$ scala MatchBoolean.scala
Find others
Find others
Find 10
Find others
```
### 示例三
- 不同的匹配使用相同的处理代码

#### 代码
```scala
for {
    x <- Seq(1, 2.1, 3, 4.4, "one", 'four)
} {
    val res = x match {
        case _: Int | _: Double => "a number: " + x
        case _: String => "string: " + x
        case _ => "unexpected value: " + x
    }
    println(res)
}
```
#### 运行及输出
```bash
$ scala MatchBoolean.scala 
a number: 1
a number: 2.1
a number: 3
a number: 4.4
string: one
unexpected value: 'four
```
### 小结
- 在case子句中,以小写字母开头的标识符被认为是用来提取待匹配值的新变量
- 如果需要引用之前一定以的变量时,应使用反引号将其包围
- 以大写字母开头的标识符被认为是类型名称
- 如果不使用变量特有方法,可以使用通配符`_`代替变量名及类型声明
- 除了偏函数,所有的match语句都必须是完全覆盖输入的.当输入类型为Any时,在结尾用`case _`或 `case some_name` 作为默认子句

## 序列的匹配

-   Seq是具体的集合类型的父类型,这些集合类型支持以确定顺序遍历其元素,如List和Vector

### 示例代码

```scala
val nonEmptySeq = Seq(1, 2, 3, 4, 5)
val emptySeq = Seq.empty[Int]
val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil     // Scala库的一个专用对象Nil表示任意类型的空list
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val emptyVector = Vector.empty[Int]
val nonEmptyMap = Map("one" -> 1, "tow" -> 2, "three" -> 3)
val emptyMap = Map.empty[String, Int]

def seqToString[T](seq: Seq[T]): String = seq match {
    case head +: tail => s"$head +:" + seqToString(tail)    // 匹配非空seq,提取头部(第一个元素)及尾部(除第一个外外)
    case Nil => "Nil"
}

for (seq <- Seq(nonEmptySeq, emptySeq, nonEmptyList, emptyList, nonEmptyVector, emptyVector,
    nonEmptyMap.toSeq, emptyMap.toSeq)) {
    println(seqToString(seq))
}
```

### 运行输出

```shell
> scala MatchSeq.scala
1 +:2 +:3 +:4 +:5 +:Nil
Nil
1 +:2 +:3 +:4 +:5 +:Nil
Nil
1 +:2 +:3 +:4 +:5 +:Nil
Nil
(one,1) +:(tow,2) +:(three,3) +:Nil
Nil
```

-   我们用表示空List专用的对象Nil来匹配
-   `+:` 操作符是序列的"构造"操作符
-   上面代码.case子句只匹配至少包含一个头部元素的非空序列,它将序列的头部和剩下的部分分别提取可变变量head和tail中
-   Seq可以应用于所有子类型,包括List和Vector

## 元组的匹配

### 代码示例

```scala
val langs = Seq(
    ("scala", "martin", "odersky"),
    ("clojure", "rich", "hickey"),
    ("lisp", "john", "mccarthy")
)

for (tuple <- langs) {
    tuple match {
        case ("scala", _, _) => println("Fond scala")
        case (lang, first, last) => println(s"found other language: $lang ($first, $last)")
    }
}
```

### 运行输出

```shell
> scala MatchTuple.scala
Fond scala
found other language: clojure (rich, hickey)
found other language: lisp (john, mccarthy)
```

## `case`中的`guard`语句

### 示例代码

```scala
for (i <- Seq(1,2,3,4)) {
    i match {
        case _ if i%2 == 0 => println(s"even: $i")  // 使用 guard
        case _ => println(s" odd: $i")
    }
}
```

### 运行

```
> scala MatchGuard.scala
 odd: 1
even: 2
 odd: 3
even: 4
```

## case类的匹配

### 示例代码

```scala
case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston", "USA"))

for (person <- Seq(alice, bob, charlie)) {
    person match {
        case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice!")
        case Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA")) => println("Hi Bob!")
        case Person(name, age, _) => println(s"Who are you, $age year-old person named $name?")
    }
}
```

### 运行及输出

```powershell
c> scala MatchCaseC.scala
Hi Alice!
Hi Bob!
Who are you, 32 year-old person named Charlie?
```

### 使用`@` 

#### 代码示例

```scala
case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston", "USA"))

for (person <- Seq(alice, bob, charlie)) {
    person match {
        case p @ Person("Alice", 25, address) => println(s"Hi Alice! $p")
        case p @ Person("Bob", 29, a @ Address(street, city, country)) =>
            println(s"Hi ${p.name}! age ${p.age}, in ${a.city}")
        case p @ Person(name, age, _) => println(s"Who are you, $age year-old person named $p?")
    }
}
```

#### 运行及输出

```
> scala MatchCaseC.scala
Hi Alice! Person(Alice,25,Address(1 Scala Lane,Chicago,USA))
Hi Bob! age 29, in Miami
Who are you, 32 year-old person named Person(Charlie,32,Address(3 Python Ct.,Boston,USA))?
```

-   p @ ... 语法将整个Person类的实例赋值给了变量p,类似地,a @ ...也将整个Address实例赋值给了变量

## 可变参数列表的匹配

### 代码示例

```scala
object Op extends Enumeration {
    type Op = Value

    val EQ = Value("=")
    val NE = Value("!=")
    val LTGT = Value("<>")
    val LT = Value("<")
    val LE = Value("<=")
    val GT = Value(">")
    val GE = Value(">=")
}

import Op._

case class WhereOp[T](columnName: String, op: Op, values: T)

case class WhereIn[T](columnName: String, val1: T, vals: T*)

val wheres = Seq(
    WhereIn("state", "IL", "CA", "Va"),
    WhereOp("state", EQ, "IL"),
    WhereOp("name", EQ, "Buck Trends"),
    WhereOp("age", GT, 29)
)

for (where <- wheres) {
    where match {
        case WhereIn(col, val1, vals @ _*) =>
            val valStr = (val1 +: vals).mkString(", ")
            println(s"WHERE $col IN ($valStr)")
        case WhereOp(col, op, value) => println(s"WHERE $col $op $value")
        case _ => println(s"ERROR: Unknown expression: $where")
    }
}
```

### 运行及输出

```
> scala MatchVarList.scala
WHERE state IN (IL, CA, Va)
WHERE state = IL
WHERE name = Buck Trends
WHERE age > 29
```

### 说明

-   可变参数的语法形式: `name @ _*` 

## 正则表达式匹配

### 代码示例

```scala
val BookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r
val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r

val catalog = Seq(
    "Book: title=Programming Scala Second Edition, author=Dean Wampler",
    "Magazine: title=The New Yorker, issue=January 2014",
    "Unknown: text=Who put this here?"
)

for (item <- catalog) {
    item match {
        case BookExtractorRE(title, author) =>
            println(s"""Book "$title", written by $author """)
        case MagazineExtractorRE(title, issue) =>
            println(s"""Magazine "title", issue $issue""")
        case entry => println(s"Unrecognized entry: $entry")
    }
}
```

### 运行及输出

```
> scala MatchRegex.scala
Book "Programming Scala Second Edition", written by Dean Wampler
Magazine "title", issue January 2014
Unrecognized entry: Unknown: text=Who put this here?
```

### 在正则表达式中使用变量插值

-   在三重双引号内的正则表达式中使用变量插值是无效的,需要对变量插值进行转义,如`s"""$first\\s+$second""".r`