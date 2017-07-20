# expect笔记

## 概述

我们通过Shell可以实现简单的控制流功能，如：循环、判断等。但是对于需要交互的场合则必须通过人工来干预，有时候我们可能会需要实现和交互程序如telnet服务器等进行交互的功能。而Expect就使用来实现这种功能的工具。

Expect是一个免费的[编程](http://www.2cto.com/kf)工具语言，用来实现自动和交互式任务进行通信，而无需人的干预。Expect的作者Don Libes在1990年 开始编写Expect时对Expect做有如下定义：Expect是一个用来实现自动交互功能的软件套件 (Expect [is a] software suite for automating interactive tools)。使用它[系统](http://www.2cto.com/os/)管理员 的可以创建脚本用来实现对命令或程序提供输入，而这些命令和程序是期望从终端（terminal）得到输入，一般来说这些输入都需要手工输入进行的。 Expect则可以根据程序的提示模拟标准输入提供给程序需要的输入来实现交互程序执行。甚至可以实现实现简单的BBS聊天机器人。

Expect是不断发展的，随着时间的流逝，其功能越来越强大，已经成为系统管理员的的一个强大助手。Expect需要Tcl编程语言的支持，要在系统上运行Expect必须首先安装Tcl。

## 工作原理

从最简单的层次来说，Expect的工作方式象一个通用化的Chat脚本工具。Chat脚本最早用于UUCP网络内，以用来实现计算机之间需要建立连接时进行特定的登录会话的自动化。

Chat脚本由一系列expect-send对组成：expect等待输出中输出特定的字符，通常是一个提示符，然后发送特定的响应。例如下面的 Chat脚本实现等待标准输出出现Login:字符串，然后发送somebody作为用户名；然后等待Password:提示符，并发出响应 sillyme。

引用：Login: somebody Password: sillyme

　　这个脚本用来实现一个登录过程，并用特定的用户名和密码实现登录。

　　Expect最简单的脚本操作模式本质上和Chat脚本工作模式是一样的。

## 语法

### spawn

`spawn`: 后面加上需要执行的shell 命令，比如说`spawn sudo touch testfile`.

spawn命令是Expect的初始命令，它用于启动一个进程，之后所有expect操作都在这个进程中进行，如果没有spawn语句，整个expect就无法再进行下去了，使用方法就像下面这样：

` spawn ssh root@192.168.0.1`

在spawn命令后面，直接加上要启动的进程等信息。当然，如果真的不要spawn过程也没有关系，虽然这样就没有办法单独执行，但是这个脚本可以与任何调用它的进程进行交互。

除此之外，spawn还支持其他选项：

-   `-open`: 启动文件进程，具体说明请参照下面的选项部分
-   `-ignore`: 忽略某些信号，具体说明请参照下面的选项部分

### expect

`expect`: 只有`spawn`执行的命令结果才会被`expect`捕捉到，因为`spawn`会启动一个进程，只有这个进程的相关信息才会被捕捉到，主要包括：标准输入的提示信息，`eof`和`timeout`.

使用方法: `expect 表达式 动作 表达式 动作...`

expect命令用于等候一个相匹配的输出，一旦匹配就执行后面的动作，这个命令接受几个特有参数，用的最多的就是-re，表示使用正则表达式的方式匹配，使用起来就像这样：

```shell
spawn  ssh  root@192.168.0.1
expect  "password:" {exp_send  "word\r"}
```

从上面的例子可以看出，expect是依附与spawn命令的，当执行ssh命令后，expect就匹配命令执行后的关键字：password:，如果匹配到关键字就会执行后面包含在{}括号中的exp_send动作，匹配以及动作可以放在二行，这样就不需要使用{}括号了，就像下面这样，实际完成的功能与上面是一样的：

```shell
spawn  ssh  root@192.168.0.1
expect  “password:”
exp_send  “word\r”
```

expect命令还有一种用法，它可以在一个expect匹配中同时匹配多个关键字，只需要将关键字放在一个大括号中就可以了：

```shell
spawn  ssh  root@192.168.0.1
expect  {
    -re  “password:” {exp_send  “word\r”}
    -re  “TopsecOS#” { }
}
```

上面的例子中，在一个expect匹配中可以匹配二个不同情况，如果发现有password:字符就执行后面的动作，而发现的是另外一个`TopsecOS#`时，因为后面的动作为空，就会退出这个expect动作，在这些动作中也有很多参数，我们在后面来慢慢介绍。

上面我们看到了一种【表达式-动作】模式，还有人喜欢使用另一种格式，就像下面这样：

```shell
spawnssh root@192.168.0.1
expect –re "password:" {
	exp_send "word\r"
} –re "TopsecOS#" {  
}
```

这种格式的好处是减少了一次缩进，不过看起来就没有那么清晰了，喜欢哪一种可以自己决定。

### expect_before

在这个语句以下的所有expect语句之前，首先做一次匹配，使用这个命令需要小心，首先来看例子：

```shell
expect{
    eof    eofproc
    "login:"     {send "$user\r"}
}
expect {
    eof    eofproc
    "password" {send "$password\r"}
}
expect {
    eof    eofproc
    "$prompt"     {send "$cmd\r"}
}
```

上面的例子中，每一个expect都有一个eof过程，而且都是首先检查有没有eof事件，然后再往下检查其他的事件。对于这种情况，我们可以用下面的语句来代替：

```shell
expect_before  eof eofproc
expect "login:"     {send "$user\r"}
expect "password"	{send "$password\r"}
expect "$prompt"    {send "$cmd\r"}
```

### send和send_user

-   `send` 和`send_user`: `send`会将`expect`脚本中需要的信息发送给`spawn`启动

    的那个进程，而`send_user`只是回显用户发出的信息，类似于`shell`中的`echo`而

    已

## 参考

[linux expect的使用详解](http://www.2cto.com/os/201305/209909.html)

[expect学习笔记及实例详解](http://blog.itpub.net/27042095/viewspace-745589/) 