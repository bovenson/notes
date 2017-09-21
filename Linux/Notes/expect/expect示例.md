# expect示例

## 创建账户

文件名: `account.sh`

执行: `./account.sh newaccount`

```shell
#!/usr/bin/expect
set passwd "mypasswd"
set timeout 60						# 设置超时, 默认情况下，timeout 是10 秒
if {$argc != 1} {					# 判断参数是否合法,参数的数目可以用$argc得到
	send "usage ./account.sh \$newaccount\n"
	exit
}

# 获取参数复制给user
# 参数存在$argv 当中，比如取第一个参数就是[lindex $argv 0]
# 并且如果需要计算的话必须用expr，如计算2-1，则必须用[expr 2-1]；
set user [lindex $argv [expr $argc-1]]					
spawn sudo useradd -s /bin/bash -g mygroup -m $user		 # 使用spawn命令运行创建账户命令

# 一般情况下，如果连续做两个expect，那么实际上是串行执行的
# expect 与“{ ”之间直接必须有空格或则TAB间隔，否则会出麻烦，会报错invalid command name "expect{" 
# 例子中的结构则是并行执行的，主要是看匹配到了哪一个；在这个例子中，如果你写成串行的话，即
# expect "assword"
# send "$passwd\n"
# expect eof
# send_user "eof"
# 那么第一次将会正确运行，因为第一次sudo 时需要密码；
# 但是第二次运行时由于密码已经输过（默认情况下sudo 密码再次输入时间为5 分钟），则不会提示用户
# 去输入，所以第一个expect 将无法匹配到assword, 而且必须注意的是如果是:
# spawn 命令出现交互式提问的但是expect 匹配不上的话，那么程序会按照timeout
# 的设置进行等待；可是如果spawn 直接发出了eof 也就是本例的情况，那么expect
# "assword"将不会等待，而直接去执行expect eof。
# 这时就会报expect: spawn id exp6 not open，因为没有spawn 在执行，后面的
# expect 脚本也将会因为这个原因而不再执行；所以对于类似sudo 这种命令分支
# 不定的情况，最好是使用并行的方式进行处理；
expect {
    "assword" {
      send_user "sudo now\n"		# 仅仅是一个用户提示而已，可以删除；
      send "$passwd\n"
      exp_continue
	}
    eof {
      send_user "eof\n"
    }
}

spawn sudo passwd $user				# 向spawn 进程发送password；

expect {						   # 使得spawn 进程在匹配到一个后再去匹配接下来的交互提示；
    "assword" {
        send "$passwd\n"
        exp_continue
	}	
	# eof 是必须去匹配的，在spawn 进程结束后会向expect 发送eof；如果
    # 不去匹配，有时也能运行，比如sleep 多少秒后再去spawn 下一个命令，但是不要依赖这种行为
    eof {
   		send_user "eof"
    }
}

spawn sudo smbpasswd -a $user
expect {
  "assword" {
      send "$passwd\n"
      exp_continue
  }
  eof {
  	send_user "eof"
  }
}
```

## ssh登录主机

```shell
#!/usr/bin/expect
set remote_ip "192.168.31.5"
set passwd "password"
set timeout 30
spawn ssh $remote_ip
expect "password:"
send "$passwd\n"
expect "*$"
# 远程执行命令用send发送，不用spawn
send "mkdir tmpdir\n" 
# 注意这个地方，要与操作系统上环境变量PS1相匹配，尤其是有PS1有空格的情况下，一定在expct "*$ "把空格加上，加不上你就完蛋了。我试过。
expect "*$" 
```

这个例子实际上是通过ssh 去登录远程机器，并且在远程机器上创佳一个目录，我们看到在我们输入密码后并没有去expect eof，这是因为ssh 这个spawn 并没有结束，而且手动操作时ssh 实际上也不会自己结束除非你exit；所以你只能expect bash 的提示符，当然也可以是机器名等，这样才可以在远程创建一个目录。

注意，请不要用`spawn mkdir tmpdir`，这样会使得上一个`spawn`即`ssh`结束，那么你的`tmpdir`将在本机建立。当然实际情况下可能会要你确认`ssh key`，可以通过并行的`expect`进行处理，不多赘述。

## scp

### 单个任务

```shell
#!/usr/bin/expect
#file_name: remote_scp.exp
set timeout 15
set host [lindex $argv 0]
set username [lindex $argv 1]
set passwd [lindex $argv 2]
set src_file [lindex $argv 3]
set dest_file [lindex $argv 4]

spawn scp $src_file $username@$host:$dest_file

expect {
  	"(yes/no)?" {
      	send_user "send yes\n"		# 提示
      	send "yes\n"
      	expect {
          	"assword" {
          		send_user "send password: $passwd\n"
          		send "$passwd\n"
          	}
      	}
  	}
  	"*assword*" {
        send_user "send password: $passwd\n"
        send "$passwd\n"
  	}
  	eof {
    	send_user "send eof\n"
  	}
}
expect eof
```

注意代码刚开始的第一行，指定了expect的路径，与shell脚本相同，这一句指定了程序在执行时到哪里去寻找相应的启动程序。代码刚开始还设定了timeout的时间为10秒，如果在执行scp任务时遇到了代码中没有指定的异常，则在等待10秒后该脚本的执行会自动终止。

spawn代表在本地终端执行的语句，在该语句开始执行后，expect开始捕获终端的输出信息，然后做出对应的操作。expect代码中的捕获的(yes/no)内容用于完成第一次访问目标主机时保存密钥的操作。有了这一句，scp的任务减少了中断的情况。代码结尾的expect eof与spawn对应，表示捕获终端输出信息的终止。

### 批量任务

```shell
#!/bin/sh
list_file=$1
src_file=$2
dest_file=$3
 
cat $list_file | while    read line
do
     host_ip=`echo $line | awk '{print $1}'`
     username=`echo $line | awk '{print $2}'`
     password=`echo $line | awk '{print $3}'`
     echo "$host_ip"
     # 这里用到上面scp单任务示例
     ./expect_scp $host_ip $username $password $src_file $dest_file
done
```

很简单的代码，指定了3个参数：列表文件的位置、本地源文件路径、远程主机目标文件路径。需要说明的是其中的列表文件指定了远程主机ip、用户名、密码，这些信息需要写成以下的格式：

`IP username password`

中间用空格或tab键来分隔，多台主机的信息需要写多行内容。

这样就指定了两台远程主机的信息。注意，如果远程**主机密码中有“$”、“#”这类特殊字符**的话，在编写列表文件时就需要在这些特殊字符前**加上转义字符**，否则expect在执行时会输入错误的密码。

对于这个shell脚本，保存为batch_scp.sh文件，与刚才保存的expect_scp文件和列表文件(就定义为hosts.list文件吧)放到同一目录下，执行时按照以下方式输入命令就可以了。

## 响应chsh命令的脚本

### 功能描述

实现一些额expect脚本来与chsh命令进行交互, chsh脚本的使用方法入下.

#### chsh脚本描述

为用户chavez改变登录脚本, 简单使用如下:

```shell
$ chsh chavez
Changing the login shell for chavez
Enter the new value, or press return for the default
Login Shell [/bin/bash]: /bin/tcsh
$
```

可以看到该命令首先输出若干行提示信息并且提示输入用户新的登录shell。我们必须在提示信息后面输入用户的登录shell或者直接回车不修改登录shell。

### 脚本一: 自动执行chsh命令的Expect脚本

```shell
#!/usr/bin/expect
# Change a login shell to tcsh
set user [lindex $argv 0]
spawn chsh $user
expect "]:"
send "/bin/tcsh "
expect eof
exit
```

#### 说明

-   和其他脚本一样首行指定用来执行该脚本的命令程序，这里是`/usr/bin/expect`
-   程序第一行用来获得脚本的执行参数(其保存在数组$argv中，从0号开始是参数)，并将其保存到变量user中
-   第二个参数使用Expect的spawn命令来启动脚本和命令的会话，这里启动的是chsh命令，实际上命令是以衍生子进程的方式来运行的
-   随后的expect和send命令用来实现交互过程。
    -   脚本首先等待输出中出现`]:`字符串，一旦在输出中出现`chsh`输出到的特征字符串(一般特征 字符串往往是等待输入的最后的提示符的特征信息)。对于其他不匹配的信息则会完全忽略。
    -   当脚本得到特征字符串时，`expect`将发送`/bin/tcsh`和 一个回车符给`chsh`命令。最后脚本等待命令退出(`chsh`结束)，一旦接收到标识子进程已经结束的`eof`字符，`expect`脚本也就退出结束。

### 脚本二: 决定合适相应

管理员往往有这样的需求，希望根据当前的具体情况来以不同的方式对一个命令进行响应。我们可以通过后面的例子看到expect可以实现非常复杂的条件响应，而仅仅通过简单的修改预处理脚本就可以实现。

```shell
expect -re "\[(.*)]:"
if {$expect_out(1,string)!="/bin/tcsh"} {
	send "/bin/tcsh" 
}
send " "
expect eof
```

#### 说明

第一个`expect`命令现在使用了`-re`参数，这个参数表示指定的的字符串是一个正则表达式，而不是一个普通的字符串。对于上面这个例子里是查找一个左方括号字符(其必须进行三次逃逸(escape)，因此有三个符号，因为它对于expect和正则表达时来说都是特殊字符)后面跟有 零个或多个字符，最后是一个右方括号字符。这里.*表示表示一个或多个任意字符，将其存放在`()`中是因为将匹配结果存放在一个变量中以实现随后的对匹配结果的访问。

当发现一个匹配则检查包含在`[]`中的字符串，查看是否为`/bin/tcsh`。如果不是则发送`/bin/tcsh`给`chsh`命令作为输入，如果是则仅仅发送一个回车符。这个简单的针对具体情况发出不同相响应的小例子说明了expect的强大功能。

在一个正则表达时中，可以在`()`中包含若干个部分并通过`expect_out`数组访问它们。各个部分在表达式中从左到右进行编码，从1开始(0包含有整个匹配输出)。`()`可能会出现嵌套情况，这这种情况下编码从最内层到最外层来进行的。

### 脚本三: 使用超时

下一个expect例子中将阐述具有超时功能的提示符函数。这个脚本提示用户输入，如果在给定的时间内没有输入，则会超时并返回一个默认的响应。这个脚本接收三个参数：提示符字串，默认响应和超时时间(秒)。

```shell
#!/usr/bin/expect
# Prompt function with timeout and default.
set prompt [lindex $argv 0]
set def [lindex $argv 1]
set response $def
set tout [lindex $argv 2]
```

脚本的第一部分首先是得到运行参数并将其保存到内部变量中。

```shell
send_tty "$prompt: "
set timeout $tout
expect " " {
set raw $expect_out(buffer)
# remove final carriage return
set response [string trimright "$raw" " "]
}
if {"$response" == "} {set response $def}
send "$response
```

这是脚本其余的内容。可以看到send_tty命令用来实现在终端上显示提示符字串和一个冒号及空格。set timeout命令设置后面所有的expect命令的等待响应的超时时间为$tout(-l参数用来关闭任何超时设置)。

然后expect命令就等待输出中出现回车字符。如果在超时之前得到回车符，那么set命令就会将用户输入的内容赋值给变脸raw。随后的命令将用户输入内容最后的回车符号去除以后赋值给变量response。

然后，如果response中内容为空则将response值置为默认值(如果用户在超时以后没有输入或者用户仅仅输入了回车符)。最后send命令将response变量的值加上回车符发送给标准输出。

一个有趣的事情是该脚本没有使用spawn命令。 该expect脚本会与任何调用该脚本的进程交互。

如果该脚本名为prompt，那么它可以用在任何C风格的shell中。

```shell
% set a='prompt "Enter an answer" silence 10'
Enter an answer: test
% echo Answer was "$a"
Answer was test
prompt设定的超时为10秒。如果超时或者用户仅仅输入了回车符号，echo命令将输出
Answer was "silence"
```

### 脚本四: 一个更复杂的例子

下面我们将讨论一个更加复杂的expect脚本例子，这个脚本使用了一些更复杂的控制结构和很多复杂的交互过程。这个例子用来实现发送write命令给任意的用户，发送的消息来自于一个文件或者来自于键盘输入。

```shell
#!/usr/bin/expect
# Write to multiple users from a prepared file
# or a message input interactively
if {$argc<2} {
send_user "usage: $argv0 file user1 user2 ... "
exit
}
```

send_user命令用来显示使用帮助信息到父进程(一般为用户的shell)的标准输出。

```shell
set nofile 0
　　# get filename via the Tcl lindex function
　　set file [lindex $argv 0]
　　if {$file=="i"} {
　　set nofile 1
　　} else {
　　# make sure message file exists
　　if {[file isfile $file]!=1} {
　　send_user "$argv0: file $file not found. "
　　exit }}
```

这部分实现处理脚本启动参数，其必须是一个储存要发送的消息的文件名或表示使用交互输入得到发送消的内容的"i"命令。

变量file被设置为脚本的第一个参数的值，是通过一个Tcl函数lindex来实现的，该函数从列表/数组得到一个特定的元素。[]用来实现将函数lindex的返回值作为set命令的参数。

如果脚本的第一个参数是小写的"i"，那么变量nofile被设置为1，否则通过调用Tcl的函数isfile来验证参数指定的文件存在，如果不存在就报错退出。

可以看到这里使用了if命令来实现逻辑判断功能。该命令后面直接跟判断条件，并且执行在判断条件后的{}内的命令。if条件为false时则运行else后的程序块。

```shell
　　set procs {}
　　# start write processes
　　for {set i 1} {$i<$argc}
　　{incr i} {
　　spawn -noecho write
　　[lindex $argv $i]
　　lappend procs $spawn_id
　　}
```

最后一部分使用spawn命令来启动write进程实现向用户发送消息。这里使用了for命令来实现循环控制功能，循环变量首先设置为1，然后因 此递增。循环体是最后的{}的内容。这里我们是用脚本的第二个和随后的参数来spawn一个write命令，并将每个参数作为发送消息的用户名。 lappend命令使用保存每个spawn的进程的进程ID号的内部变量$spawn_id在变量procs中构造了一个进程ID号列表。

```shell
　　if {$nofile==0} {
　　setmesg [open "$file" "r"]
　　} else {
　　send_user "enter message,
　　ending with ^D: " }
```

最后脚本根据变量nofile的值实现打开消息文件或者提示用户输入要发送的消息。

```shell
set timeout -1
while 1 {
if {$nofile==0} {
if {[gets $mesg chars] == -1} break
set line "$chars "
} else {
expect_user {
-re " " {}
eof break }
set line $expect_out(buffer) }
foreach spawn_id $procs {
send $line }
sleep 1}
exit
```

上面这段代码说明了实际的消息文本是如何通过无限循环while被发送的。while循环中的 if判断消息是如何得到的。在非交互模式下，下一行内容从消息文件中读出，当文件内容结束时while循环也就结束了。(break命令实现终止循环) 。

在交互模式下，expect_user命令从用户接收消息，当用户输入ctrl+D时结束输入，循环同时结束。 两种情况下变量$line都被用来保存下一行消息内容。当是消息文件时，回车会被附加到消息的尾部。

foreach循环遍历spawn的所有进程，这些进程的ID号都保存在列表变量$procs中，实现分别和各个进程通信。send命令组成了 foreach的循环体，发送一行消息到当前的write进程。while循环的最后是一个sleep命令，主要是用于处理非交互模式情况下，以确保消息 不会太快的发送给各个write进程。当while循环退出时，expect脚本结束。

# END

## 参考

[expect学习笔记及实例详解](http://blog.itpub.net/27042095/viewspace-745589/) 

[linux expect的使用详解](http://www.2cto.com/os/201305/209909.html)