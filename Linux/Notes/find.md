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

