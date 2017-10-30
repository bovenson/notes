---
title: Shell程序设计笔记杂项
tags: Linux, Shell程序设计, 笔记
---

[TOC]

# 获取脚本所在目录

```shell
#### bash 获取执行的脚本所在的路径
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
```



# 更改PWD为文件所在路径

```shell
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

#### 有问题的版本
# FULLPATH="$PWD/$(dirname $0)"	# 提取路径
# cd "$FULLPATH"					# 更改pwd
```

# 提取文件路径

```shell
bovenson@MBP:~/Git/notes/Linux/Script$ echo $(dirname "/hello/sd.java")
/hello
bovenson@MBP:~/Git/notes/Linux/Script$ echo $(dirname "~/hello/sd.java")
~/hello
```

