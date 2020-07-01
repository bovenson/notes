---
title: Python正则表达式
tags:
	- 正则表达式
categories:
	- Python
---

[TOC]

# 简介

-   **正则表达式** 是包含文本和特殊字符的字符串, 为高级的文本模式匹配, 抽取, 与文本形式的搜索和替换功能提供了基础
-   `Python`通过标准库`re`模块来支持正则表达式
-   模式匹配的两种方法完成**匹配(模式匹配)**
    -   搜索(`search()`)
    -   匹配(`match()`)

# 特殊符号和字符

元字符指正则表达式中的特殊符号和字符。

## **符号**

| 符号          | 描述                                                         | 示例                     |
| ------------- | ------------------------------------------------------------ | ------------------------ |
| *`literal`*   | 匹配文本字符串的字面值*literal*                              | `foo`                    |
| `re1\|re2`    | 匹配正则表达式`re1`和`re2`                                   | `foo\|bar`               |
| `.`           | 匹配任何字符(除`\n`之外)                                     | `f.o`                    |
| `^`           | 匹配字符串起始部分                                           | `^foo`                   |
| `$`           | 匹配字符串终止部分                                           | `bar$`                   |
| `*`           | 匹配0次或者多次前面出现的正则表达式                          | `[A-Za-z0-9]*`           |
| `+`           | 匹配1次或者多次前面出现的正则表达式                          | `[a-z]+\\.com`           |
| `?`           | 匹配0次或者1次前面出现的正则表达式                           | `goo?`                   |
| `{N}`         | 匹配N次前面出现的正则表达式                                  | `[0-9]{3}`               |
| `{M,N}`       | 匹配M~N次前面出现的正则表达式                                | `[0-9]{5,9}`             |
| `[…]`         | 匹配来自字符集的任意单一字符                                 | `[aeiou]`                |
| `[x-y]`       | 匹配x~y范围中的任意单一字符                                  | `[0-9], [A-Za-z]`        |
| `[^…]`        | 不匹配此字符集中出现的任何一个字符, 包括某一范围的字符       | `[^aeiou], \[^A-Za-z]`   |
| `(*|+|?|{})?` | 用于匹配上面频繁出现/重复出现符号的非贪婪版本`(*、+、？、{})` | `.*?[a-z]`               |
| `(…)`         | 匹配封闭的正则表达式，然后另存为子组                         | `([0-9]{3})?,f(oo|u)bar` |

## **特殊符号**

| 符号 | 描述                                      | 示例          |
| ---- | ----------------------------------------- | ------------- |
| `\d` | 匹配任何十进制数字，与`[0-9]`一致         | `data\d+.txt` |
| `\D` | 与`\d`相反                                |               |
| `\w` | 匹配任何字母数字字符，与`[A-Za-z0-9]`相同 |               |
| `\W` | 与`\w`相反                                |               |
| `\s` | 匹配任何空格字符，与`[\n\t\r\v\f]`相同    |               |
| `\S` | 与`\s`相反                                |               |
| `\N` | 匹配已保存的子组 N                        | `price:\1`    |
| `\c` | 逐字匹配任何特殊字符`c`                   | `\.,\\,\*`    |
| `\A` | 匹配字符串起始，与`^`相同                 |               |
| `\Z` | 匹配字符串结束，与`$`相同                 |               |

## **扩展符号**

| 符号              | 描述                                                         | 示例              |
| ----------------- | ------------------------------------------------------------ | ----------------- |
| ` (?iLmsux) `     | 在正则表达式本身中嵌入一个或多个特殊**特殊**标记 (vs. via function/method) | `(?x),(?im)`      |
| `(?:...)`         | 匹配一个不用保存的分组                                       | `(?:\w+\.)`       |
| ` (?P<name>...) ` | 使用名字表示的正则分组                                       | `(?P<data>)`      |
| `(?#...)`         | 表示注释，所有内容会被忽略                                   | `(?#comment)`     |
| `(?=...)`         | 匹配条件是如果...出现在之后的位置，而不使用输入字符串；称作正向前视断言(positive lookahead assertion) | `(?=.com)`        |
| `(?!...)`         | 匹配条件是如果...不出现在之后的位置，而不使用输入字符串；称作负向前视断言(negative lookahead assertion) | `(?!.net)`        |
| `(?<=...)`        | 匹配条件是如果...出现在之前的位置，而不使用输入字符串；称作正向后视断言(positive lookbehind assertion) | `(?<=800-)`       |
| `(?<!...)`        | 匹配条件是如果...不出现在之前的位置，而不使用输入字符串；称作负向后视断言(negative lookbehind assertion) | `(?<!192\.168\.)` |
| `(?(id/name)Y|N)` | Conditional match of regex Y if group with given id or name exists else N; \|N is optional | `(?(1)y|x)`       |

## **使用管道符匹配多个正则表达式**

管道符号在正则表达式中又称为择一匹配符，表示 **从多个模式中选择其一** 的操作。

|  正则表达式   | 匹配的字符串  |
| :-----------: | :-----------: |
|   `at|home`   |   `at,home`   |
|  `r2d2|c3po`  |  `r2d2,c3po`  |
| `bat|bet|bit` | `bat,bet,bit` |

## 匹配任意单个字符

句点`(.)`符号匹配除了换行符`\n`以外的任何字符。无论字母、数字、空格(不包括`\n`换行符)、可打印字符、不可打印字符，使用`.`都可以匹配。

| 正则表达式 |                       匹配的字符串                       |
| :--------: | :------------------------------------------------------: |
|   `f.0`    | 匹配在字母`f`和`o`之间的任意一个字符，如:`fao,f9o,f#o`等 |
|    `..`    |                       任意两个字符                       |
|   `.end`   |           匹配在字符串`end`之前的任意一个字符            |

**注意**

要显式匹配一个句点符号本身，必须使用反斜线转义句点符号的功能，例如`\.`。

## 匹配起始或结尾

有些符号和相关的特殊字符用于在字符串的起始或结尾部分指定用于搜索的模式。

|    符号     | 位置 |
| :---------: | :--: |
| `^` 或 `\A` | 起始 |
| `$` 或 `\Z` | 结尾 |

简单示例如下。

|   正则表达式   |         匹配的字符串          |
| :------------: | :---------------------------: |
|    `^Froms`    | 任何以`From`作为开头的字符串  |
|  `/bin/bash$`  | 任何以`/bin/bash`结尾的字符串 |
| `^Subject:hi$` |       匹配`Subject:hi`        |

## 匹配单词边界

| 符号 |          说明          |
| :--: | :--------------------: |
| `\b` |   匹配一个单词的边界   |
| `\B` | 匹配不是一个单词的边界 |

简单示例如下。

| 正则表达式 |             匹配的字符串              |
| :--------: | :-----------------------------------: |
|   `the`    |         任何包含`the`的字符串         |
|  `\bthe`   |        任何以`the`开始的字符串        |
| `\bthe\b`  |           仅仅匹配单词`the`           |
|  `\Bthe`   | 任何包含但并不以`the`作为起始的字符串 |

## 创建字符集

使用`[]`创建字符集，可以匹配某些特定字符。

| 正则表达式 |   匹配的字符串    |
| :--------: | :---------------: |
| `b[aeiu]t` | `bat,bet,bit,but` |
| `[cr][23]` |   `c2,c3,r2,r3`   |

## 限定范围和否定

除了单字符外，字符集还支持匹配指定的字符范围。两个字符中间用连字符`-`连接，用于指定一个字符的范围。如果`^`紧跟在左括号后面，这个符号就表示不匹配给定字符集中任何一个字符。

|     正则表达式     |                匹配的字符串                |
| :----------------: | :----------------------------------------: |
|     `z.[0-9]`      | `z` 后面跟着任何一个字符，然后跟着一个数字 |
| `[r-u][env-y][us]` | 等价于`[rstu][envwxy][us]` ，比如匹配`res` |
|     `[^aeiou]`     |             匹配一个非元音字符             |
|     `[^\t\n]`      |            不匹配制表符或换行符            |

## 使用闭包操作符实现存在性和频数匹配

| 符号 |                             说明                             |
| :--: | :----------------------------------------------------------: |
| `*`  | 匹配左侧的正则表达式出现零次或多次的情形，这称作`Kleene`闭包 |
| `+`  |      匹配一次或多次出现的正则表达式，这称作正闭包操作符      |
| `?`  |           操作符将匹配零次或者一次出现的正则表达式           |
| `{}` | 里面或者是单值，或者是一对由逗号分隔的数值；`{N}`表示匹配`N`次；`{N,M}`表示匹配`N~M`次 |

**如果问号紧跟在任何使用闭包操作符的匹配后面，它将直接要求正则表达式引擎匹配尽可能少的次数。**当模式匹配使用分组操作符时，正则表达式引擎将试图吸收匹配该模式的尽可能多的字符，这通常叫做**贪婪匹配**。问号要求正则表达式引擎在当前正则表达式中尽可能少地匹配字符。

简单示例。

|   正则表达式   |    匹配的字符串     |
| :------------: | :-----------------: |
|    `[dn]ot`    |   `do,dot,no,not`   |
|   `0?[1-9]`    | 可能存在前置0的数字 |
| `[0-9]{15,16}` | 匹配15或者16个数字  |
|  `</?[^>]+>`   |    匹配HTML标签     |

## 表示字符集的特殊字符

有一些特殊字符能够表示字符集。

| 符号 | 描述                              |
| ---- | --------------------------------- |
| `\d` | 匹配任何十进制数字，与`[0-9]`一致         | `data\d+.txt` |
| `\D` | 与`\d`相反                                |               |
| `\w` | 匹配任何字母数字字符，与`[A-Za-z0-9]`相同 |               |
| `\W` | 与`\w`相反                                |               |
| `\s` | 匹配任何空格字符，与`[\n\t\r\v\f]`相同    |               |
| `\S` | 与`\s`相反                                |               |

简单示例。

| 正则表达式 |                      匹配的字符串                      |
| :--------: | :----------------------------------------------------: |
| `\w+-\d+`  | 一个由字母数字组成的字符串和一串由一个连字符分割的数字 |

## 使用圆括号指定分组

有时候不仅想要知道整个字符串是否匹配我们的标准，而且想要知道能否提取任何已经成功匹配的特定字符串或者子字符串，可以使用分组来实现。

一对圆括号可以实现一下任意一个功能：

- 对正则表达式进行分组
- 匹配子组

对正则表达式分组，可以在整个正则表达式中使用重复的操作符。

使用圆括号进行分组的一个副作用就是，匹配模式的子字符串可以保存起来供后续使用。这些子组能够被同一次的匹配或者搜索重复调用，或者提取出来用于后续处理。

匹配子组的重要性在于，很多时候除了进行匹配操作以外，我们还想要提取所匹配的模式。

简单示例如下。

| 正则表达式    | 匹配的字符串           |
| ------------- | ---------------------- |
| `\d+(\.\d*)?` | 表示简单浮点数的字符串 |

## 扩展表示法

正则表达式的扩展表示法，以问号开始`(?...)`。他们通常用于在判断匹配之前提供标记，实现一个前视或者后视匹配，或者条件检查。

尽管这些符号使用圆括号，但是只有`(?P<name>)` 表示一个分组匹配，其他的都没有创建一个分组。

|    正则表达式     | 匹配的字符串                                                 |
| :---------------: | :----------------------------------------------------------- |
|   `(?:\w+\.)*`    | 以句点作为结尾的字符串，例如 `google.`，但是这些匹配不会保存下来供后续使用和数据检索 |
|   `(?#comment)`   | 注释                                                         |
|    `(?=.com)`     | 如果一个字符串和后面跟着 `.com` 才做匹配操作，并不使用任何目标字符串 |
|    `(?!.net)`     | 如果一个字符串后面不是跟着`.net`才做匹配操作                 |
|    `(?<=800-)`    | 如果字符串之前为`800-`才做匹配                               |
| `(?<!192\.168\.)` | 如果一个字符串之前不是`192.168.`才做匹配操作                 |
|    `(?(1)y|x)`    | 如果一个匹配组1(`\1`)存在，就与`y`匹配；否则与`x`匹配        |

# Python中的正则表达式

在Python中，`re`模块支持更强大而且更通用的Perl风格的正则表达式，该模块允许多个线程共享同一个已编译的正则表达式对象，也支持命名子组。

## re模块

### re模块函数

| 函数                        | 描述                                                         |
| --------------------------- | ------------------------------------------------------------ |
| `compile(pattern, flags=0)` | 使用任何可选的标记来编译正则表达式的模式，然后返回一个正则表达式对象 |

### re模块函数和正则表达式对象的方法

| 函数                                  | 描述                                                         | 返回值                                     |
| ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------ |
| `match(pattern, string, flags=0)`     | 使用带有可选标记的正则表达式模式匹配字符串                   | 匹配成功，返回匹配对象；如果失败，返回None |
| `search(pattern, string, flags=0)`    | 使用可选标记搜索字符串中第一次出现的正则表达式模式           | 匹配成功，返回匹配对象；如果失败，返回None |
| `findall(pattern, string[, flags])`   | 查找字符串中所有(非重复)出现的正则表达式模式                 | 匹配列表                                   |
| `finditer(pattern, string[, flags])`  | 与`findall`相同，但返回的不是列表                            | 一个迭代器                                 |
| `split(pattern, string,max=0 )`       | 根据正则表达式的模式分隔符，split函数将字符串分割为列表，然后返回成功匹配的列表，分割最多操作`max`次，默认分割所有匹配成功的位置 | 分割后的列表                               |
| `sub(pattern, repl, string, count=0)` | 使用`repl`替换count次正则表达式的模式在字符串中出现的位置；默认替换所有 | 替换操作数目                               |
| `purge()`                             | 清除隐式编译的正则表达式模式；清除缓存                       |                                            |

### 常用的匹配对象方法		

| 函数                      | 描述                                                         |
| ------------------------- | ------------------------------------------------------------ |
| `group(num=0)`            | 返回整个匹配对象；或者编号为num的特定子组                    |
| `groups(default=None)`    | 返回一个包含所有匹配子组的元组(如果没有成功匹配，则返回一个空元组) |
| `groupdict(default=None)` | 返回一个包含所有匹配的命名子组的字典，所有的子组名称作为字典的键(如果没有匹配成功返回一个空元组) |

### 常用的模块属性

| 属性                 | 描述                                                         |
| -------------------- | ------------------------------------------------------------ |
| `re.I,re.IGNORECASE` | 不区分大小写的匹配                                           |
| `re.L,re.LOCALE`     | 根据所使用的本地语言环境通过`\w,\W,\b,\B,\s,\S`实现匹配      |
| `re.M,re.MULTILINE`  | `^`和`$`分别匹配目标字符串中行的起始和结尾，而不是严格匹配整个字符串本身的起始和结尾 |
| `re.S,re.DOTALL`     | `.` 通常匹配除了`\n` 之外的所有单个字符，该标记可以使`.`匹配换行符 |
| `re.X, re.VERBOSE`   | 通过反斜线转义，否则所有空格加上`#`都被忽略                  |

## 使用compile函数编译正则表达式

- 在Python中可以通过两种途径使用正则表达式：
  - re模块函数
  - 调用编译后的正则表达式对象方法

- 在Python中由两类对象和正则表达式有关：

  - `re.compile`生成的表达式对象
  - 匹配对象（成功调用 `match()` 或 `search()` 之后返回的对象）

- 几乎所有的re模块函数都可以作为regex对象的方法。

- 可以通过按位或操作符`(|)`合并使用多个标记

- 可以使用`(?FLAG)`将标记嵌入到正则表达式

  ```shell
  >>> re.match(r'(?i)the', 'the')
  <_sre.SRE_Match object; span=(0, 3), match='the'>
  >>> re.match(r'(?i)the', 'The')
  <_sre.SRE_Match object; span=(0, 3), match='The'>
  >>> re.match(r'the', 'The')
  >>>
  ```

## 匹配对象以及`group()`和`groups()`方法

匹配对象是成功调用`match()`或者`search()`返回的对象。匹配对象有两个主要的方法: `group()`和`groups()`。

`group()`要么返回整个匹配对象，要么根据要求返回特定子组。`groups()`则仅返回一个包含唯一或者全部子组的元组。如果没有子组的要求，那么当`group()`仍返回整个匹配时，`groups()`返回一个空元组。

Python正则表达式允许命名匹配。

## 使用match方法匹配字符串

`match`方法试图从字符串的起始部分对模式进行匹配。

如果匹配成功，就返回一个匹配对象；如果匹配对象失败，就返回None。

匹配对象的`group()`方法能够用于显示那个成功的匹配。

```shell
Python 3.5.4rc1 (default, Jul 25 2017, 08:53:34) 
[GCC 6.4.0 20170704] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import re
>>> m = re.match('foo', 'foo')
>>> if m is not None:
...     m.group()
... 
'foo'
>>> m
<_sre.SRE_Match object; span=(0, 3), match='foo'>
```

只要模式从字符串的起始部分匹配，即使字符串比模式长，匹配也仍然能够成功。匹配结果是从较长字符串中**抽取的匹配部分**。

```shell
>>> re.match('foo', 'food on the table').group()
'foo'
```

## 使用search在字符串中查找模式

`search`函数在任意位置对给定正则表达式模式搜索第一次出现的匹配情况。如果搜索到成功的匹配，就会返回一个匹配对象；否则，返回None。

与`match`相比，`match`只能从起始开始匹配，`search`可以匹配任意位置。

```shell
>>> m = re.match('foo', 'seafood')
>>> m.group() if m is not None else print(m)
None
>>> m = re.search('foo', 'seafood')
>>> m.group() if m is not None else print(m)
'foo'
```

上面代码，使用`match`匹配失败，使用`search`则匹配成功。

## 匹配多个字符串

管道符号或择一匹配符号`(|)`的使用。

```shell
>>> bt = 'bat|bet|bit'
>>> m = re.match(bt, 'bat')
>>> m.group() if m is not None else print(m)
'bat'
>>> m = re.match(bt, 'blt')		   		# 不能匹配
>>> m.group() if m is not None else print(m)
None
>>> m = re.match(bt, 'He bit me')  		# 不能匹配
>>> m.group() if m is not None else print(m)
None
>>> m = re.search(bt, 'He bit me')  	# 可以搜索到
>>> m.group() if m is not None else print(m)
'bit'
```

## 匹配任何单个字符

点号`.`不能匹配**换行符**和空**字符串**。

```shell
>>> dote = '.end'
>>> m = re.match(dote, 'bend')  # OK
>>> m.group() if m is not None else print(m)
'bend'
>>> m = re.match(dote, 'end')   # 不能匹配空内容
>>> m.group() if m is not None else print(m)
None
>>> m = re.match(dote, '\nend') # 不能匹配换行符
>>> m.group() if m is not None else print(m)
None
>>> m = re.search(dote, 'The end.')  # 搜索匹配
>>> m.group() if m is not None else print(m)
' end'
```

使用转义符，匹配点号。

```shell
>>> pi = '3.14'
>>> pit = '3\.14'
>>> m = re.match(pit, '3.14')   # 精确匹配
>>> m.group() if m is not None else print(m)
'3.14'
>>> m = re.match(pi, '3014')    # 点号匹配0
>>> m.group() if m is not None else print(m)
'3014'
>>> m = re.match(pi, '3.14')    # 点号匹配.
>>> m.group() if m is not None else print(m)
'3.14'
```

## 创建字符集

```shell
>>> selc = '[cr][23][dp][o2]'
>>> m = re.match(selc, 'c3po')
>>> m.group() if m is not None else print(m)
'c3po'
>>> m = re.match(selc, 'c3p1')
>>> m.group() if m is not None else print(m)
None
```

## 重复、特殊字符以及分组

正则表达式中最常见的情况包括：

- 特殊字符的使用
- 正则表达式模式的重复出现
- 使用圆括号对匹配模式的各部分进行分组和提取操作

**特殊字符与重复出现**

匹配`0~1`个中间子域名

```shell
>>> import re
>>> patt = '\w+@(\w+\.)?\w+\.com'
>>> m = re.match(patt, 'nobody@xxx.com')			# 匹配0个中间子域名
>>> m.group() if m is not None else print(m)
'nobody@xxx.com'
>>> m = re.match(patt, 'nobody@xxx.yyy.com')		# 匹配1个中间子域名
>>> m.group() if m is not None else print(m)
'nobody@xxx.yyy.com'
>>> m = re.match(patt, 'nobody@xxx.yyy.zzz.com')	# 不能匹配2个中间子域名
>>> m.group() if m is not None else print(m)
None
```

匹配任意多个子域名

```shell
>>> patt = '\w+@(\w+\.)*\w+\.com'					# 将 ? 替换为 *
>>> m = re.match(patt, 'nobody@xxx.yyy.zzz.com')	# 匹配2个中间子域名
>>> m.group() if m is not None else print(m)
'nobody@xxx.yyy.zzz.com'
```

**分组**

使用圆括号来匹配和保存子组，以便于后续处理。

使用`group()`和`groups()`方法获取分组，其两者区别：

- `group()`
  - 访问每个独立的子组
  - 获取完整匹配(不传递参数)
- `groups()`
  - 获取一个包含所有匹配子组的元组

```shell
>>> m = re.match('(\w\w\w)-(\d\d\d)', 'abc-123')
>>> m.group()                   # 完整匹配
'abc-123'
>>> m.group(1)                  # 子组1
'abc'
>>> m.group(2)                  # 子组2
'123'
>>> m.groups()                  # 全部子组
('abc', '123')
```

一个完整示例

```shell
>>> m = re.match('ab', 'ab')            # 没有分组
>>> m.group()                           # 完整匹配
'ab'
>>> m = re.match('ab', 'ab')            # 没有分组
>>> m.group()                           # 完整匹配
'ab'
>>> m.groups()                          # 所有子组
()
>>>
>>> m = re.match('(ab)', 'ab')          # 一个子组
>>> m.group()                           # 完整匹配
'ab'
>>> m.group(1)                          # 子组1
'ab'
>>> m.groups()                          # 全部子组
('ab',)
>>>
>>> m = re.match('(a)(b)', 'ab')        # 两个子组
>>> m.group()
'ab'
>>> m.group(1)                          # 子组1
'a'
>>> m.group(2)                          # 子组2
'b'
>>> m.groups()                          # 全部子组
('a', 'b')
>>>
>>> m = re.match('(a(b))', 'ab')        # 两个嵌套子组
>>> m.group()                           # 完整匹配
'ab'
>>> m.group(1)                          # 子组1
'ab'
>>> m.group(2)                          # 子组2
'b'
>>> m.groups()                          # 全部子组
('ab', 'b')
```

## 匹配字符串的起始和结尾以及单词边界

```shell
>>> m = re.search('^The', 'The end.')   # 匹配
>>> m.group() if m is not None else print(m)
'The'
>>> m = re.search('^The', 'end. The')   # 不做为开始
>>> m.group() if m is not None else print(m)
None
>>> m = re.search(r'\bthe', 'bite the dog')     # 匹配左侧边界
>>> m.group() if m is not None else print(m)
'the'
>>> m = re.search(r'\bthe', 'bitethe dog')      # 匹配左侧边界
>>> m.group() if m is not None else print(m)
None
>>> m = re.search(r'\Bthe', 'bitethe dog')      # 匹配左侧没有边界
>>> m.group() if m is not None else print(m)
'the'
>>> m = re.search(r'\Bthe\B', 'bitethe dog')    # 匹配两侧没有边界
>>> m.group() if m is not None else print(m)
None
>>> m = re.search(r'\Bthe\b', 'bitethe dog')    # 匹配左侧没有边界，右侧有边界
>>> m.group() if m is not None else print(m)
'the'
```

## 使用findall和finditer查找每一次出现的位置

**`findall()`**

- 查询字符串中某个正则表达式模式全部的非重复出现情况
- 与`match()`和`search()`的区别是，`findall()`总是返回一个列表

`finditer()`与`findall()`类似，不过返回结果是一个迭代器。

```shell
>>> re.findall('car', 'car')
['car']
>>> re.findall('car', 'carry')
['car']
>>> re.findall('car', 'carry the barcardi to the car')
['car', 'car', 'car']
```

**结合分组使用**

```shell
>>> s = 'This and that.'
>>> re.findall(r'(th\w+) and (th\w+)', s, re.I)
[('This', 'that')]
>>> list(re.finditer(r'(th\w+) and (th\w+)', s, re.I))[0].groups()
('This', 'that')
>>> list(re.finditer(r'(th\w+) and (th\w+)', s, re.I))[0].group(1)
'This'
>>> list(re.finditer(r'(th\w+) and (th\w+)', s, re.I))[0].group(2)
'that'
```

单个分组的多重匹配

- 如果模式中只有一个分组，则匹配结果作为结果集合的单个元素
- 如果模式中由多个分组，则匹配结果为元组，作为结果集的单个元素

```shell
>>> s = 'this and that'
>>> re.findall(r'(th\w+) and (th\w+)', s, re.I)
[('this', 'that')]
>>> re.findall(r'(th\w+)', s, re.I)
['this', 'that']
>>>
>>>
>>> s = 'This and that. What, where, when, and who'
>>> re.findall(r'th\w+|wh\w+', s, re.I)
['This', 'that', 'What', 'where', 'when', 'who']
>>> re.findall(r'(th\w+)|(wh\w+)', s, re.I)
[('This', ''), ('that', ''), ('', 'What'), ('', 'where'), ('', 'when'), ('', 'who')]
>>> re.findall(r'(wh\w+)', s, re.I)
['What', 'where', 'when', 'who']
>>>
>>>
>>> s = 'This where. That when. There who.'
>>> re.findall(r'(th\w+)\s(wh\w+)', s, re.I)
[('This', 'where'), ('That', 'when'), ('There', 'who')]
```

## 使用sub和subn搜索和替换

有两个函数用于实现搜索和替换功能: `sub()` 和 `subn()`。`sub()`返回一个替换后的字符串；`subn()`还返回一个表示替换的总数，替换后的字符串和替换总数作为元组返回。

```shell
>>> re.sub('X', 'Mr. Smith', 'attn: X\n\nDear X,\n')
'attn: Mr. Smith\n\nDear Mr. Smith,\n'
>>> re.subn('X', 'Mr. Smith', 'attn: X\n\nDear X,\n')
('attn: Mr. Smith\n\nDear Mr. Smith,\n', 2)
>>> re.sub('[ae]', 'X', 'abcdef')
'XbcdXf'
>>> re.subn('[ae]', 'X', 'abcdef')
('XbcdXf', 2)
```

使用匹配对象的`group()`方法除了能够取出匹配分组编号外，还可以使用`\N`，其中`N`是在替换字符串中使用的分组编号。

```shell
>>> re.sub(r'(\d{1,2})/(\d{1,2})/(\d{2}|\d{4})', r'\2/\1/\3', '2/20/1992')
'20/2/1992'
>>> re.sub(r'(\d{1,2})/(\d{1,2})/(\d{2}|\d{4})', r'\2/\1/\3', '2/20/92')
'20/2/92'
```

## 在限定模式上使用`split`分隔字符串

`split` 基于正则表达式的模式分隔字符串。可以通过为`max`参数设定一个值（非零）来指定最大分割数。

```shell
>>> import re
>>> DATA = ()
>>> DATA = (
...     'Mountain View, CA 94040',
...     'Sunnyvale, CA',
...     'Los Altos, 94023',
...     'Cupertino 95014',
...     'Palo Alto CA'
... )
>>> for datum in DATA:
...     print(re.split(', |(?= (?:\d{5}|[A-Z]{2})) ', datum))
...
['Mountain View', 'CA', '94040']
['Sunnyvale', 'CA']
['Los Altos', '94023']
['Cupertino', '95014']
['Palo Alto', 'CA']
```

## 扩展符号

通过使用`(?iLmsux)`系列选项，用户可以直接在正则表达式里面指定一个或者多个标记。

**`re.I/re.IGNORECASE, re.M/MULTILINE`**

```shell
>>> re.findall(r'(?i)yes', 'yes? Yes. YES!~')		# 忽略大小写
['yes', 'Yes', 'YES']
>>> re.findall(r'(?i)th\w+', 'The quickest way is through this tunnel.')	# 忽略大小写
['The', 'through', 'this']
>>> re.findall(r'(?im)(^th[\w ]+)', """				# 忽略大小写；多行
... This line is the first,
... another line,
... that line, it's the best.
... """)
['This line is the first', 'that line']
```

**`re.S/re.DOTALL`**

使用`re.S,re.DOTALL`标记，使得点号`.`能够用来表示换行符。

```shell
>>> re.findall(r'th.+', '''
... The first line
... the second line
... the third line
... ''')
['the second line', 'the third line']
>>> re.findall(r'(?s)th.+', '''
... The first line
... the second line
... the third line
... ''')
['the second line\nthe third line\n']
```

**`re.X/re.VERBOSE`**

`re.X/re.VERBOSE`标记允许用户通过抑制在正则表达式中使用空白符（除了在字符类中或者在反斜线转义中）来创建更易读的正则表达式。

```shell
>>> re.search(r'''(?x)
...     \((\d{3})\)     # 匹配区号
...     [ ]             # 匹配空格
...     (\d{3})         # 匹配前缀
...     -               # 横线
...     (\d{4})         # 终点数字
... ''', '(800) 555-1212').groups()
('800', '555', '1212')
```

**`(?:...)`**

通过使用`(?:...)`符号，可以对部分正则表达式进行分组，但是并不会保存该分组用于后续的检索或应用。

```shell
>>> re.findall(r'http://(?:\w+\.)*(\w+\.com)',
...             'http://goole.com http://www.google.com http://code.google.com')
['goole.com', 'google.com', 'google.com']
>>> re.search(r'\((?P<areacode>\d{3})\) (?P<prefix>\d{3})-(?:\d{4})',
...             '(800) 555-1212').groupdict()
{'areacode': '800', 'prefix': '555'}
```

结合`\g`，使用`(?P<name>)` 和 `(?P=name)`符号。

```shell
>>> re.sub(r'\((?P<areacode>\d{3})\) (?P<prefix>\d{3})-(?:\d{4})',
...     '(\g<areacode>) \g<prefix>-xxxx', '(800) 555-1212')
'(800) 555-xxxx'
```

**检索元组**

- 对于没有命名的元组，使用`\N`，其中`N`为数字
- 对于命名的元组，可以使用`\g<name>`，也可以使用`\N`

```shell
>>> m = re.search(r'(\d{4}) (?P<prefix>\d{4}) (\d{3})', '0530 8023 123')
>>> m.group()
'0530 8023 123'
>>> m.group(1)
'0530'
>>> m.group(2)
'8023'
>>> m.group(3)
'123'
>>> m.groupdict()
{'prefix': '8023'}
>>>
>>> re.sub(r'(\d{4}) (\d{4}) (\d{3})', r'\3 \2 \1', '0530 8023 123')
'123 8023 0530'
>>> re.sub(r'(\d{4}) (?P<prefix>\d{4}) (\d{3})', r'\3 \2 \1 - \g<prefix>', '0530 8023 123')
'123 8023 0530 - 8023'
```

在一个相同的正则表达式中**重用模式**。

```shell
>>> re.match(r'(?P<num>\d{2})(?P=num)', '1212')			# 只能匹配相同的内容
>>> print(m) if m is None else m.group()
'1212'
>>> m = re.match(r'(?P<num>\d{2})(?P=num)', '1234')		# 不能匹配不同的内容
>>> print(m) if m is None else m.group()
None
```

```shell
>>> bool(re.match(r'\((?P<areacode>\d{3})\) (?P<prefix>\d{3})-(?P<number>\d{4}) (?P=areacode)-(?P=prefix)-(?P=number)',
...     '(800) 555-1212 800-555-1212'))
True
>>> bool(re.match(r'''(?x)
...     # match (800) 555-1212, save areacode, prefix, no.
...     \((?P<areacode>\d{3})\)[ ](?P<prefix>\d{3})-(?P<number>\d{4})
... 
...     # space
...     [ ]
... 
...     # match 800-555-1212
...     (?P=areacode)-(?P=prefix)-(?P=number)
... 
...     # space
...     [ ]
... 
...     # match 18005551212
...     1(?P=areacode)(?P=prefix)(?P=number)
... ''', '(800) 555-1212 800-555-1212 18005551212'))
True
```

**前视匹配**

可以使用`(?=...)` 和 `(?!...)` 符号在目标字符串中实现一个前视匹配，而不必实际使用这些字符串。

- `(?=...)` : 正向前视断言
- `(?!...)` : 负向前视断言

```shell
>>> re.findall(r'\w+(?= van Rossum)',	# 正向前视断言
... '''
...     Guido van Rossum
...     Tim Peters
...     Alex Martelli
...     Just van Rossum
...     Raymond Hettinger
... ''')
['Guido', 'Just']
>>> re.findall(r'(?m)^\s+(?!noreply|postmaster)(\w+)',	# 负向前视断言
... '''
...     sales@phptr.com
...     postmaster@phptr.com
...     eng@phptr.com
...     noreply@phptr.com
...     admin@phptr.com
... ''')
['sales', 'eng', 'admin']
>>> ['%s@aw.com' % e.group(1) for e in \
... re.finditer(r'(?m)^\s+(?!noreply|postmaster)(\w+)',
... '''
...     sales@phptr.com
...     postmaster@phptr.com
...     eng@phptr.com
...     noreply@phptr.com
...     admin@phptr.com
... ''')]
['sales@aw.com', 'eng@aw.com', 'admin@aw.com']
```

**条件正则表达式匹配 **

`(?(id/name)yes-pattern|no-pattern)`

> Will try to match with `yes-pattern` if the group with given *id* or *name* exists, and with `no-pattern` if it doesn’t. `no-pattern` is optional and can be omitted. For example, `(<)?(\w+@\w+(?:\.\w+)+)(?(1)>|$)` is a poor email matching pattern, which will match with `'<user@host.com>'` as well as `'user@host.com'`, but not with `'<user@host.com'` nor `'user@host.com>'`.

```shell
>>> r = r'(<)?(\w+@\w+(?:\.\w+)+)(?(1)>|$)'
>>> m = re.match(r, '<user@host.com>')
>>> print(m) if m is None else m.group()
'<user@host.com>'
>>> m = re.match(r, 'user@host.com')
>>> print(m) if m is None else m.group()
'user@host.com'
>>> m = re.match(r, '<user@host.com')
>>> print(m) if m is None else m.group()
None
>>> m = re.match(r, 'user@host.com>')
>>> print(m) if m is None else m.group()
```

## 贪婪搜索

加入由一系列类似如下格式的字符串

```
Thu Feb 15 17:32:12 2007::szhkai@qq.com::1123242-3-5
```

我们所感兴趣的是，数据记录内包含由连字符连接的三个整数的整行数据

```shell
>>> s = '''
Thu Feb 15 17:41:42 2007::szhkai@qq.com::1123242-3
Sun Jul 22 13:32:25 2007::szhkai@qq.com::1123242-5
The May 12 17:02:52 2007::szhkai@qq.com::1123242-3-5
Thu Apr 18 12:22:42 2007::szhkai@qq.com::12323-3-5
'''
>>> re.findall(r'(?m).+\d+-\d+-\d+', s)
['\tThe May 12 17:02:52 2007::szhkai@qq.com::1123242-3-5', '\tThu Apr 18 12:22:42 2007::szhkai@qq.com::12323-3-5']
```

如果我们对`\d+-\d+-\d+`这一部分感兴趣，可以使用元组提取

```shell
>>> re.findall(r'(?m).+(\d+-\d+-\d+)', s)
['2-3-5', '3-3-5']
```

但是不能提取第一个整数。这是因为正则表达式在实现上是采用贪婪匹配，试图匹配该模式尽可能多的字符。可以使用非贪婪操作符`?`解决这个问题。可以在`*, +, ?`后使用`?`。该操作符要求正则表达式引擎匹配尽可能少的字符。在`.+`后放置一个`?`可以获得期望的结果。

```shell
>>> re.findall(r'(?m).+?(\d+-\d+-\d+)', s)
['1123242-3-5', '12323-3-5']
```

# 注意事项

**ASCII码冲突**

如果符号同时使用于ASCII码和正则表达式特殊符号，就会出现问题，如`\b`表示ASCII字符的退格符，但是`\b`同时也是一个正则表达式的特殊符号，表示匹配一个单词的边界。对于正则表达式编译器而言，若将`\b`视为正则表达式特殊字符，需要使用`\`进行转义。

```shell
>>> m = re.match('\bblow', 'blow')      # 退格键; 没有匹配
>>> print(m) if m is None else m.group()
None
>>> m = re.match('\\bblow', 'blow')     # 匹配单词边界
>>> print(m) if m is None else m.group()
'blow'
>>> m = re.match(r'\bblow', 'blow')     # 使用 raw string
>>> print(m) if m is None else m.group()
'blow'
```

**\w和\W字母数字字符集同时受`re.L/LOCALE`和`Unicode(re.U/UNICODE)`标记影响。**

# 参考

- 《Python 核心编程》
- [Python文档](https://docs.python.org/3/library/re.html)

# 说明

## Python版本

```shell
# 对于Python2
bovenson@ThinkCentre:~$ python2
Python 2.7.13+ (default, Jul 19 2017, 18:15:03) 
[GCC 6.4.0 20170704] on linux2
Type "help", "copyright", "credits" or "license" for more information.

# 对于Python3
bovenson@ThinkCentre:~$ python3
Python 3.5.4rc1 (default, Jul 25 2017, 08:53:34) 
[GCC 6.4.0 20170704] on linux
Type "help", "copyright", "credits" or "license" for more information.
```
