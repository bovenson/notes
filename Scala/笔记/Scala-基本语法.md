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



# 参考

- [菜鸟教程](http://www.runoob.com/scala/scala-basic-syntax.html)