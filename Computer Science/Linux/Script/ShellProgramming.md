---
title: Shell 编程
tags:
	- Shell
categories:
	- Linux
---

---
[TOC]

**注意:** 不要将脚本命名为`test`,因为`test`是一个内置的应用程序

# 第一个shell脚本

```shell
#!/bin/bash
# File: hello.sh
echo "Hello World !"
```

```shell
bovenson@ThinkCentre:~/Git/neu-ip-gateway-manager$ bash hello.sh 
Hello World!
```

# 变量

**定义**

```shell
v="This is a variable."
```

**注意**

- 变量名和等号之间不能有空格
- 首个字符必须为字母（a-z，A-Z）
- 中间不能有空格，可以使用下划线（_）
- 不能使用标点符号
- 不能使用bash里的关键字（可用help命令查看保留关键字）
- 已定义的变量，可以被重新定义

**使用**

使用一个定义过的变量，只要在变量名前面加美元符号即可

```shell
#!/bin/bash
# File: hello.sh

v="This is a variable."

echo $v
echo ${v}	# 变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
This is a variable.
This is a variable.
bovenson@ThinkCentre:~/Tmp$ 
```

**变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界**, 推荐给所有变量加上花括号，这是个好的编程习惯, 比如下面这种情况:

```shell
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill}Script"
done
```

**只读变量**

使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变。

```shell
#!/bin/bash
# File: hello.sh

my_name="szk"
readonly my_name
my_name="bovenson"
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
hello.sh:行6: my_name: 只读变量
bovenson@ThinkCentre:~/Tmp$ 
```

**删除变量**

```shell
unset variable_name
```

**注意**:

- 变量被删除后不能再次使用
- unset命令不能删除只读变量

**变量类型**

- **局部变量:** 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量
- **环境变量:** 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量
- **shell变量:** shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行

# 字符串

字符串是shell编程中最常用最有用的数据类型（除了数字和字符串，也没啥其它类型好用了），字符串可以用单引号，也可以用双引号，也可以不用引号。单双引号的区别跟PHP类似。

## 单引号

```shell
str='this is a string'
```

单引号字符串的限制:

- 单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的
- 单引号字串中不能出现单引号（对单引号使用转义符后也不行）

## 双引号

```shell
my_name='szk'
str="Hello, I know your are \"$my_name\"! \n"
```

双引号的优点:

- 双引号里可以有变量
- 双引号里可以出现转义字符

## 拼接字符串

```shell
#!/bin/bash
# File: hello.sh

my_name="szk"
greeting="hello, "$my_name" !"
greeting_1="hello, ${my_name} !"
echo $greeting $greeting_1
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
hello, szk ! hello, szk !
bovenson@ThinkCentre:~/Tmp$ 
```

## 获取字符串长度

```shell
#!/bin/bash
# File: hello.sh

my_name="szk"
echo ${#my_name}
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
3
```

## 提取子字符串

```shell
#!/bin/bash
# File: hello.sh

slogon="One World, One Dream!"
echo ${slogon:4:5}	# 这里4是开始下标, 5是截取长度
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
World
```

## 查找字符串中字符位置

```shell
#!/bin/bash
# File: hello.sh

slogon="One World, One Dream!"

index=`expr index "$slogon" W`	# 查找W字符
echo ${index}
index=`expr index "$slogon" Dra` # 查找字符D, r, a中的一个
echo ${index}
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
5
7
```

## 字符串截取

假设有变量 `var=http://www.aaa.com/123.htm`

**方法一**

```shell
# # 号截取，删除左边字符，保留右边字符
echo ${var#*//}
# 其中 var 是变量名，# 号是运算符，*// 表示从左边开始删除第一个 // 号及左边的所有字符
# 即删除 http://
# 结果是 ：www.aaa.com/123.htm
```

**方法二**

```shell
# ## 号截取，删除左边字符，保留右边字符。
echo ${var##*/}
# ##*/ 表示从左边开始删除最后（最右边）一个 / 号及左边的所有字符
# 即删除 http://www.aaa.com/
# 结果是 123.htm
```

**方法三**

```shell
 # %号截取，删除右边字符，保留左边字符
 echo ${var%/*}
 # %/* 表示从右边开始，删除第一个 / 号及右边的字符
 # 结果是：http://www.aaa.com
```

**方法四**

```shell
# %% 号截取，删除右边字符，保留左边字符
echo ${var%%/*}
# %%/* 表示从右边开始，删除最后（最左边）一个 / 号及右边的字符
# 结果是：http:
```

**方法五**

```shell
# 从左边第几个字符开始，及字符的个数
echo ${var:0:5}
# 其中的 0 表示左边第一个字符开始，5 表示字符的总个数。
# 结果是：http:
```

**方法六**

```shell
# 从左边第几个字符开始，一直到结束
echo ${var:7}
# 其中的 7 表示左边第8个字符开始，一直到结束
# 结果是 ：www.aaa.com/123.htm
```

**方法七**

```shell
# 从右边第几个字符开始，及字符的个数
echo ${var:0-7:3}
# 其中的 0-7 表示右边算起第七个字符开始，3 表示字符的个数。
# 结果是：123
```

**方法八**

```shell
# 从右边第几个字符开始，一直到结束
echo ${var:0-7}
# 表示从右边第七个字符开始，一直到结束。
# 结果是：123.htm
```

**注:**

- 左边的第一个字符是用 0 表示，右边的第一个字符用 0-1 表示

# 数组

- bash支持一维数组（不支持多维数组）
- 数组元素的下标由0开始编号
- 获取数组中的元素要利用下标，下标可以是整数或算术表达式，其值应大于或等于0
- 可以不使用连续的下标，而且下标的范围没有限制。

**定义数组**

在Shell中，用括号来表示数组，数组元素用"空格"符号分割开:

`数组名=(值1 值2 ... 值n)`

例如:`array_name=(value0 value1 value2 value3)`

或者:

```shell
array_name=(
value0
value1
value2
value3
)
```

还可以单独定义数组的各个分量：

```shell
array_name[0]=value0
array_name[1]=value1
.
.
.
array_name[n]=valuen
```

**读取数组**

读取数组元素值的一般格式是：

`${数组名[下标]}`

例如:

`valuen=${array_name[n]}`

使用@符号可以获取数组中的所有元素，例如：

`echo ${array_name[@]}`

```shell
#!/bin/bash
# File: hello.sh

slogon=("One" "World" "," "One" "Dream!")
echo ${slogon[0]}
echo ${slogon[1]}
echo ${slogon[@]}
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
One
World
One World , One Dream!
bovenson@ThinkCentre:~/Tmp$ 
```

**获取数组长度**

```shell
#!/bin/bash
# File: hello.sh

slogon=("One" "World" "," "One" "Dream!")
# 获取数组长度的方法与获取字符串长度的方法相同
echo ${#slogon[@]}
echo ${#slogon[*]}
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
5
5
```

# 注释

- 以"#"开头的行就是注释，会被解释器忽略
- sh里没有多行注释，只能每一行加一个#号

示例:

```shell
#--------------------------------------------
# 这是一个注释
# author：菜鸟教程
# site：www.runoob.com
# slogan：学的不仅是技术，更是梦想！
#--------------------------------------------
##### 用户配置区 开始 #####
#
#
# 这里可以添加脚本描述信息
# 
#
##### 用户配置区 结束  #####
```

每一行加个#符号太费力了，可以把这一段要注释的代码用一对花括号括起来，定义成一个函数，没有地方调用这个函数，这块代码就不会执行，达到了和注释一样的效果.

# 参数

**位置参数**

我们可以在执行 Shell 脚本时，向脚本传递参数，脚本内获取参数的格式为：**$n**。**n** 代表一个数字，1 为执行脚本的第一个参数，2 为执行脚本的第二个参数，以此类推……

位置参数包括命令名和命令行参数.在shell脚本中, 按照它们在命令行上的位置来引用它们(如命令`command a b c` 中参数`a`,`b`,`c`的位置 ). 不能通过赋值语句改变位置参数的值.

*bash的内置命令`set`可以修改除调用程序名(命令名)以外的任意位置参数, 但是在tcsh中, `set`内置命令不能改变位置参数的值*

```shell
在脚本中使用 $0 $1 来获取位置参数
```

```shell
#!/bin/bash
# File: hello.sh

echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第二个参数为：$2";
echo "第三个参数为：$3";
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh v1 v2 v3 v4 v5 v6 v7
Shell 传递参数实例！
执行的文件名：hello.sh
第一个参数为：v1
第二个参数为：v2
第三个参数为：v3
bovenson@ThinkCentre:~/Tmp$ 
```

**几个特殊字符用来处理参数**

| 字符   | 说明                                       |
| ---- | ---------------------------------------- |
| `$#` | 传递到脚本的参数个数                               |
| `$*` | 以一个单字符串显示所有向脚本传递的参数。如`$*`用`"`括起来的情况、以`$1` ` $2`  … `$n`的形式输出所有参数。 |
| `$$` | 脚本运行的当前进程ID号                             |
| `$!` | 后台运行的最后一个进程的ID号                          |
| `$@` | 与`$*`相同，但是使用时加引号，并在引号中返回每个参数。如`$@`用`"`括起来的情况、以`$1` `$2`  … `$n` 的形式输出所有参数。 |
| `$-` | 显示Shell使用的当前选项，与set命令功能相同。               |
| `$?` | 显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。          |

```shell
#!/bin/bash
# File: hello.sh

echo "Shell 传递参数实例！";
echo "第一个参数为：$1";

echo "参数个数为：$#";
echo "传递的参数作为一个字符串显示：$*";
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh v1 v2 v3 v4 v5 v6 v7
Shell 传递参数实例！
第一个参数为：v1
参数个数为：7
传递的参数作为一个字符串显示：v1 v2 v3 v4 v5 v6 v7
```

**`$*` 与 `$@` 区别**

- 相同点：都是引用所有参数。
- 不同点：只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，，则 " * " 等价于 "1 2 3"（传递了一个参数），而 "@" 等价于 "1" "2" "3"（传递了三个参数）。

# 基本运算符

原生bash不支持简单的数学运算，但是可以通过其他命令来实现，例如 awk 和 expr，expr 最常用。

expr 是一款表达式计算工具，使用它能完成表达式的求值操作。例如，两个数相加(**注意使用的是反引号 ` 而不是单引号 '**)：

```shell
#!/bin/bash

val=`expr 2 + 2`
echo "两数之和为 : $val"
```

- 表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2，这与我们熟悉的大多数编程语言不一样。
- 完整的表达式要被反引号包含，注意这个字符不是常用的单引号，在 Esc 键下边。

## 算术运算符

| 运算符  | 说明                       | 举例                            |
| ---- | ------------------------ | ----------------------------- |
| +    | 加法                       | \`expr $a + $b\` 结果为 30       |
| -    | 减法                       | \`expr \$a - \$b` 结果为 -10     |
| *    | 乘法                       | \`expr \$a \\* \$b\` 结果为  200 |
| /    | 除法                       | \`expr\ $b / \$a\` 结果为 2      |
| %    | 取余                       | \`expr \$b % \$a\` 结果为 0      |
| =    | 赋值                       | a=`$b` 将把变量 b 的值赋给 a          |
| ==   | 相等。用于比较两个数字，相同则返回 true   | [ `$a` == `$b` ] 返回 false     |
| !=   | 不相等。用于比较两个数字，不相同则返回 true | [\ $a != \$b ] 返回 true        |

**注意:**

- 条件表达式要放在方括号之间，并且要有空格，例如: **[\$a==\$b]** 是错误的，必须写成 **[ \$a == \$b ]**
- 乘号(*)前边必须加反斜杠(\)才能实现乘法运算

## 关系运算符

- 关系运算符只支持数字，不支持字符串，除非字符串的值是数字

| 运算符  | 说明                           |
| ---- | ---------------------------- |
| -eq  | 检测两个数是否相等，相等返回 true          |
| -ne  | 检测两个数是否相等，不相等返回 true         |
| -gt  | 检测左边的数是否大于右边的，如果是，则返回 true   |
| -lt  | 检测左边的数是否小于右边的，如果是，则返回 true   |
| -ge  | 检测左边的数是否大于等于右边的，如果是，则返回 true |
| -le  | 检测左边的数是否小于等于右边的，如果是，则返回 true |

## 布尔运算符

| 运算符  | 说明                                |
| ---- | --------------------------------- |
| !    | 非运算，表达式为 true 则返回 false，否则返回 true |
| -o   | 或运算，有一个表达式为 true 则返回 true         |
| -a   | 与运算，两个表达式都为 true 才返回 true         |

## 逻辑运算符

| 运算符  | 说明      |
| ---- | ------- |
| &&   | 逻辑的 AND |
| \|\| | 逻辑的 OR  |

## 字符串运算符

| 运算符  | 说明                             |
| ---- | ------------------------------ |
| =    | 检测两个字符串是否相等，相等返回 true          |
| !=   | 检测两个字符串是否相等，不相等返回 true         |
| -z   | 检测字符串长度是否为0，为0返回 true          |
| -n   | 检测字符串长度是否为0，不为0返回 true         |
| str  | 检测字符串是否为空，不为空返回 true, 如 [ $a ] |

## 文件测试运算符

文件测试运算符用于检测 Unix 文件的各种属性

| 操作符     | 说明                                       | 举例                     |
| ------- | ---------------------------------------- | ---------------------- |
| -b file | 检测文件是否是块设备文件，如果是，则返回 true。               | [ -b $file ] 返回 false。 |
| -c file | 检测文件是否是字符设备文件，如果是，则返回 true。              | [ -c $file ] 返回 false。 |
| -d file | 检测文件是否是目录，如果是，则返回 true。                  | [ -d $file ] 返回 false。 |
| -f file | 检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。 | [ -f $file ] 返回 true。  |
| -g file | 检测文件是否设置了 SGID 位，如果是，则返回 true。           | [ -g $file ] 返回 false。 |
| -k file | 检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。   | [ -k $file ] 返回 false。 |
| -p file | 检测文件是否是有名管道，如果是，则返回 true。                | [ -p $file ] 返回 false。 |
| -u file | 检测文件是否设置了 SUID 位，如果是，则返回 true。           | [ -u $file ] 返回 false。 |
| -r file | 检测文件是否可读，如果是，则返回 true。                   | [ -r $file ] 返回 true。  |
| -w file | 检测文件是否可写，如果是，则返回 true。                   | [ -w $file ] 返回 true。  |
| -x file | 检测文件是否可执行，如果是，则返回 true。                  | [ -x $file ] 返回 true。  |
| -s file | 检测文件是否为空（文件大小是否大于0），不为空返回 true。          | [ -s $file ] 返回 true。  |
| -e file | 检测文件（包括目录）是否存在，如果是，则返回 true。             | [ -e $file ] 返回 true。  |


# 控制结构

## if...then

```bash
if test-command
then
		comands
fi
```
```shell
if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi
```

## if else

```shell
if condition
then
    command1 
    command2
    ...
    commandN
else
    command
fi
```

## if else-if else

```shell
if condition1
then
    command1
elif condition2 
then 
    command2
else
    commandN
fi
```

## for 循环

```shell
for var in item1 item2 ... itemN
do
    command1
    command2
    ...
    commandN
done
```

```shell
for var in item1 item2 ... itemN; do command1; command2… done;
```

```shell
# 示例 1
for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
done
# 输出
The value is: 1
The value is: 2
The value is: 3
The value is: 4
The value is: 5

# 示例 2
for str in 'This is a string'
do
    echo $str
done
# 输出
This is a string

# 示例3
## from 0 to 10 by step 2
$ for i in `seq 0 2 10`; do echo $i; done
0
2
4
6
8
10
## PS
# seq [选项]... 尾数
# seq [选项]... 首数 尾数
# seq [选项]... 首数 增量 尾数
```

## while 语句

```shell
while condition
do
    command
done
```

```shell
# 示例 1
#!/bin/sh
int=1
while(( $int<=5 ))
do
        echo $int
        let "int++"
done
# 输出
1
2
3
4
5

# 示例 2
echo '按下 <CTRL-D> 退出'
echo -n '输入你最喜欢的电影名: '
while read FILM
do
    echo "是的！$FILM 是一部好电影"
done
```

## 无限循环

```shell
while :
do
    command
done

# 或者
while true
do
    command
done

# 或者
for (( ; ; ))
```

## until 循环

```shell
until condition
do
    command
done

# 条件可为任意测试条件，测试发生在循环末尾，因此循环至少执行一次—请注意这一点
```

## case

```shell
case 值 in
模式1)
    command1
    command2
    ...
    commandN
    ;;
模式2）
    command1
    command2
    ...
    commandN
    ;;
esac

# case的语法和C family语言差别很大，它需要一个esac（就是case反过来）作为结束标记
# 每个case分支用右圆括号，用两个分号表示break。
```



## 跳出循环

在循环过程中，有时候需要在未达到循环结束条件时强制跳出循环，Shell使用两个命令来实现该功能：break和continue。

# 函数

shell中函数的定义格式如下:

```shell
[ function ] funname [()]
{
    action;
    [return int;]
}
```

- 可以带function fun() 定义，也可以直接fun() 定义,不带任何参数
- 参数返回，可以显示加：return 返回，如果不加，将以最后一条命令运行结果，作为返回值。 return后跟数值n(0-255)

```shell
# 示例 1
demoFun(){
    echo "这是我的第一个 shell 函数!"
}
echo "-----函数开始执行-----"
demoFun
echo "-----函数执行完毕-----"

# 示例 2
funWithReturn(){
    echo "这个函数会对输入的两个数字进行相加运算..."
    echo "输入第一个数字: "
    read aNum
    echo "输入第二个数字: "
    read anotherNum
    echo "两个数字分别为 $aNum 和 $anotherNum !"
    return $(($aNum+$anotherNum))
}
funWithReturn
echo "输入的两个数字之和为 $? !"
```

# 输入/输出重定向

| 命令              | 说明                             |
| --------------- | ------------------------------ |
| command > file  | 将输出重定向到 file。                  |
| command < file  | 将输入重定向到 file。                  |
| command >> file | 将输出以追加的方式重定向到 file。            |
| n > file        | 将文件描述符为 n 的文件重定向到 file。        |
| n >> file       | 将文件描述符为 n 的文件以追加的方式重定向到 file。  |
| n >& m          | 将输出文件 m 和 n 合并。                |
| n <& m          | 将输入文件 m 和 n 合并。                |
| << tag          | 将开始标记 tag 和结束标记 tag 之间的内容作为输入。 |

需要注意的是文件描述符 0 通常是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）。

# 参考

- [Arithmetic Expansion](http://mywiki.wooledge.org/ArithmeticExpression)