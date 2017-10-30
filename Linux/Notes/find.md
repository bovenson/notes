---
title: find命令
tags: Linux, find命令
---

[TOC]

# 格式

`find path -option [ -print ] [ -exec -ok command ] {} \;`

# 参数

| 参数       | 说明                                       | 示例   |
| -------- | ---------------------------------------- | ---- |
| `-print` | 将匹配的文件输出到标准输出                            |      |
| `-exec`  | 对匹配的文件执行该参数所给出的shell命令。相应命令的形式为'command' { } \; 注意{ }和\；之间的空格 |      |

# exec参数

`-exec`参数后面跟的是command命令 / 脚本文件, 该命令必须以`;`或是`+`结束, 通常需要键入`\;`或`';'`来防止分号被shell解释掉; 此命令以`;`结尾和以`+`结尾是有区别的:

- 以`+`结尾: 尽可能少的调用命令, 通常只调用一次
- 以`;`结尾: 对每个文件, 会调用命令一次

```shell
#### 示例
bovenson@MBP:~/Tmp$ cat script/echoargs 
#!/bin/bash
echo $1 - $2 - $3
bovenson@MBP:~/Tmp$ tree tfile/
tfile/
├── a
├── b
└── c

0 directories, 3 files
bovenson@MBP:~/Tmp$ find tfile -exec ./script/echoargs {} \;
tfile - -
tfile/a - -
tfile/c - -
tfile/b - -
bovenson@MBP:~/Tmp$ find tfile -exec ./script/echoargs {} +
tfile - tfile/a - tfile/c
```

## 如果出现没有权限错误

- 检查脚本文件是否有可执行权限

# 示例

```shell
### 1
# 列出当前目录以及子目录下所有扩展名为“.scala”的文件
bovenson@ThinkCentre:~/Git/notes$ find . -name "*.scala"
./Scala/Project/LearningScala/src/main/scala/FunctionExample.scala
./Scala/Project/LearningScala/src/main/scala/test/TestTwo.scala
./Scala/Project/LearningScala/src/main/scala/test/TestAB.scala
./Scala/Project/LearningScala/src/main/scala/test/TestThree.scala
./Scala/Project/LearningScala/src/main/scala/test/TestOne.scala
./Scala/Project/LearningScala/src/main/scala/test/TestAA.scala
./Scala/Project/LearningScala/src/main/scala/HelloWorld.scala
```

