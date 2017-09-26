---
title: Python编码规范 - PEP8
tags: Python, 编码规范, PEP8
---

# 代码编排

- 缩进
  - 每个缩进级别使用4个空格
  - 不适用Tab
- 行
  - 每行最大长度79，换行可以使用反斜杠，最好使用圆括号
  - 换行点要在操作符的后边敲回车
- 间隔
  - 类和top-level函数定义之间空两行
  - 类中的方法定义之间空一行
  - 函数内逻辑无关段落之间空一行
  - 其他地方尽量不要空行
- 续行
  - 续行应该与其包裹元素对齐
  - 要么使用圆括号、方括号和花括号内的隐式行连接来垂直对齐；要么使用悬挂式缩进对齐。使用悬挂式缩进时，应该考虑下面的意见
  - 第一行不应该有参数
  - 使用缩进以便与其他代码区分清楚



# 文档编排

- 模块内容的顺序
  - 模块说明和docstring
  - import ：按标准、三方、自己编写顺序依次排放，之间空一行
  - globals & constants
  - 其他定义
- 不要一句import中多个库，比如不推荐：`import os,sys`
- 如果采用`from xx import yy`引用库，可以省略`xx.`，可能出现命名冲突，这是就要采用`import xx`，使用:`xx.yy`

# 空格的使用

总体原则：避免不必要的空格。

- 各种右括号前不要加空格
- 逗号、冒号、分号前不要加空格
- 函数的左括号前不要加空格
- 序列的左括号前不要加空格
- 操作符左右各加一个空格，不要为了对齐增加空格
- 函数默认参数使用的赋值符左右省略空格
- 不要将多句语句写在同一行，尽量使用`;`
- `if/for/while`语句中，即使执行语句只有一句，也必须另起一行

# 注释

当一段代码发生变化时，需要即使更新注释。

注释使用英文，最好是完整的句子，首字母大写，句后要有结束符，结束符后跟两个空格，开始下一句。短语可以省略结束符。

## 块注释

在一段代码前增加的注释。在`#`后加一个空格。段落之间以只有`#`的行间隔。比如：

```python
# Description: Module config.
# 
# Input: None
#
# Output: None
```

## 行注释

在一句代码后加注释，比如：`x = x + 1	# Increment x`。

但是这种方式尽量少使用。

**避免无谓的注释。**

# 文档描述

- 为所有的共有模块、函数、类、方法写docstrings；非共有的没有必要，但是可以写注释（在`def`的下一行）

- 如果docstring要换行，参考下例，详见PEP 257

  ```python
  """Return a foobang

  Optional plotz says to frobnicate the bizbaz first.

  """
  ```

# 命名规范

总体原则：新编代码必须按下面命名风格进行，现有库的代码尽量保持风格。

- 尽量单独使用小写字母`l`，大写字母`O`等容易混淆的字母
- 模块名：尽量短小，全部小写，可以使用下划线
- 包命名：尽量短小，全部小写，不可以使用下划线
- 类的命名：使用CapWords方式，模块内部使用的类采用`_CapWords`的方式
- 异常命名：使用`CapWords`+`Error`后缀的方式
- 全局变量：尽量只在模块内有效，类似C语言中的static。实现方法有两种，一是`__all__`机；二是前缀一个下划线
- 函数命名：全部小写，可以使用下划线
- 常量命名：全部大写，可以使用下划线
- 类的属性（方法和变量）：
  - 全部使用小写，可以使用下划线
  - 类的属性有三种作用域：public、non-public和subclass API，可以理解为C++中的public、private、protected
  - non-public属性加前缀下划线
  - 类的属性若与关键字名字冲突，后缀一下划线，尽量不要使用缩略等其他方式
- 为避免与子类属性命名冲突，在类的一些属性前，前缀两条下划线。比如：类Foo中声明`__a`，访问时，只能通过`Foo._Foo__a`，避免歧义
- 类的方法第一个参数必须是`self`，而静态方法第一个参数必须是`cls`

# 编码建议

- 编码中考虑到其他python实现的效率等问题，比如运算符`+`在`CPython(python)`中效率很高，但是`Jython`中却非常低，所以应该采用`.join()`的方式

- 尽可能使用`is`、`is not` 取代 `==` ， 比如`if x is not None` 要优先于 `if x`

- 使用基于类的异常，每个模块或包都有自己的异常类，此异常类继承自`Exception`

- 异常中不要使用裸露的`except`， `except`后跟具体的`exceptions`

- 异常中`try`的代码尽可能少

- 使用`isinstance()`比较对象的类型，比如：

  ```python
  if isinstance(obj, int)	# YES
  if type(obj) is type(1)	# NO
  ```

- 判断序列空或不空，有如下规则：

  ```python
  ### YES
  if not seq
  if seq

  ### NO
  if len(seq)
  if not len(seq)
  ```

- 字符串不要以空格收尾

- 二进制数据判断使用`if boolvalue`的方式

# 参考

- [参考一](https://www.douban.com/note/134971609/)
- [参考二](https://www.python.org/dev/peps/pep-0008/#a-foolish-consistency-is-the-hobgoblin-of-little-minds)
- [参考三](http://www.cnblogs.com/ajianbeyourself/p/4377933.html#_label0)

