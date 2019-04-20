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



# `=>`

- [参考](http://orchome.com/401)



# `=> Unit`和`() => Unit`的区别

- `() => Unit`: 是一个函数
- `=> Unit`: 是一个执行结果为Unit的表达式
- [参考](http://orchome.com/253)



# `->`

- [参考一](http://orchome.com/432)
- -> 操作符用来创建对偶作者

```scala
// 代码
package test

object TestThree {
    def main(args: Array[String]): Unit = {
        val v = "Hello" -> 1
        println(v.getClass)
        println(v)
    }
}

// 输出
class scala.Tuple2
(Hello,1)
```



# `_`

- 作为通配符, 类似java中的*
- `:_*` 作为一个整体,告诉编译器你希望将某个参数当作参数序列处理, 例如`val s = sum(1 to 5:_*)`, 将`1 to 5`当做函数列表处理
- 知道一个集合中每个元素.例如我们要在一个Array a中筛出偶数，并乘以2，可以用以下办法：`a.filter(_%2==0).map(2*_)`
- 在元组中, 可以用方法`_1,  _2, _3`访问组员
- 作为占位符