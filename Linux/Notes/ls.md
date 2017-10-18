---
title: ls命令
tags: Linux, ls, 命令
---



# 简介

# 参数

# CASE

## 只列出文件夹

```shell
#### ls -ld */	
# *表示所有的文件,加上/就是子目录了
bovenson@ThinkCentre:~/Tmp$ ls -ld */
drwxr-xr-x 3 bovenson bovenson 4096 9月  21 10:47 aa/
drwxr-xr-x 3 bovenson bovenson 4096 10月 16 19:37 C/
drwxr-xr-x 3 bovenson bovenson 4096 10月 16 19:34 C++/
drwxr-xr-x 2 bovenson bovenson 4096 9月  25 15:26 Java/
drwxr-xr-x 2 bovenson bovenson 4096 7月  25 15:19 python/
drwxr-xr-x 6 bovenson bovenson 4096 9月  21 10:59 RsyncTest/
drwxr-xr-x 5 bovenson bovenson 4096 6月  10 19:58 venv/

#### ls -lp | grep '/'
bovenson@ThinkCentre:~/Tmp$ ls -lp | grep '/'
drwxr-xr-x 3 bovenson bovenson    4096 9月  21 10:47 aa/
drwxr-xr-x 3 bovenson bovenson    4096 10月 16 19:37 C/
drwxr-xr-x 3 bovenson bovenson    4096 10月 16 19:34 C++/
drwxr-xr-x 2 bovenson bovenson    4096 9月  25 15:26 Java/
drwxr-xr-x 2 bovenson bovenson    4096 7月  25 15:19 python/
drwxr-xr-x 6 bovenson bovenson    4096 9月  21 10:59 RsyncTest/
drwxr-xr-x 5 bovenson bovenson    4096 6月  10 19:58 venv/


#### ls -l | grep '^d'
bovenson@ThinkCentre:~/Tmp$ ls -l | grep '^d'
drwxr-xr-x 3 bovenson bovenson    4096 9月  21 10:47 aa
drwxr-xr-x 3 bovenson bovenson    4096 10月 16 19:37 C
drwxr-xr-x 3 bovenson bovenson    4096 10月 16 19:34 C++
drwxr-xr-x 2 bovenson bovenson    4096 9月  25 15:26 Java
drwxr-xr-x 2 bovenson bovenson    4096 7月  25 15:19 python
drwxr-xr-x 6 bovenson bovenson    4096 9月  21 10:59 RsyncTest
drwxr-xr-x 5 bovenson bovenson    4096 6月  10 19:58 venv
```

## 查看文件夹用量（所有文件所占的文件块的总数）

```shell
bovenson@ThinkCentre:~/Tmp$ ls -l ls-test/
总用量 12
-rw-r--r-- 1 bovenson bovenson    6 10月 18 10:11 a1
-rw-r--r-- 1 bovenson bovenson   19 10月 18 10:12 a2
drwxr-xr-x 2 bovenson bovenson 4096 10月 18 10:12 b
bovenson@ThinkCentre:~/Tmp$ ls -l */
aa/:
总用量 4
drwxr-xr-x 3 bovenson bovenson 4096 9月  21 10:47 b

C/:
总用量 16
-rwxr-xr-x 1 bovenson bovenson 9272 10月 16 19:37 c
-rw-r--r-- 1 bovenson bovenson   97 10月 16 19:39 c.cpp

C++/:
总用量 28
-rwxr-xr-x 1 bovenson bovenson 9328 10月 16 19:34 t
-rw-r--r-- 1 bovenson bovenson  401 10月 16 19:34 T.class
-rw-r--r-- 1 bovenson bovenson  136 10月 16 19:29 t.cpp
-rw-r--r-- 1 bovenson bovenson  106 10月 16 18:44 T.java
-rw-r--r-- 1 bovenson bovenson   75 10月 16 19:49 t.py

Java/:
总用量 0

ls-test/:
总用量 12
-rw-r--r-- 1 bovenson bovenson    6 10月 18 10:11 a1
-rw-r--r-- 1 bovenson bovenson   19 10月 18 10:12 a2
drwxr-xr-x 2 bovenson bovenson 4096 10月 18 10:12 b

python/:
总用量 0

RsyncTest/:
总用量 16
drwxr-xr-x 3 bovenson bovenson 4096 9月  21 10:07 a
drwxr-xr-x 3 bovenson bovenson 4096 9月  21 10:49 aa
drwxr-xr-x 3 bovenson bovenson 4096 9月  21 10:51 ab
drwxr-xr-x 3 bovenson bovenson 4096 9月  21 10:59 t

venv/:
总用量 16
drwxr-xr-x 2 bovenson bovenson 4096 6月  10 19:58 bin
drwxr-xr-x 2 bovenson bovenson 4096 6月  10 19:57 include
drwxr-xr-x 3 bovenson bovenson 4096 6月  10 19:57 lib
-rw-r--r-- 1 bovenson bovenson   60 6月  10 19:58 pip-selfcheck.json
```

