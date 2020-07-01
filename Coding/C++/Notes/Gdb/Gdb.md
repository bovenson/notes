---
title: GDB调试C++程序
tags:
	- GDB
categories:
	- C++
---

# Usage

```shell
gdb [options] [executable-file [core-file or process-id]]
gdb [options] --args executable-file [inferior-arguments ...]
gdb [options] [--python|-P] script-file [script-arguments ...]
```

# Debug

```shell
# reset dir
(gdb) set substitute-path /home/foo /tmp/debug/home/foo

# 查看调用栈
(gdb) bt / backtrace

# 逐帧查看调用信息
(gdb) frame n / f n
```

