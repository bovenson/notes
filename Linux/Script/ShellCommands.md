---
title: Shell脚本编程常用命令
tags:
	- Shell
categories:
	- Linux
---

[TOC]

# test

Shell中的 test 命令用于检查某个条件是否成立，它可以进行表达式、数值、字符和文件的测试。

## 格式

在Shell脚本编程中，有两种方式使用`test`命令。

- `test option file`
- `[ option file ]`

使用[]代替test时, 表达式两侧([]内)的空格不能省略.

## 测试

### 表达式

表达式一般是文本、数字或文件和目录属性的比较，并且可以包含变量、常量和运算符。运算符可以是字符串运算符、整数运算符、文件运算符或布尔运算符。

```shell
#!/bin/bash

# 表达式测试

if test 2 > 1
then 
	echo 'True'
else
	echo 'False'
fi

if [ 3 -gt 2 -a 1 -gt 2 ]
then
	echo 'All True'
else
	echo 'Not all true'
fi
```

**输出:**

```shell
True
Not all true
```



### 数值测试

| 参数   | 说明      |
| ---- | ------- |
| -eq  | 等于则为真   |
| -ne  | 不等于则为真  |
| -gt  | 大于则为真   |
| -ge  | 大于等于则为真 |
| -lt  | 小于则为真   |
| -le  | 小于等于则为真 |

```shell
#!/bin/bash

# 数值测试

num1 = 100
num2 = 200

if test $[num1] -eq $[num2]
then
	echo 'EQUAL'
else
	echo 'NOT EQUAL'
fi
```



### 字符串测试 

| 参数     | 说明           |
| ------ | ------------ |
| =      | 等于则为真        |
| !=     | 不相等则为真       |
| -z 字符串 | 字符串的长度为零则为真  |
| -n 字符串 | 字符串的长度不为零则为真 |

### 文件测试

文件测试运算符用于检测 Unix 文件的各种属性

| 操作符     | 说明                                       | 举例                     |
| ------- | ---------------------------------------- | ---------------------- |
| -b file | 检测文件是否是块设备文件，如果是，则返回 true。               | [ -b $file ] 返回 false。 |
| -c file | 检测文件是否是字符设备文件，如果是，则返回 true。              | [ -c $file ] 返回 false。 |
| -d file | 检测文件是否是目录，如果是，则返回 true。                  | [ -d $file ] 返回 false。 |
| -e file | 检测文件（包括目录）是否存在，如果是，则返回 true。             | [ -e $file ] 返回 true。  |
| -f file | 检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。 | [ -f $file ] 返回 true。  |
| -g file | 检测文件是否设置了 SGID 位，如果是，则返回 true。           | [ -g $file ] 返回 false。 |
| -G file | 检测文件存在并属于有效组ID                           |                        |
| -h file | 检测文件存在并是一个符号连接(同 -L)                     |                        |
| -k file | 检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。   | [ -k $file ] 返回 false。 |
| -L file | 检测文件存在并是一个符号连接(同 -h)                     |                        |
| -o file | 检测文件存在并属于有效用户ID                          |                        |
| -p file | 检测文件是否是命名管道，如果是，则返回 true。                | [ -p $file ] 返回 false。 |
| -r file | 检测文件是否可读，如果是，则返回 true。                   | [ -r $file ] 返回 true。  |
| -s file | 检测文件是否为空（文件大小是否大于0），不为空返回 true。          | [ -s $file ] 返回 true。  |
| -S file | 检测是否是套接字                                 |                        |
| -u file | 检测文件是否设置了 SUID 位，如果是，则返回 true。           | [ -u $file ] 返回 false。 |
| -w file | 检测文件是否可写，如果是，则返回 true。                   | [ -w $file ] 返回 true。  |
| -x file | 检测文件是否可执行，如果是，则返回 true。                  | [ -x $file ] 返回 true。  |

```shell
test file1 -ef file2	# 判断文件具有相同的设备号和节点号
test file1 -nt file2	# 判断文件1比文件2 新
test file1 -ot file2	# 判断文件1比文件2 旧
```




```shell
#!/bin/bash

# 文件测试

if test -e ./file-test
then
	echo 'YES'
else
	echo 'NO'
fi
```

## 逻辑操作符

Shell还提供了与( -a )、或( -o )、非( ! )三个逻辑操作符用于将测试条件连接起来，其优先级为："!"最高，"-a"次之，"-o"最低。

```shell
if test -e ./notFile -o -e ./bash
then
    echo '有一个文件存在!'
else
    echo '两个文件都不存在'
fi
```

## man test

```shell
TEST(1)                                                       User Commands                                                       TEST(1)

NAME
       test - check file types and compare values

SYNOPSIS
       test EXPRESSION
       test

       [ EXPRESSION ]
       [ ]
       [ OPTION

DESCRIPTION
       Exit with the status determined by EXPRESSION.

       --help display this help and exit

       --version
              output version information and exit

       An omitted EXPRESSION defaults to false.  Otherwise, EXPRESSION is true or false and sets exit status.  It is one of:

       ( EXPRESSION )
              EXPRESSION is true

       ! EXPRESSION
              EXPRESSION is false

       EXPRESSION1 -a EXPRESSION2
              both EXPRESSION1 and EXPRESSION2 are true

       EXPRESSION1 -o EXPRESSION2
              either EXPRESSION1 or EXPRESSION2 is true

       -n STRING
              the length of STRING is nonzero

       STRING equivalent to -n STRING

       -z STRING
              the length of STRING is zero

       STRING1 = STRING2
              the strings are equal

       STRING1 != STRING2
              the strings are not equal

       INTEGER1 -eq INTEGER2
              INTEGER1 is equal to INTEGER2

       INTEGER1 -ge INTEGER2
              INTEGER1 is greater than or equal to INTEGER2

       INTEGER1 -gt INTEGER2
              INTEGER1 is greater than INTEGER2

       INTEGER1 -le INTEGER2
              INTEGER1 is less than or equal to INTEGER2

       INTEGER1 -lt INTEGER2
              INTEGER1 is less than INTEGER2

       INTEGER1 -ne INTEGER2
              INTEGER1 is not equal to INTEGER2

       FILE1 -ef FILE2
              FILE1 and FILE2 have the same device and inode numbers

       FILE1 -nt FILE2
              FILE1 is newer (modification date) than FILE2

       FILE1 -ot FILE2
              FILE1 is older than FILE2

       -b FILE
              FILE exists and is block special

       -c FILE
              FILE exists and is character special

       -d FILE
              FILE exists and is a directory

       -e FILE
              FILE exists

       -f FILE
              FILE exists and is a regular file

       -g FILE
              FILE exists and is set-group-ID

       -G FILE
              FILE exists and is owned by the effective group ID

       -h FILE
              FILE exists and is a symbolic link (same as -L)

       -k FILE
              FILE exists and has its sticky bit set

       -L FILE
              FILE exists and is a symbolic link (same as -h)

       -O FILE
              FILE exists and is owned by the effective user ID

       -p FILE
              FILE exists and is a named pipe

       -r FILE
              FILE exists and read permission is granted

       -s FILE
              FILE exists and has a size greater than zero

       -S FILE
              FILE exists and is a socket

       -t FD  file descriptor FD is opened on a terminal

       -u FILE
              FILE exists and its set-user-ID bit is set

       -w FILE
              FILE exists and write permission is granted

       -x FILE
              FILE exists and execute (or search) permission is granted

       Except  for  -h  and  -L, all FILE-related tests dereference symbolic links.  Beware that parentheses need to be escaped (e.g., by
       backslashes) for shells.  INTEGER may also be -l STRING, which evaluates to the length of STRING.

       NOTE: Binary -a and -o are inherently ambiguous.  Use 'test EXPR1 && test EXPR2' or 'test EXPR1 || test EXPR2' instead.

       NOTE: [ honors the --help and --version options, but test does not.  test treats each of those as it  treats  any  other  nonempty
       STRING.

       NOTE:  your shell may have its own version of test and/or [, which usually supersedes the version described here.  Please refer to
       your shell's documentation for details about the options it supports.

AUTHOR
       Written by Kevin Braunsdorf and Matthew Bradburn.

REPORTING BUGS
       GNU coreutils online help: <http://www.gnu.org/software/coreutils/>
       Report [ translation bugs to <http://translationproject.org/team/>

COPYRIGHT
       Copyright © 2016 Free Software Foundation, Inc.  License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
       This is free software: you are free to change and redistribute it.  There is NO WARRANTY, to the extent permitted by law.

SEE ALSO
       Full documentation at: <http://www.gnu.org/software/coreutils/[>
       or available locally via: info '(coreutils) test invocation'

GNU coreutils 8.26                                            February 2017                                                       TEST(1)
```

