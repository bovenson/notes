# Scala-函数

- Scala 有函数和方法，二者在语义上的区别很小。Scala 方法是类的一部分，而函数是一个对象可以赋值给一个变量。换句话来说在类中定义的函数即是方法
- 可以在任何地方定义函数，甚至可以在函数内定义函数（内嵌函数）
- Scala 函数名可以有以下特殊字符：**+, ++, ~, &,-, -- , \, /, :** 等。

## 声明

`def functionName([参数列表]) : [返回值类型]`

## 定义

```scala
def functionName ([参数列表]) : [返回值类型] = {
  // function body
  return [expr]
}
```

**示例**

```scala
// Code
object FunctionExample {

    def main(args: Array[String]): Unit = {
        println(addInt(1, 2))
    }

    def addInt(a:Int, b:Int) : Int = {
        var sum:Int = 0
        sum = a + b
        return sum	// 这里去掉 return 也可以
    }
}

// Output
3
```

