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
# IF...ELSE

```scala
if (BOOL_EXQ_1) {
  // CODE SEGMENT
} else if (BOOL_EXQ_2) {
  // CODE SEGMENT
} else {
  // CODE SEGMENT
}
```

# 循环

## 语法

```scala
// 1. while
while(BOOL_EXP) {
  // do something
}

// 2. do...while
do {
  // do something
} while (BOOL_EXP)

//3. for
//// 循环 Range
for (x <- Range) {	// 左箭头 <- 用于为变量 x 赋值
  // do something
}

//// 循环 List
for (x <- List) {
  // do something
}
```

**实例**

```scala
// do...while Example
var i :Int = 0
do {
  i += 1
} while (i < 10)

// for Example
//// 循环 Range
////// to
for (j <- 1 to 3) { // to; 不用声明j
  println(i)
}

////// until
for (j <- 1 until 3) { // to; 不用声明j
  println(i)
}

////// 使用占位符(_)
for (_ <- 1 until 3) { // to; 不用声明j
  println(i)
}

//// 循环 List
for (x <- List(1, 3, 4 ,5 ,6)) {
  println(x)
}
```

## 多重循环

```scala
for (i <- 1 to 2; j <- 3 to 4; k <- List("Hi", "Hello")) {
  println("i:" + i + "\tj:" + j + "\tmessage:" + k)
}

// 输出
i:1	j:3	message:Hi
i:1	j:3	message:Hello
i:1	j:4	message:Hi
i:1	j:4	message:Hello
i:2	j:3	message:Hi
i:2	j:3	message:Hello
i:2	j:4	message:Hi
i:2	j:4	message:Hello
```

## 循环过滤

```scala
// 循环过滤
for (x <- List if BOOL_EXP_1; if BOOL_EXP_2) {
  // do something
}

// Example 1
for (i <- 1 to 10 if i % 2 == 0) {
  println(i)
}
// Example 1 Output
2
4
6
8
10

// Example 2
for (i <- 1 to 10 if i % 2 == 0 && i != 4) {
  println(i)
}
// Example 2 Output
2
6
8
10

// Example 3
for (i <- 1 to 10 if i % 2 == 0; if i >= 6) {
  println(i)
}
// Example 3 Output
6
8
10

// Example 4
for (i <- 1 to 10; j <- 1 to 10; k <- List("Hi", "Hello") if i%2==0; if i>4; if i<10; if j<4 && j%2==1) {
  println("i:" + i + "\tj:" + j + "\tmessage:" + k)
}
// Example 4 Output
i:6	j:1	message:Hi
i:6	j:1	message:Hello
i:6	j:3	message:Hi
i:6	j:3	message:Hello
i:8	j:1	message:Hi
i:8	j:1	message:Hello
i:8	j:3	message:Hi
i:8	j:3	message:Hello
```

## 使用yield

```scala
// Example
//// 定义
val forYield = for { x <- 1 to 10 if x % 2 == 0 } yield x	

//// 使用
println(forYield)
println("length:" + forYield.length)
for (a <- forYield) {
  println(a)
}

//// 输出
Vector(2, 4, 6, 8, 10)
length:5
2
4
6
8
10
```

