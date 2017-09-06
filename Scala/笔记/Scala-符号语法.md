# 花括号

- `{}` : 单独使用是一个有值的表达式

```scala
// 代码
object TestTwo {
    def main(args: Array[String]): Unit = {
        val v = {}
        val num = {
            var a = 1
            var b =2
            a + b
        }

        println(v.getClass)
        println(v)

        println(num.getClass)
        println(num)
    }
}

// 输出
void
()
int
3
```

# 大括号和小括号

- [参考一](http://blog.csdn.net/bluishglc/article/details/52946575)

# `=> Unit`和`() => Unit`的区别

- `() => Unit`: 是一个函数
- `=> Unit`: 是一个执行结果为Unit的表达式
- [参考](http://orchome.com/253)

