# Ubuntu笔记

[TOC]

## 查看系统信息

### 查看系统版本

**prox目录下记录当前系统运行的各种数据,version记录的版本信息可以直接通过cat查看**

-   `cat /proc/version`
-   `uname -a`
-   `lsb_release -a`


## 软件管理命令

### 查看已经安装的软件

```shell
dpkg -l
# dpkg --help
# -l|--list [<pattern> ...]        List packages concisely.
```

