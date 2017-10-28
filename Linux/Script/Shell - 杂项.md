---
title: Shell程序设计笔记杂项
tags: Linux, Shell程序设计, 笔记
---

[TOC]

# 更改PWD为文件所在路径

```shell
FULLPATH="$PWD/$(dirname $0)"	# 提取路径
cd "$FULLPATH"					# 更改pwd
```

# 提取文件路径

```shell
bovenson@MBP:~/Git/notes/Linux/Script$ echo $(dirname "/hello/sd.java")
/hello
bovenson@MBP:~/Git/notes/Linux/Script$ echo $(dirname "~/hello/sd.java")
~/hello
```

