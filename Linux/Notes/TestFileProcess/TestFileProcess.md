---
title: 文本文件处理
tags:
	- Linux
categories:
	- Linux
---

# 替换文件内容

**判断文件中有没有某行数据**

```shell
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ cat tfile 
--service=a
--path=/home
--mode=debug
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ if grep "\-\-service" ./tfile; then echo "YES"; else echo "NO"; fi
--service=a
YES
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ if grep "\-\-services" ./tfile; then echo "YES"; else echo "NO"; fi
NO

#### 不打印grep信息
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ if grep -q "\-\-service" ./tfile; then echo "YES"; else echo "NO"; fi
YES
```

**替换**

```shell
#### 原始数据
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ cat tfile 
--service=a
--service=b
--service=c
--path=/home
--mode=debug

#### 替换所有
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ sed 's/\-\-service=.*/\-\-service=service/' tfile 
--service=service
--service=service
--service=service
--path=/home
--mode=debug

#### 替换第一行
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ sed '1s/\-\-service=.*/\-\-service=service/' tfile 
--service=service
--service=b
--service=c
--path=/home
--mode=debug

#### 原始数据未更改
work@c3-data-ml00.bj:~/sunzhenkai/Tmp$ cat tfile 
--service=a
--service=b
--service=c
--path=/home
--mode=debug
```

