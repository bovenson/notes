---
title: Shell 脚本示例
tags:
	- Shell
	- Linux
categories:
	- Linux
---

[TOC]

# 字符串

## 拼接

```shell
#### 1
"adgfdf""ccc"

#### 2
a="abc"
b="cde"
echo $a$b
abccde
```

## 替换

```shell
# sed
echo $LINE | sed -e "s/12345678/\"${replace}\"/g"
```

# 循环

```shell
#### while
$ while true; do echo "HELLO"; sleep 1; done

#### for
$ for i in {1..10}; do echo -n "This is a test in loop $i "; date ; sleep 5; done

### 循环加1
$ a=0; while true; do ((a=a+1)) && echo $a; sleep 1; done
1
2
3
```

# 参数

## getopts

```shell
# opts
:前缀	忽略错误
:后缀	参数后必须有值

# example
:abc:de:	忽略参数错误，-c、-e后必须有值
```

**示例**

```shell
#!/bin/bash

# define usage info
usage() {
    cat <<EOF
Usage: $0 [-a] [-b name] msg
EOF
}

while getopts ":ab:Bc:" opt; do
    case $opt in
        a) echo "found -a" ; a="hello" ;;
        b) echo "found -b and value is: $OPTARG" ;;
        c) echo "found -c and value is: $OPTARG" ;;
        *) usage ;;
    esac
done

shift $(($OPTIND - 1))

echo "MSG: $1"

if [ -n "${a}" ] ; then         # or [ ! -z "${a}" ]
    echo "-a exists : ${a}"
else
    echo "-a not exists"
fi
```

```shell
#!/bin/bash

usage() {
    cat <<EOF
Usage: $0 [-h host] [-p port]
       $0 [-a host:port]
EOF
}

while getopts "h:p:a:" opt; do
    case $opt in
        h) rhost="$OPTARG" ;;
        p) rport="$OPTARG" ;;
        a) raddress="$OPTARG" ;;
        *) usage ; exit 1 ;;
    esac
done

if [[ ( -z "$raddress" ) && ( -z "$rhost" || -z "$rport" ) ]]; then
    usage
    exit 1
fi
```

# 路径

## 获取绝对路径

```shell
$ readlink -f ../../ctr-score
/home/work/sunzhenkai/Git/ctr-score
$ link=`readlink -f ../../ctr-score`
$ echo $link
/home/work/sunzhenkai/Git/ctr-score
```

## 打印当前目录下所有文件

```shell
###### 1
for file in *; do echo ${file}; done;
# 示例输出
test-01.sh
test-02.sh
test-03.sh

###### 2
for file in `ls`; do echo ${file}; done;
# 示例输出
test-01.sh
test-02.sh
test-03.sh
```

## 获取当前工作路径

```shell
### 1
# `pwd`
bovenson@ThinkCentre:~$ echo `pwd`
/home/bovenson

### 2
# ${PWD} or $PWD
bovenson@ThinkCentre:~$ echo ${PWD}
/home/bovenson
bovenson@ThinkCentre:~$ echo $PWD
/home/bovenson
```

## 获取脚本相对路径

获取脚本相对于当前工作路径的路径。

```shell
$(dirname $0)
```

## 获取脚本绝对路径

```shell
${PWD}/$(dirname $0)
```

```shell
bovenson@ThinkCentre:~/Tmp$ pwd
/home/bovenson/Tmp
bovenson@ThinkCentre:~/Tmp$ cat a/b/c/test.sh
#########################################################################
# File Name: a/b/c/test.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-21 10:07:32
#########################################################################
#!/bin/bash

BASEDIR=$(dirname $0)
FULLPATH="${PWD}/$(dirname $0)"

echo "PWD:      $PWD"
echo "BASEDIR:  $BASEDIR"
echo "FULLPATH: $FULLPATH"

bovenson@ThinkCentre:~/Tmp$ bash a/b/c/test.sh 
PWD:      /home/bovenson/Tmp
BASEDIR:  a/b/c
FULLPATH: /home/bovenson/Tmp/a/b/c
```

## 获取脚本所在目录

```shell
#### bash 获取执行的脚本所在的路径
# 1
"$(dirname "$0")"

# 2
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
```

## 更改PWD为文件所在路径

```shell
# 1
cd "$(dirname "$0")"

# 2
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

#### 有问题的版本
# FULLPATH="$PWD/$(dirname $0)"	# 提取路径
# cd "$FULLPATH"					# 更改pwd
```

## 提取文件路径

```shell
bovenson@MBP:~/Git/notes/Linux/Script$ echo $(dirname "/hello/sd.java")
/hello
bovenson@MBP:~/Git/notes/Linux/Script$ echo $(dirname "~/hello/sd.java")
~/hello
```
# 其他

## 执行脚本

```shell
$command
eval $command
`$command`

#### 上面三条命令是有区别的
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ command="echo string"
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ $command
string
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ eval $command
string
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ `$command`
```

## 只不删除特定文件

删除除例外文件外所有文件及文件夹。

```shell
#### 1
find . ! -name 'filename-pattern' -type f -exec rm -f {} +
# 删除除匹配'filename-pattern'外的所有常规文件(!递归删除所有文件只剩文件夹, 包括隐藏文件)
# 如果要删除文件夹, 把 -type f 改为 -type d, rm -f 改为 rm -rf
```

## 解压多个文件

```shell
bovenson@ThinkCentre:~/Git/Tianchi/o2o/input$ ls
ccf_offline_stage1_test_revised.zip  ccf_offline_stage1_train.zip  ccf_online_stage1_train.zip	sample_submission.zip
bovenson@ThinkCentre:~/Git/Tianchi/o2o/input$ for i in $(ls *.zip); do
> unzip $i
> done
Archive:  ccf_offline_stage1_test_revised.zip
  inflating: ccf_offline_stage1_test_revised.csv  
Archive:  ccf_offline_stage1_train.zip
  inflating: ccf_offline_stage1_train.csv  
Archive:  ccf_online_stage1_train.zip
  inflating: ccf_online_stage1_train.csv  
Archive:  sample_submission.zip
  inflating: sample_submission.csv   
bovenson@ThinkCentre:~/Git/Tianchi/o2o/input$ 
```

**单独Shell脚本**

```shell
#!/bin/bash
for i in $(ls *.zip 2>/dev/null)
do
    unzip $i
done
```

## 后台执行并重定向输出

新建文件`mstart`, 写入下面代码.

```bash
#!/bin/bash
$1 1>/etc/null 2>&1 &
```

**说明**

- `1>/etc/null`将标准输出(`stdout`)重定向到空设备文件,也可以重定向到文件,即将标准输出输出到指定文件(例如:`1>~/myapplog`)
- `2>&1` 把标准错误(`stderr`)输出重定向到标准输出(`stdout`), 也可以重定向到其他位置.
- `&` 将程序设置在后台运行

**使用**

- 首先更改权限

  `chmod a+x ./mstart`

- 将文件`mstart`放到环境变量中(需要使新设置的环境变量生效, 如:`source /etc/profile`(如果实在`/etc/profile`文件中配置的PATH的话))

- 使用`mstart`命令启动`gedit`

  `mstart gedit`

**这样就可以在后台启动一个程序,且不会在控制台打印程序的输出信息.**

## 间隔执行命令

```shell
#### watch
$ watch -n 1 date		# 每秒打印日期

#### while 循环
$ while true; do date; sleep 1; done	# 每秒打印日期
```

## 统计

```shell
$ recommend-service-user]$ cat recommend-service-user.stdout.log.20190416 | grep "remote address" | awk 'match($0, /[0-9]{1,}\.[0-9]{1,}\.[0-9]{1,}\.[0-9]{1,}/){ print substr($0, RSTART, RLENGTH) }' | sort | uniq -c | sort -rn
```

## 读取文件并按行处理

```shell
## 01
$ while read p; do host $p; done < file-name
## 02
$ cat 03 | while read line; do host $line; done
```

# 判断

```shell
$ if [ -f 01 -a "${a}" = "abc" -a "${b}" = "bcd" ]; then echo "YES"; else echo "NO"; fi
```

## 复杂表达式

```shell
# bash
if [[ ( -z "$raddress" ) && ( -z "$rhost" || -z "$rport" ) ]]; then
	do something ...
fi
```

# 网络

## 代理

```shell
# set
export http_proxy='http://proxyServerSddress:proxyPort'    
export https_proxy='https://proxyServerSddress:proxyPort'

# unset
unset http_proxy
unset https_proxy
```

