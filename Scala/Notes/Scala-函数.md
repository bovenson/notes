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

## 函数传名调用和传值调用

Scala的解释器在解析函数参数(function arguments)时有两种方式：

- 传值调用（call-by-value）：先计算参数表达式的值，再应用到函数内部
- 传名调用（call-by-name）：将未计算的参数表达式直接应用到函数内部

```scala
// 代码
object FunctionExample {
    def main(args: Array[String]): Unit = {
        delayedByName(time())
        println("\n" + "*" * 30 + "\n")
        delayedByValue(time())
    }

    def time(): Long = {
        println("获取时间")
        System.nanoTime
    }
  
	// 传名调用
    def delayedByName(t: => Long): Long = {
        println("In delayedByName")
        t
    }
  
	// 传值调用
    def delayedByValue(t: Long): Long = {
        print("In delayedByValue")
        t
    }
}

// 输出
In delayedByName
获取时间

******************************

获取时间
In delayedByValue

/* 说明
 delayed 方法中，在变量名和变量类型使用 => 符号来设置传名调用
 */
```



## 调用时指定参数名

```scala
object TestAA {
    def main(args: Array[String]): Unit = {
        def sub(a:Int, b:Int): Int = {
            a - b
        }

        println(sub(b = 4, a = 10))
    }
}

// 输出
6
```



## 可变参数

```scala
object TestAB {
    def main(args: Array[String]): Unit = {
        def varParams(a:Int, ss:String*) = {
            var res = "" + a + "\t"
            for (s <- ss) {
                res += s + "\t"
            }
            res
        }
        println(varParams(1, "hello", "world"))
    }
}

// 输出
1	hello	world	
```



## 默认参数值

```scala
object TestAA {
    def main(args: Array[String]): Unit = {
        def sub(a:Int=20, b:Int): Int = {
            a - b
        }

        println(sub(b = 4, a = 10))
        println(sub(b = 4))
    }
}

// 输出
6
16
```



## 闭包

- 闭包是一个函数
- 返回值依赖于声明在函数外部的一个或多个变量