[TOC]

# expect

能与交互式程序进行“可程序化”会话的脚本语言。

## 命令选项概述

`expect [ -dDinN ] [ -c cmds ] [ [ -[f|b] ] cmdfile ] [ args ]`

## 概述

Expect是一种能够按照脚本内容里面设定的方式与交互式程序进行“会话”的程序。根据脚本内容，Expect可以知道程序会提示或反馈什么内容以及什么是正确的应答。它是一种可以提供“分支和嵌套结构”来引导程序流程的解释型脚本语言。另外，还可以在随时需要的时候把控制权交给用户，然后再还给脚本。

ExpectK是Expect和Tk的混合体。它就按照Expect和Tk的方式运行。Expect也可以直接嵌入到C或是C++程序中（这种情况是不涉及Tcl解释）。

Expect还能同时和多个程序交互。

### 可以做什么

- 让你的计算机呼叫你，这样你可以不用付呼叫费。
- 启动一个游戏（例如：rogue），如果不是最佳配置，则一直重新启动，直到是最佳配置为止。然后把控制权转交给你。
- 运行fsck的时候，用”yes”或是”no”来回应fsck的交互问题。在没有预设答案标准的情况下把控制权返还给你。
- 连接到另一个网络或是BBS站点，自动收取你的邮件，就像邮件是发往你的当地系统一样。
- 在执行rlogin，telnet，tip，su，chgrp等等命令的时候保存“环境变量”，“当前目录”或是其他一些信息。

有很多原因致使Shell不能完成这样的任务（你自己可以试试看）。而这一切对于Expect来说都是可以的。一般情况下，当一个程序需要程序与用户进行交互的时候就需要用到Expect。还需要的一个前提是这种交互必须能程序化(例如：循环结构，选择结构等等，个人认为必须是有规律可循的)。如果需要的话，Expect还能把控制权返还给用户。同样，用户也可以在任何时候把控制权还给脚本程序。

## 用法

Expect通过读取cmdfile（命令文件）来执行一系列指令。只要系统支持`#!`，在Script脚本文件的首行标明

`#!/usr/local/bin/expect –f` 

并赋予脚本文件可执行权限，执行脚本文件就可以(隐含方式或是默认)调用Expect。当然，上面的路径必须正确地指明Expect解释程序的位置，`/usr/local/bin`只是一个例子。

- `-c` 选项：用来标明需要在执行脚本内容之前来执行的命令，这条命令(-c选项后的命令)应该用引号括起来，以免在执行时被shell分开解释。`-c`选项可能会被反复使用。多条命令可以使用同一个`-c`，命令之间需要用分号隔开。这些命令会按照它们出现的先后顺序执行。（在`Expectk`中，`-c`相当于`-command`）。

- `-d` 选项：允许输出调试性信息。这些信息主要报告像`expect`和`interact`等命令执行时的内部行为。这个选项与写在脚本开头的`exp_internal 1`具有同样的效果，同时还会打印出Expect的版本。（strace命令用在跟踪变量声明，trace命令用于跟踪变量的赋值）(在`Expectk`中，`-d`相当于`-diag`)

- `-D`选项：开启交互调试器。后面必须跟有一个整数值作为参数，当值为非零或是按下CTRL+C的时候（或是遇到断点，或是在脚本中恰好出现其他的调试语句），调试器会在进行下一次Tcl Procedure前取得控制权。想了解更多信息请参见README文件或是下面的SEE ALSO。（在`Expectk`中，这个选项相当于`-Debug`）

- `-f` 选项：指明从哪个文件中读取命令。这个选项是可选的，因为只有当使用`#!`时它才有可能被用到。而其他选项可以写在命令行中。（在`Expectk`中，它相当于`-file`）。

  默认情况下，命令文件是全部读入内存一并执行的。但有些时候需要每次只读一行。例如：`stdin`（标准输入）就是这样读取的。如果强制任意文件以这种方式(每次读一行)执行的话就使用`-b`选项。（在`Expectk`中，它相当于`-buffer`）。

  如果”-“被一个文件名替代，那么脚本就会用读指定文件的方式来替代从标准输入读的方式。（例如：”。/ -“就表示从一个名为”-”的文件中读所需的信息）。

- `-i` 选项：使`Expect`能交互式的提示输入命令，而不是从文件中读取。在遇到文件尾或是执行了`exit`命令时，提示输入命令终止。要了解更多信息请参见下面的`interpreter`。`-i` 选项是假设既不是从一个命令文件读，也没有使用`-c`选项。（在`Expectk`中，它相当于`-interactive`）

- `--` ： 是用来为划定选项尾的。当你需要像使用选项一样传一个参数，但希望这个参数不要被当作选项解释时，就需要用到这个选项。当阻止其他选项时，可以把它放在`#!`行中。

  例如：下面的例子会让所有参数（包括脚本文件名）都存储在argv中

  `!/usr/local/bin/expect --`

  **注意**：当在`#!`行中使用参数时，必须遵守getopt(3)和execve(2)的规定。

  `$exp_library`下如果有`expect。rc`这个文件的话，它会自动被加载为资源文件(应该是类似于标准配置文件，像用户根目录下的`。bash_profile`文件一样)。除非使用`-N`选项取消自动加载。（在`Expectk`中，它相当于`-NORC`）。这个文件被加载后，紧接着用户根目录下的`。expect。rc(~/。expect。rc)`会被加载。除使用-n选项取消。如果定义了环境变量`DOTDIR`，那么它被认为是存放有`。expect。rc`文件的目录。然后从这个目录中读取`。expect。rc`文件。（在`Expectk`中，它相当于`-norc`）。这些加载配置文件的动作是出现在执行完-c选项指定的命令之后。

- `-v` 选项：用来打印出版本号，然后退出。（在`Expectk`中的相应选项是`-version`）

可选的参数汇成一列，存放在变量`argv`中。`Argc`被初始化为`argv`的长度（变量个数）。`Argv0`被设置为脚本名称（or binary if no script is used）。例如：下面的例子打印出脚本的名称和前三个参数。

`Send_user “$argv0 [lrang $argv 0 2 ]\n”`

## 命令

`Expect`使用`Tcl`语言(Tool Command Language)。`Tcl`提供诸如流控制，表达式值和一些其他的特性。像递归调用，定义函数等等。在这里用到的没有说明的命令都是`Tcl`命令。

`Expect`支持一些额外的命令。下面具体描述。除非另外声明，否则命令返回空字符串。命令按字母顺序排序，这样便于查找。仅管如此，初学者还是觉得按照`spawn ， send ， expect ， interact`这种方式来读比较容易。

**注意**：`Exploring Expect`这本书中提供了关于`Expect`和`Tcl`的介绍。这本Manpage手册中也提供了一些例子。但数量有限，因为这本是做为入门的教材手册使用的。

在本手册中，以E开头的是指Expect程序，小写e开头的是指expect命令。

### close

`close [-slave][-onexec 0|1] [-i spawn_id] `

关闭与当前进程的连接。

- 大多数交互程序会在它们的`stdin`(标准输入)中检测到`EOF`(文件尾)，然后退出。所以通常`close`也有能力杀死进程。
- `-i`选项：指定了要杀死的对应于`spawn_id`的进程`。expect`和`interact`都能检测到当前程序的退出，并隐含的执行一个关闭。如果你通过执行`exec kill $pid`来杀死进程的话，那么你就需要再显式的调用一下`close`。
- `-onexec`选项：用来确定`spawn_id`在开始新的`spawned process`(我将其翻译为监测进程)时是被关闭还是要被覆盖。如果想保持这个`spawn_id`打开的话，那么后面的参数需要设为0。一个非零值将会使`spawn_id`关闭，并可以将这个`spawn_id`用于新的进程(默认行为)。
- `-slave`选项：是用来关闭从属进程。(参见`spawn -pty`)。如果在连接中止的时候，从属进程还打开的话，那么它将自动关闭。不管进程是显式的调用或是隐式的被中止，你都需要调用`wait`命令来清理进程执行的残余。`Close`不会调用`wait`。因为在关闭进程的时候，并不能保障它“正常退出”(个人认为，可能是指退出时做相应的清理工作)。要了解更多的信息，请参见`wait`命令。

### debug

`debug [[-now] 0|1]`

控制Tcl调试器以步进方式执行语句，设置断点等等。

在没有参数的情况下，如果调试器没有运行，返回1，否则返回0。用1做参数时，启动调试器，用0做参数时，停止调试器。如果连同-now一起使用的话，调试器将立即启动（也就是说，在debug命令当中）。否则调试器会在执行下一条语句的时候启动。

调试命令不会改变任何的traps。可以参见以-D选项启动Expect（参见上面）

要了解更多关于debugger的内容，请参见README文件和下面的SEE ALSO。

### disconnect

从终端关闭与一个克隆进程的连接，但让它在后台继续运行。这个进程将被赋予为单独的进程组（如果可能的话）。标准`I/O`被重定向到`/dev/null`。下面的代码使用`disconnect`命令使脚本在后台继续运行。

```tcl
if [fork]!=0 exit
	disconnect
```

下面的脚本需要读取一个密码，然后每小时执行一次，每次执行的时候都要求输入密码。脚本提供了所需的密码，所以你只需输入一次就可以了。（参见能关闭回显的终端命令）。

```shell
send_user "password?\ "
expect_user -re "(.*)\n"
for {} 1 {} {
	if [fork]!=0 {sleep 3600;continue}
	disconnect
	spawn priv_prog
	expect Password:
	send "$expect_out(1，string)\r"
	...
	exit
}
```

用这种方式，而不是用Shell后台方式来执行程序的好处是，用`disconnect`可以在关闭前保存终端参数，然后把它们应用于新的终端中。如果使用`&`的话，`Expect`没有机会读取终端参数，因为在`Expect`取得控制权的时候，终端已经退出了。

### exit

`exit [-opts] [status]`

使Expect退出或是准备退出。

- `-onexit`选项：使下一个参数做为退出的句柄被使用。没有参数时，返回当前的退出句柄。`-noexit`选项使`Expect`准备退出，而不是把控制权暂时返还给系统。用户定义的退出句柄和Expect内部的句柄都是以同样的方式被返回。接下来将不再执行Expect命令。这在Tcl扩展环境下执行Expect时非常有用。保留当前的解释器(在Tk环境中的主窗口)以清除其他的Tcl扩展。如果Expect再次调用exit(这有可能发生)，不会返回句柄。退出时，全部连接将关闭，关闭的动作会被监测的进程检测为“到文件尾”。exit只按照正常exit(2)的中的语句来执行，不会引发其他的动作。因此监视的进程如果没有检测到“到文件尾”的话，会继续执行。(能检测更多的情况是很重要的。例如：什么样的信号会发给监测进程，但这些是由系统决定的，它们放在exit(3)的文档中)。如果被监测的进程继续运行的话，将会被init继承。当前的状态信息将做为Expect的退出信息返回（如果没有指明的话，返回信息为0）。退出命令在脚本程序执行的最后才自动隐含的调用。

### Exp_continue

`Exp_continue [-continue_timer]`

这个命令可以使expect继续执行而不是正常的返回。默认情况下，`exp_continue`会重高超时时钟，`-continue_timer`选项会阻止时钟重新计数(连续计数)。

### Exp_internal

`Exp_internal [-f file] value`

如果是value非零的话，使接下来的命令将调试信息输出到Expect和标准错误输出。如果是0的话，输出的信息将会被屏蔽。调试信息包括收到的每条信息和每次尝试用当前输出与脚本中的模式相匹配的信息。如果设置了输出文件，那么正常的和调试的信息都会被写到这个文件当中。(忽略上面value选项的值)。任何之前打开的调试输出文件将会被关闭。-info选项使exp_internal返回最近关于non-info参数的描述。

### Exp_open

`Exp_open [args][-I spawn_id]`

它返回对应于原始spawn id的文件描述符。

这样这个文件描述符就可以被使用了，就好像这个文件是被Tcl的open指令打开的一样。(这个spawn id将不再使用，wait指令将不能用在这个进程)

`-leaveopen`选项使`spawn id`保持打开，以便供Expect命令使用。

### Exp_pid

`Exp_pid [-i spawn_id]`

它将返回对应于当前被跟踪进程的ID。如果使用-i选项，将返回对应于指定的spawn id的进程ID。

### Exp_send

它是send的别称

### Exp_send_error

它是Send_error的别称

### Exp_send_log

它是Send_log的别称

### Exp_send_tty

它是Send_tty的别称

### Exp_send_user

它是Send_user的别称

### Exp_version

`Exp_version [[-exit] version] `

它用于确保脚本程序与当前的Expect兼容。在没有参数的情况下，返回当前Expect的版本。这个版本就会编译到脚本中。如果你确切的知道你的脚本程序不需要最新版本的特性，可以指定一个以前的版本。

版本号由三个由句点分隔的数字组成。第一个是主序号。对应某一主序号版本的Expect写的脚本程序，在不同主序号版本的Expect环境下基本不能正常运行。exp_version在主版本不同的情况下会返回一个错误。第二个数字是次版本号。编写脚本的Expect的次版本号如果比当前的Expect大的话，可能会用到一些新的特性，在当前的环境下可能不能正常运行。exp_version会在当主序号相同，但次序号比当前Expect版本大的时候返回一个错误信息。第三个数字在Expect的版本比较中没有多大作用。它只是当发行版有任何变化的时候会增加。比如说增加一些新的文档或是做了优化。当升级到一个新的次版本号时，这个数字会被初始化为零。

如果使用了`-exit`选项，Expect会在当前的版本过期的时候打印一个错误信息，然后退出。

### expect

`expect [[-opts] pat1 body1] ... [-opts] patn [bodyn]`

等待直到被监视进程的输出与设定的模式相匹配，或是一个指定的时间过后，或是遇到文件尾。如果最后的body是空的，那么它将被忽略。

最近的expect_before设定的模式会在其他模式之前被隐含地使用。最近的expect_after设定的模式将在所有其他模式匹配完后才被调用。

如果整个Expect命令的参数超过一行，这个参数可能被分为多行，各行之间用一个”\”连接，以防被分开解释。在这种情况下，Tcl解释器通常会置换掉”\”。

如果一个模式设定为eof，则相应的语句被在当达到文件尾的时候执行。如果一个模式设定为timeout，那么相应的语句会在超时时执行。如果没有设定timeout对应的执行语句，将会在timeout时隐含执行空指令。即不执行任何语句。默认的超时时钟设的是10秒，但可以自己设定。通过”set timeout 30”，可以将超时时钟设定为30秒。如果设定为-1的话，那么超时时钟将是无穷大，如果一个模式设定为default，那么相应的语句将会在遇到文件尾或是超时时执行。如果触发了相应的模式，则此模式对应的语句将会被执行。Expect返回语句执行的结果(或是在没有模式触发的情况下是空字符串)。在多种模式匹配的情况下，第一个匹配的模式对应的语句将被执行。

每次出现新的输出的时候，它们都会依次匹配相应的模式。因此，如果你想[测试](http://lib。csdn。net/base/softwaretest)匹配是否成功，可以把最后一个模式设定为肯定会出现的东西，例如一个提示符。在没有提示符的情况下，你需要使用一个timeout模式。模式被定义为三种类型。默认情况下，模式被定义为Tcl的string match(字符串匹配)指令。(这些模式很像C Shell中的正则表达式，它们通常被用来做模糊匹配)。`-gl`选项保护那些可能被认为是Expect的选项的模式匹配字符串。以`-`开头的所有模式匹配字符串都需要这样保护起来。(因为默认情况下，以`-`开头的字符串都被保留起来做为将来的选项)。

例如:下面的代码期望一个正确的登录。(注意下面的abort是一个已经在脚本的其他位置定义好的函数)

```shell
expect {
    busy 				{puts busy\n ; exp_continue}
    failed 				abort
    "invalid password" 	 abort
    timeout 			abort
    connected
}
```



在第四行需要使用引号，因为它包含了一个空格。如果不用引号把模式括起来的话，它会被分别解释为模式与执行函数。执行同样动作的其他模式同样需要把执行函数的名称写出来(像其他两个执行”abort”函数的模式)，如果使用regexp-style模式的话(看下面的例子)，更多关于建立glob-style模式的信息请参见Tcl手册。regexp模式以`-re`开头。上面的例子可以用regexp模式改写成下面的代码:

```shell
expect {
    busy 		{puts busy\n ; exp_continue}
    -re "failed|invalid password" abort
    timeout 	abort
    connected
}
```

两种类型都可以被正确匹配。这就是说设置的类型可以不用是整个字符串。可以只匹配头部或是尾部（就假设其他部分也匹配一样）。用`^`来匹配字符串头部。用`$`来匹配字符串尾部。如果你不希望等待直到字符尾，你可以在监视进程回显字符的中间时刻结束响应。虽然仍能打印出正确的结果，但最后的输出可能看上去有点混乱。因此如果能够详细描述预期的字符串尾部的话，还是鼓励使用`$`来匹配尾部。在许多编辑器中，`^`和`&`分别表示首行和尾行。因为Expect不是基于行缓冲的程序。所以这两个字符分别用来表示当前匹配缓冲区中的头数据和尾数据。

`-ex`选项: 使模式进行精确匹配。这时，不对`*`，`^`等字符进行解释（但还是要遵守`Tcl`的规则）。Expect patterns are always unanchored。

`-nocase`选项: 使输出中的大写字符也按小写字符匹配。模式匹配字符串本身改变。在读取输出进行匹配时，超过2000字符将会强制将前面的字符丢弃。这个数目可以通过match_max来改变。（但太大的数目会降低匹配的效率）。如果`patlist`是`full_buffer`，则在收到`match_max`个字节而没有相应的模式匹配成功时，执行full_buffer所对应的语句。不管是否使用了关键字`full_buffer`，丢弃的字符都会被写到`expect_out`缓冲区中。如果`patlist`是关键字`null`。并且空是有效字符（通过`remove_nulls`指令访问），如果输出是一个单个的ASCII码0，那么`null`相对应的语句将被执行。通过`glob`或是`regexp`模式是不能来匹配0字符。

在匹配字符串时（或是遇到文件尾`EOF`，或是缓冲区满`full_buffer`），任何匹配的或是前面没有匹配的输出都会被保存在expect_out缓冲区中。匹配到的9个字符分别被放到expect_out(1，string)至expect_out(9，string)中。如果在模式前使用了-indices选项，那么，这10个字符的开始字符和结尾字符在字符串中的位置被分别存放在变量expect_out(X，start)和expect_out(X，end)中。其中X是自然数（应该是0到9）。0（expect_out(0，*)）是指整个匹配的字符串，它可以用于glob模式，也可以用于regexp模式。

例如：如果一个进程的输出为`abcdefgh\n` , 那么

`expect "cd"`

执行结果和下面的代码执行结果是一样的:

```shell
set expect_out(0，string) cd
set expect_out(buffer) abcd
```

`efgh\n`被丢弃到输出缓冲区了。如果一个进程的输出是`abbbcabkkkka\n`，那么:

`expect –indices –re "b(b*).*(k+)"`

的执行结果和下面语句的执行结果是相同的。

```shell
set expect_out(0，start) 1
set expect_out(0，end) 10
set expect_out(0，string) bbbcabkkkk
set expect_out(1，start) 2
set expect_out(1，end) 3
set expect_out(1，string) bb
set expect_out(2，start) 10
set expect_out(2，end) 10
set expect_out(2，string) k
set expect_out(buffer) abbbcabkkkk

```



`a\n`被丢弃了输出缓冲区中了。含有`*`(和`-re ".*"`)的模糊匹配的模式会清空输出缓冲区，不再读取从进程中输出的字符。一般情况下，匹配的输出会被Expect的内部缓冲区丢弃。可以通过在模式前加上`-notransfer`选项来避免被丢弃。这个选项在实验时非常有用(为了方便，可以简写成`-not`)。与匹配输出相对应的`spawn id`被存储在`expect_out(spawn_id)`中。

`-timeout` 选项: 使得Expect使用选项后面的数值做为超时时间，而不是timeout变量中设置的时间。

默认情况下，设定的模式只与当前进程的输出进行匹配。`-i`选项使得指定`spawn_id`或是`spawn_id`列的输出与下面列出的所有模式进行匹配(直到下一个`-i`选项为止)。`spawn_id`列要么是用空格分隔的一列`spawn_id`，要么是用变量存储的这要一列`spawn_id`。例如，下面的例子中，当前进程与`"connected"`进行匹配，由变量`$proc2`指定进程与`"busy"`，`"failed"`，`"invalid passowrd"`进行匹配。

```shell
expect {
    -i $proc2 busy {puts busy\n ; exp_continue}
    -re "failed|invalid password" abort
    timeout abort
    connected
}
```

全局变量`any_spawn_id`的值是在当前`expect`中所有`-i`选项定义的`spawn_id`进程列的总和。它用来使这些`spawn_id`进程列与模式进行匹配。在一些`-i`选项中可能只给出了`spawn_id`列，但没有给出相应匹配模式。(例如，`-i`选项紧接下来就是另一个`-i`选项)。那么这些`spawn_id`列将会去匹配与`any_spawn_id`相对应的模式。

`-i`选项还可以定义一个全局变量，里面存储着`spawn_id`列。当变量内容发生变化时，它会被重新读取。这样就可以在程序执行的时候改变I/O源。以这种方式提供的`spawn_id`被称为`"indirect spawn_id"`。

`Break`和`continue`使流程(例如:for结构，proc函数)按照正常的顺序执行。`exp_continue`使`expect`继续执行而不是像通常一样返回。这对于避免`explicit loops`(不执行以后有语句，直接进入下一次循环)和重复的语句很有用。下面的例子是一个自动`rlogin`的代码片断。`exp_continue`的使用避免了在`rlogin`揭示输入密码的时候的再写一个重复的`expect`语句。(需要等待第二次提示)

```shell
expect {
    Password: {
        stty -echo
        send_user "password (for user) on host: "
        expect_user -re "(。*)\n"
        send_user "\n"
        send "$expect_out(1，string)\r"
        stty echo
		    exp_continue
    } incorrect {
        send_user "invalid password or account\n"
        exit
    } timeout {
        send_user "connection to $host timed out\n"
        exit
    } eof {
        send_user \
        	"connection to host failed: $expect_out(buffer)"
        exit
    } -re $prompt
}
```

例如，下面的代码使用户可以在任务完全自动化的情况下，还能引导人机交互。这种情况下，终端被设置成原始状态。如果按下”+”，那么一个变量的值增加，如果按下”P”，那么向进程发送几个回车符，或是以其它的方式回应一下。如果按下”i”，那么用户就会从脚本那儿把控制权收回，来与进程进行交互。在每个情况下，exp_continue都使在执行完当前的动作之后，继续执行模式匹配。

```shell
stty raw -echo
expect_after {
    -i $user_spawn_id
    "p" {send "\r\r\r"; exp_continue}
    "+" {incr foo; exp_continue}
    "i" {interact; exp_continue}
    "quit" exit
}
```



默认情况下，exp_continue会重置超时时间。如果以带有-continue_timer选项的方式执行exp_continue的话，超时时钟不会重新启动。

### Expect_after

`Expect_after [expect_args]`

它和expect_before的工作方式相同。在expect和expect_after能同时匹配的情况下。进程与expect命令下面的模式进行匹配。想了解更多的信息请参见expect_before。

### Expect_background

`Expect_background [expect_args]`

它和expect有一样的参数列表。但不同的是它是立即返回。一旦有新的输入到达时就开始进行模式匹配，timeout和default两个模式对于expect_background来说没有意义，它们会被隐含忽略。否则，expect_background会像expect一样调用expect_before和expect_after的模式匹配。

当expect_background在执行模式匹配时，对应于这个spawn_id的后台进程将被阻塞。当执行完成时，后台进程被解开。在后台进程被阻塞期间，还可以在前台以同样的spawn_id执行一个expect脚本。但在非阻塞情况下是不可能这样做的。在用同一个spawn_id声明一个新的expect_background时，前一个就会被自动删除。声明一个没有匹配模式的expect_background将会使相应的spawn_id失去在后台匹配模式的能力。

### Expect_before

`Expect_before [expect_args]`

它和expect具有相同的参数列表。但不同的是它立即返回。相同spawn_id最近的expect_before下的匹配模式会自动隐含的加载到下面的expect命令中。如果其中一个模式匹配成功了，就好像匹配的模式是列在expect命令本身下面一样。如果expect_before和expect的模式同时匹配，那么将使用expect_before。如果没有相应的匹配列出来，那么这个spawn_id将不进行任何模式匹配的动作。

除非使用-i选项强制声明，否则expect_before的模式将与执行expect_before命令时对应的spawn_id的进程输出进行匹配(而不是有模式匹配成功时的spawn_id)。-info选项会返回当前模式的详细信息。默认情况，它会报告当前的spawn_id的信息。也可以通过指定spawn_id来显示指定spawn_id的信息。

例如: `expect_before –info –I $proc`

这样最多返回一个spawn_id的详细信息。 The flag -indirect suppresses direct spawn ids that come only from indirect specifications。

-all选项使expect_before报告所有spawn_id的信息，而不是单个spawn_id的信息。

### expect_tty

`expect_tty [expect_args ]`

和expect的用法很像，但它是从/dev/tty读取字符串（例如：用户的击键）。默认情况下，读是工作在精加工缓冲模式下的。因此，每行之后必须以回车结尾，这样expect才能分别识别它们。读模式（例如行缓冲，等等）可以通过stty命令更改（参见下面的stty命令）

### expect_user

`expect_user [expect_args]`

和expect的用法很像，但它是从stdin(标准输入)读取字符串（例如：用户的击键）。默认情况下，读是工作在精加工模式下的。因此，每行之后必须以回车结尾，这样expect才能分别识别它们。读模式（例如行缓冲，等等）可以通过stty命令更改（参见下面的stty命令）。

### fork

创建一个新进程。这个新进程是当前进程的完整拷贝。成功时，会返回0给新进程，返回新进程的ID给当前进程。失败时（失败的原因可能是资源匮乏，如交换分区，内存不足等），返回一个-1给当前进程，没有新进程创建。复制的新进程和它的父进程一样通过exit命令退出。复制的新进程允许写日志文件。如果不屏蔽大多程序的debugging（调试）和logging（写日志）功能，结果（个人认为：输出结果或是日志）看起来会显得有点混乱。在多个用户的情况下，即使是很短暂的pty执行结果，看起来也会很让人混乱迷惑。因此，在监视某个进程(个人认为是执行spawn)之前执行fork更好一点。

### interact

`interact [string1 body1] ... [stringn [bodyn]]`

返回当前进程的控制权给用户。所以击键会被传给当前进程（就像平时操作一样）。当前进程的stdout和stderr也会返回（个人认为：可能在脚本执行时，标准输出和标准错误输出是被重定向到Expect的，因为执行spawn之后，expect会等待进程的输出，包括错误输出）。String-body被指定为参数。在这种情况下，当有指定的string输入时，对应的body就会被执行（默认情况，string不会被传给当前进程）。如果没有最后的body部分，那么将执行interact命令。如果整个interact语句参数过长，超过一行，这些参数会用反斜线连接，分隔在多行，这样避免了语句在执行时被隔断。这种情况下，在Tcl进行语法解释的时候会忽略这些反斜线，把这多行做为一条语句来执行。例如，下面的代码举例说明了以string-body方式执行interact命令。String-body是这样设定的：当你按下Ctrl+Z时，Expect 将挂起，按下Ctrl+A时，用户将会看到屏幕显示“you typed a control A”，并且也向当前进程发送一个Ctrl+A。当用户按下$时，用户会看到屏幕上显示系统日期。按下Ctrl+C 时Expect将退出。如果输入”foo” ，用户将在屏幕上看到“bar”，如果输入~~，那么Expect解释器交互执行。

```shell
set CTRLZ \032
interact {
    -reset $CTRLZ {exec kill -STOP [pid]}
    \001 	{send_user "you typed a control-A\n";
    			send "\001"
    		}
    $ 		{send_user "The date is [exec date]。"}
    \003 	exit
    foo 	{send_user "bar"}
    ~~
}
```

在string-body中，字符是按string在string-body中出现的顺序匹配的。

在不清楚余下的字符是什么的情况下，只是部分匹配的字符是不会被发送到当前进程的。如果在获得了余下的字符之后，整个字符串没有相应的string-body可以匹配（也就是说整个字符串在string-body中，没有对应相同的string），除了上面说的匹配字符外，也没有其他更多的匹配（个人理解：比如整个字符xxxbbccada。第一次提到的匹配字符xxxbb，string-body中有两个对应的string：string1=xxxbb，string2=xxxbbcc，那么也就是说整个字符是没有相应的string与之匹配，如果只有string1，没有string2，那也就是“没有更多的匹配”，只有xxxbb会发送到当前进程，如果存在string2，那么我们最好把string2放在string1前面，这样可以先在匹配string2，如果输出字符串中，没有相应的xxxbbcc，然后再去匹配string1。也就是说把“最大匹配”放在前面），那么只有匹配的字符会发给当前进程。因此，我们可以把“部分匹配”放在后面，如果整个字符（或是“最大字符”）匹配失败，我们再进行“部分匹配”。默认情况下，string匹配必须是精确完全匹配。（与之相反，expect命令默认使用glob-style模式）。-ex选项保证那些可能被解释成interact选项的string能被正确执行。任何以”-”开头的string都需要使用-ex。（所有以”-”开头的字符将被做为选项）

-re选项强制string按regexp模式解释。这种情况下，像expect会把它的输出存储在变量expect_out里面一样，interact匹配的字符串也会在存储在变量interact_out中。-indices选项的作用也和expect中的一样。Eof模式列出了在遇到文件尾的时候要执行的语句。一个单独的eof模式可能跟在-output选项后面，这样当写输出遇到文件尾的时候，就会触发eof模式，执行相应的语句。默认的eof行为是返回，所以执行interact命令时，在遇到文件尾就是返回。Timeout模式介绍了超时（以秒为单位）的概念，并列出了（超时）连续数秒没有读取到字符后的执行语句。Timeout作用于最近指定的进程。＊＊这里没有默认的timeout，特殊变量timeout（expect命令里面使用的）对这里的timeout模式没有影响。＊＊例如，下面的命令可以用于自动退出用户，他们在一小时之内没有输入任何字符，却一直收到系统消息。

`interact -input $user_spawn_id timeout 3600 return –output $spawn_id`

如果模式为关键词null，而且null是允许的(通过remove_null命令)，则在输出中如果出现单个的ASCII 0，那么null对应的语句将被执行。在glob和regexp模式下是不可能完成的。在模式前加上-iwrite选项，将会把匹配成功(或是遇到文件尾)的进程的spawn_id赋值给变量interact_out(spawn_id)。Break和continue会使控制结构(for循环，子函数等等)按照正常的方式运行，但return会使interact把信息返回给它的调用函数。Inter_return会使它的调用函数返回。例如，如果一个子函数foo调用了inter_return，在执行inter_return时，子函数foo会返回。(这就是说，当interact交互式调用解释器时，如果输入return，那么交互还将继续，如果输入inter_return，那么interact将返回)

在interact执行过程中，终端工作在“原始状态”下，这样所有字符都将发送给当前进程。如果当前进程没有捕获到工作流程的信号，那么按下Ctrl+Z会使其中止。如果想重启这个进程，可以给它发送一个“继续”信号(如执行：`kill -CONT <pid>`)，如果你真想给当前进程发送一个“中止” 信号，你可以考虑先监视csh，然后再启动你的程序。也就是说，如果你想发送中止信号给Expect，首先要调出解释器(可能是按一下ESC键)，然后按下Ctrl+Z。

为了避免进入解释器，交互式的执行命令，string-body可以用做“速记”，当string-body对应的body执行的时候，使用的前一个终端模式。为了程序的执行效率，默认情况下，终端使用原始状态。-reset使终端恢复到interact执行以前的状态(总是“精加工”状态)。注意的是，在进行终端模式转换的时候，此时输入的字符可能丢失(在一些系统上，会出现这种

糟糕的现象)。最好在你必须使用”精加工”模式再使用-reset选项。-echo选项使与模式进行匹配的字符同时也被发送给产生这些字符串的当前进程，就好像是当前进程读取到他们一样。这在当用户希望在执行某些指令需要看到回显的时候非常有用。

如果回显了一个模式，但最终没有匹配成功，这些字符会被发送到监视的进程，如果监视的进程再把它们显示出来的话，那么用户将会看到他们两次。-echo可能仅仅适合于当用户不可能不完成模式匹配的情况。例如：下面是摘自于rftp，一个递归式ftp脚本，用户被提示输入”~g，~p，~l”，以便递归的”获得，上传，查看”当前路径。这些字符和常规的ftp命令相差太远，用户除非出错，否则基本上不会打出”~”后面跟有某些字符的情况。这种情况下，他们就可能会忽略了正确的结果。

```shell
interact {
    -echo ~g {getcurdirectory 1}
    -echo ~l {getcurdirectory 0}
    -echo ~p {putcurdirectory}
}
```

`-nobuffer`选项会把进行模式匹配的字符发送给输出进程，就像这些字符是被读取的一样。这在你想让进程回显模式的时候非常有用。例如，下面的代码监视了哪个用户在拨叫(一种Hayes模式的Modem)，每次都会在脚本的日志文件中后面看到一个”atd”。

```shell
proc lognumber {} {
    interact -nobuffer -re "(。*)\r" return
    puts log "[exec date]: dialed interact_out(1，string)"
}
interact -nobuffer "atd" lognumber
```



在交互过程中，log_user的前一个值被忽略了。特别需要说明的是，interact会强制使他的输出记录成日志(输出到标准输出)，因为它认为用户不希望没有任何回应的交互。-o选项使下面的key-body模式应用于当前进程的输出(也就是说用当前进程的输出来匹配模式)。这对于处理像”在一个telnet会话中输入很多错误字符(个人认为：非命令或是选项字符)”的情况非常有用。

默认情况下，interact希望用户对标准输入进行写操作，对标准输出进行读操作。-u选项通过指定进程名(通常是指定一个spawn_id)来使此进程的用户与其他进程进行interact(交互)。这就使两个毫不相差的进程通过这样一个联系连接起来。为了协助调试，Expect的调试信息经常会输出到标准错误输出(或是是标准输出，为了记录日志和调试信息)。同样，解释器也会交互的从标准输入读取字符。例如：下面的代码，建立了一个登录进程，它呼叫用户，然后使双方连接在一起。当然其他进程也可以取代这里的login进程。一个脚本，允许在不提供用户名与密码的情况下正常工作。

```shell
spawn login
set login $spawn_id
spawn tip modem
# dial back out to user
# connect user to login
interact -u $login
```

为了发送输出给多个进程，必须使用-output选项指定spawn_id列表。同样，要给多个进程输入字符，需要使用-input选项(-input和-output，还有expect中的-i选项都支持列表，除了特殊变量any_spawn_id在interact命令中无效，在expect中有效)。

所有接下来的选项或字符串(或模式)对当前的输入有效。直到下一个input选项为止。如果没有-input选项，-output选项暗含表示`"–input $user_spawn_id –output"`(在不含有-input选项的模式中也一样)。如果指定了一个-input选项，那么它将覆盖`$user_spawn_id`，如果出现第二个–input选项，那么它将覆盖`$spawn_id`，还有可能会指定更多的`-input`选项。

这两个暗含的输入进程把它们的输出默认分别把`$spawn_id`和`$user_spawn_id`作为它们的输出(做了调换)。如果-input选项后面没有-output，那么这个进程的输入将会被忽略。-i选项介绍了一种当没有使用-input或是-output选项时的替代方式。-i选项暗含一个-o选项。

使用“间接”spawn_id列可以改变交互进程 (“间接”spawn_id列已经在expect命令里面讲过) 。“间接”spawn_id列可以通过-i，-u，-input或是-output选项指定。

### interpreter

`interpreter [args]`

使用户交互的输入Expect或是Tcl命令，每个命令的结果都会被打印出来。

Break和continue会使控制结构(for循环，子函数等等)按照正常的方式运行，但return会使interact把信息返回给它的调用函数。Inter_return会使它的调用函数返回。例如，如果一个子函数foo调用了inter_return，在执行inter_return时，子函数foo会返回。其他命令使interpreter继续提示输入新的命令。默认情况下，提示包含两个整数。第一个表示the depth of evaluation stack嵌套的层数(也就是Tcl_Eval被调用了多少次)。第二个参数是Tcl的history identifier历史指针。提示符可以通过定义一个叫做”prompt1”的子函数来设置，这个子函数的输出会成为下一个提示符。如果一条语句中包含半开的(也就是一个，不是一对儿)引号，大括号，中括号或是小括号，那么下一个提示符会被放在新一行。第二个提示符同样也可以通过定义一个叫做”prompt2”的子函数来设置。在interpreter执行过程中，终端使用“精加工”模式，即使它的调用函数使用的是“原始中”模式。如果在没有使用-eof选项的情况下，标准输入被关闭，那么interpreter就会返回。如果使用了-eof选项，那么将调用下一个参数。

### log_file

`log_file [args][[-a] file]`

如果指定了文件名，那么log_file命令会把会话的记录写入文件(从执行这条语句开始)，如果没有给定任何参数，那么log_file命令会停止记录。前面的日志文件都将被关闭。不指定文件名，还可以通过-open或是-leaveopen选项来指定Tcl文件描述符，这和spawn命令的用法一样(参见spawn命令)。-a选项强制把log_user命令产生的输出记录到日志。默认情况下，为了在一次会话中能很方便的多次关闭日志记录，log_file命令会把输出信息添加到文件尾，而不是覆盖原来的内容。如果想覆盖原来的内容，可以使用-noappend选项。-info选项使log_file命令返回关于最近的non-info(非info选项)参数的描述。

### log_user

`log_user -info|0|1`

默认时，send/expect对话会被记录到标准输出中，可以通过log_user 0来禁止，通过log_user 1来恢复。输出到日志文件维持不变。-info选项使log_user命令返回关于最近的non-info(非info选项)参数的描述。

### match_max

`match_max [-d][-i spawn_id] [size]`

这个命令定义Expect内部使用的缓冲区大小。如果没有参数，返回当前大小。如果使用-d选项的话，将缓冲区设置为默认大小(初始的默认大小是2000 Bytes)。如果使用了-i选项，那么设置的是对应于spawn_id的进程的缓冲区大小。否则设置的是当前进程的。

### overlay

`overlay [-# spawn_id][-# spawn_id] [...] program [args]`

终止当前的Expect程序，执行program args。一个连字符没有指定参数，那么连字符将被放到命令之前，就像它是一个登录Shell一样。除了那些在命令行中做为参数指定的spawn_id外，其他将全部被关闭。这些在命令行中指定的spawn_id将被重定向到指定的文件描述符。这些spawn_id被重定向到文件描述符是为了新程序来继承。例如，下面的命令运行chess程序，而且允许当前程序--chess master(chess控制者)来控制。

`overlay -0 $spawn_id -1 $spawn_id -2 $spawn_id chess`

虽然它牺牲了执行程序化交互的能力，因为Expect已经失去控制权，但还是要比”interact –u”更有效率。注：在这里，没有提供控制终端，因此，如果你断开或是重定向了标准输入，那么控制作业的程序(Shell，login等等)将不能正常访问。

### parity

`parity [-d][-i spawn_id] [value]`

定义parity是否需要与当前监视的进程的输出分隔开。如果设为0，则是分隔开，否则将不分开。如果没有参数的话，将返回当前值。-d选项将parity设置为默认的值(初始默认值为1，不分开)。-i选项用来指定需要设置parity值的，对应于spawn_id的进程。否则设置当前进程的parity值。

### remove_nulls

`remove_nulls [-d][-i spawn_id] [value]`

此命令用来定义null在匹配模式或是存储到变量expect_out或是interact_out之前，是否需要与监视进程的输出分隔开。如果设为1，则分开，如果为0，则不分开。没有参数，返回当前值。-d选项用来把它设为默认值(初始默认值为1，分隔开)。-i选项用来设置对应于spawn_id的进程的remove_nulls的值。如果没有使用-i选项，那么将设置当前进程的remove_nulls的值。不管null是否被分隔，Expect都会把它们记录到日志和标准输出中。

### send

`send [-flags] string`

发送字符串给当前进程。例如`send "hello world" `会向当前进程发送“hello空格world回车”(Tcl提供了一个类似于printf的命令，它可以构建任意复杂的字符串)。尽管工作在行缓冲模式下的程序直到遇到回车的时候才会读到这些字符，但这些字符还是在输入的时候就被立即发送出去了。回车被表示为”\r”。

--选项使下面的参数做为字符串而不是选项。所有的字符串，不管它是否像选项，都可以前缀一个”--”。这提供了一种安全机制：不会再去费力区分很像选项的字符串到底是什么了。(所有以”-”开头的字符串将作为选项使用)。

-i选项指定字符串将发往对应于spawn_id的进程。如果指定的spwan_id是user_spawn_id的话，而且终端工作在原始状态下，那么字符串中的换行将被转换为回车换行，以使它们看起来像是工作在“精加工“状态下。-raw选项禁止这种转换。-null选项用来发送null字符。默认情况是发送一个null字符。可以通过设定一个整数来确定发送多少个null字符。-break产生一个中断条件。这只有对使用spwan_open命令打开tty设备对应的spawn_id时才有效。如果你监视了类似于tip的程序，那么你应当遵循tip的规则来产生一个中断。-s选项强制发送速度变慢。这是避免计算机输入时，会出现输入缓冲区溢出。因为某些输入缓冲区是以人的输入速度为标准设计的。输出的速度由变量send_slow的值来限定的。它带有两个参数。第一个是一个整数，它用来描述自动发送的字节数。第二个参数用来设定发送的时间间隔。例如：set send_slow{10 。001} 将使”send -s”在每发10个字符后停1毫秒，然后再发10个字符。-h选项使发送模拟人的击键速度。每个字符之间的时间间隔也像人手敲击间隔。(这种[算法](http://lib。csdn。net/base/datastructure)是基于WeiBull规则，它带有修正值以适应特殊情况)这种输出的速度通过变量send_human中的5个参数来设定。前两个参数是字符敲击之间的平均时间，第一个是默认使用时间。第二个是输入一个词后的停顿时间。以模拟这种转变过程中(从连续输入字符到输入一个词再输入另一个词的转变)的微小停顿。第三个参数是稳定度，。1表示非常不稳定，1表示相对不稳定，10表示非常稳定。极限是0和无限大。最近两个参数分别指定了最小和最大的间隔时间。它们用来修正最终时间。如果最小和最大有大量的取值范围，最后的平均时间可能会和给定的平均间隔时间有很大的差别。

例如：下面模拟了一个快速稳定的打字员

```shell
set send_human {.1 .3 1 .05 2}
send -h "I'm hungry. Let's do lunch."
```

下面模拟的更适合于一个经常有较大停顿的打字员

```shell
set send_human {.4 .4 .2 .5 100}
send -h "Goodd party lash night!"
```

注：错误是不可以模拟的。虽然你可以通过在send命令参数里面嵌入错误和更正来设定错误更正模式。

这些发送null字符，设定中断条件和强制发送变慢，模拟人的输入的选项之间都是互斥的，只有最后的选项有效，还有需要说明的是，发送null字符和设定中断条件选项是不能带有其他参数的。

在第一个send前加一个expect命令是个不错的主意，因为expect会等待进程开始，而send不会等待。在某些情况下，进程还没有开始，你的第一个send已经发送完毕了，这样你就会有把你的数据丢失的危险。在某些交互程序没有初始化提示符的情况下，可以在send之前加一个延时。

```shell
# To avoid giving hackers hints on how to break in，
# this system does not prompt for an external password。
# Wait for 5 seconds for exec to complete
spawn telnet very。secure。gov
sleep 5
send password\r
```

exp_send是send的别名。如果你使用的是Expectk或是Tk环境下某些Expect的变体或是扩展，那么Tk会把send做为与现在完全不同用途的命令。

### send_error

`send_error [-flags] string`

用法很像send，除了它是把输出发送到stderr(标准错误输出)，而不是当前进程。

### send_log

`send_log [--] string`

用法很像send，除了它是把string发送给logfile。如果没有logfile打开的话，那么参数将被忽略。

### send_tty

`send_tty [-flags] string`

用法很像send，除了输出是发往/dev/tty而不是当前进程。

### send_user

`send_user [-flags] string`

用法很像send，除了输出是发往标准输出stdout而不是当前进程。

### sleep

`sleep seconds`

使脚本停顿给定的秒数。Seconds是十进制数。在Expect停顿期间，中断(或是在使用Expectk时的Tk事件)也会正常响应。

### spawn

`spawn [args] program [args]`

创建一个执行program args命令的进程。它的stdin，stdou，stderr(标准输入，标准输出，标准错误输出)都连到Expect。这样它们可以被其他的命令读取和写入。执行close命令或是进程本身关闭这三个描述符之中的一个都将关闭这个连接。当用spawn开启一个新进程时，这个进程的描述符将赋给变量spawn_id。Spawn_id指定的进程被认为是当前进程。为了有效的提供作业控制，可能需要读写spawn_id。User_spawn_id是一个全局变量，它指定了用户的ID，例如：把user_spawn_id赋给spawn_id时，expect将以expect_user方式执行。Error_spawn_id也是一个全局变量。它包含了指向错误输出的描述符。例如：当spawn_id赋了这个值的话，那么send将以send_error方式工作。Tty_spwan_id也是一个全局变量，它包含了一个指向/dev/tty的描述符。如果/dev/tty不存在(例如在一个cron任务，at任务，或是batch脚本中，无人操作)，那么tty_spawn_id将不被赋值。可以通过下面的代码得到验证。

```shell
if {[info vars tty_spawn_id]} {
	# /dev/tty exists
} else {
    # /dev/tty doesn't exist
    # probably in cron， batch， or at script
}
```

spawn命令会返回UNIX进程的ID。如果没有监视进程，那么返回0。变量spawn_out(slave，name)将保存pty从设备的名称。默认情况下，spawn命令会回显命令和参数，-noecho禁止回显。-console选项使控制台的输出重定向到监视的进程。但并不是所有系统都支持这一选项。Spawn内部使用pty。它像用户的tty一样初始化。它还将更进一步的初始化，以”完善”功能(对应stty(1))。如果设置了变量stty_init，它将以stty参数的形式执行进一步初始化。例如：“set stty_init raw”将会使以后监视的进程终端以原始方式启动。–nottycopy选项将忽略(不执行)基于用户tty的初始化。–nottyinit选项将忽略(不执行)“完善“的初始化。正常情况下，spawn的执行速度很快，如果你发现spawn执行的非常慢，它很有可能遇到了错误的pty，为了避免错误进程的干扰，在pty方面做了大量的测试(每个错误的pty会花费10秒钟)。使用-d选项执行spawn命令会看到Expect是否工作在不稳定状态的pty上面。如果不能杀死与这些pty对应的进程，那么唯一的办法就只有重启。如果因为exec执行失败(没有program这个程序)，造成program不能正常被监视，那么在执行下一个interact或是expect命令时会返回一个错误信息。就像程序已经执行，然后产生了错误信息一样。这是spawn正常的运行结果。在spawn执行的时候，如果执行fork复制进程，之后监视的进程和原来的Expect程序之间只能通过spawn_id互相通信。-open使下一个参数被解释为Tcl文件描述符(也就是说由open命令返回的)。可以把它作为spawn_id使用，就像它是一个被监视的进程一样(文件描述符将不再使用)。这让你在不使用pty的情况下，监视原始设备，文件和管道。如果返回0，表示没有相关联的进程。当与监视进程的连接关闭之后，Tcl文件描述符也将关闭。-leaveopen选项用法很像-open选项，除了监视进程被关闭，Tcl文件描述符仍然保持打开之外。-pty选项打开一个pty，但不监视进程。返回0表示没有相关联的进程。Spawn_id还像以往一样赋值。对应于pty slave的文件描述符赋给变量spawn_out(slave，name)。可以通过close-slave关闭pty slave。-ingnore选项定义了在监视进程过程中可以忽略的信号。如果没有定义的话，那么对应相应的信号将执行相应的动作。这里的信号命名方式与trap命令中的一样。只是这里每个信号要分别使用一个单独的标记。

### strace

`strace level`

使接下来的语句在执行之前首先被打印出来(Tcl的trace命令跟踪变量)。Level定义了跟踪几层。例如，下面的语句执行Expect程序，只跟踪4层调用，其他不管。

`expect -c "strace 4" script.exp`

`-info`选项使strace命令返回最近的非info选项参数的描述。

### stty

`stty args`

像内部命令stty一样，改变终端的工作模式。默认情况下，控制终端是可以被访问的。其他终端通过在文件尾添加”< /dev/tty。。”，也可以被访问(注意：这些参数不要组合成一个单一的参数)。终端状态将作为命令结果返回。如果没有请示终端状态，而且控制终端可以访问，那么前一个“原始”状态和显示属性将以一种命令可识别的方式返回。例如：参数raw或是–cooked使终端工作在原始状态，而-raw或是cooked使终端工作在“精加工“状态。参数-echo和–noecho分别设置终端为”回显“和”不回显“方式。

下面的例子演示了怎样间断性的关闭回显。这可以用在那些避免将密码嵌入到脚本中的自动执行程序(参见下面的Expect Hints关于这方面的讨论)。

```shell
stty -echo
send_user "Password: "
expect_user -re "(。*)\n"
set password $expect_out(1，string)
stty echo
system args
```

把args传给shell作为输入，就好像是从终端输入的一样。Expect会等待直到shell执行结束。从shell的返回状态的处理方式和exec处理它自己的返回状态一样。与exec不同的是：exec会把标准输入和标准输出重定向到脚本，而system不做重定向(除非在要执行的语句内部指定)。因此，它可以用于必须直接与/dev/tty对话的程序。同理，system的执行结果也不记录到日志里面。

### timestamp

`timestamp [args]`

返回一个时间戳。没有参数的话，返回从epoch开始到现在的秒数。-format选项指定了一个要返回的时间字符串，返回的时候这个字符串中的相应字符将按照POSIX规则替换。例如：a%会被工作日的简写形式所代替(例如：Sat)。其他的如下：

```shell
%a	abbreviated weekday name
%A 	full weekday name
%b 	abbreviated month name
%B 	full month name
%c 	date-time as in: Wed Oct 6 11:45:56 1993
%d 	day of the month (01-31)
%H 	hour (00-23)
%I 	hour (01-12)
%j 	day (001-366)
%m 	month (01-12)
%M 	minute (00-59)
%p 	am or pm
%S 	second (00-61)
%u 	day (1-7， Monday is first day of week)
%U 	week (00-53， first Sunday is first day of week one)
%V 	week (01-53， ISO 8601 style)
%w 	day (0-6)
%W 	week (00-53， first Monday is first day of week one)
%x 	date-time as in: Wed Oct 6 1993
%X 	time as in: 23:59:59
%y 	year (00-99)
%Y 	year as in: 1993
%Z 	timezone (or nothing if not determinable)
%% 	a bare percent sign
```

其他%形式没有指定的，那么相应的字符在返回时不改变。只支持C规范。

-seconds选项指定了一个整数。这个整数用来确定epoch。它是从epoch到现在的秒数。用epoch时刻的时间做为样本，来格式化时间。如果没有参数，那么将使用当前时间作为样本。

-gmt选项强制使时间戳的输出使用GMT时区模式。如果没有参数，使用当前时区。

### trap

`trap [[command] signals]`

在将来收到任何指定信号时，执行指定的命令。这个命令在全局范围内有效。如果指定的命令不存在，那么信号对应的执行动作就是返回。如果命令是SIG_IGN，那么信号将被忽略。如果命令是SIG_DFL，那么信号将执行系统的默认动作。Signals可以是一个信号，也可以是一组信号。信号可以以数字表示，也可以像signal(3)中定义的那样以符号表示。可以省略前缀”SIG”。没有参数的话，返回trap命令执行时的信号码。-code选项在命令最初开始执行的时候，返回命令的返回码，而不是返回Tcl的返回码。

-interp选项使在命令执行的时候，而不是命令声明的时候，用interpreter active给命令赋值。

-name选项返回trap命令当前被执行时的信号名。

-max选项返回最大的可以设置的信号码

例如：”trap {send_user “Ouch”} SIGINT会在每次用户按下Ctrl+C时打印出”Ouch”。默认情况下，SIGINT(按下Ctrl+C实现)和SIGTERM会使Expect退出，这是因为Expect起动时会默认执行trap exit {SIGINT，SIGTERM}。如果你用-D选项启动了调试器，那么SIGINT被重新定义为启动交互调试器。这是因为默认执行了trap {exp_debug 1} SIGINT。这个跟踪命令可以通过修改变量EXPECT_DEBUG_INIT中的值来改变。当然你也可以通过在脚本中增加相关的trap命令来覆盖它们。特别说明的是，如果你写了”trap exit SIGINT”，那么这将覆盖掉”trap {exp_debug 1} SIGINT(系统默认)。这在你阻止用户进入调试器非常有用。如果你想在脚本运行的时候还能跟踪调试器，而且还能定义自己的“SIGINT“信号对应执行指令。那么你就用”if ![exp_debug] {trap my_CMD SIGINT}”。当必须出选择时，你可以通过其它信号来调用调试器。Trap命令不允许覆盖关于信号SIGALRM的对应执行动作。因为它是Expect内部使用的信号。Disconnect命令会把SIGALRM设置为SIG_IGN(忽略)。了解更多信息请参见signal(3)。

### wait

`wait [args]`

等待直到一个监视的进程中止。(如果没有指定进程，则是当前进程)。Wait命令会返回一个4个整数列。第一个是监视进程的ID，第二个是对应的spawn_id，第三个如果为0表示成功，-1的话表示[操作系统](http://lib。csdn。net/base/operatingsystem)有有错误产生。如果第三个参数为0，第4个参数为监视进程的返回状态信息。如果第三个参数为-1，第4个参数为系统设置的错误码。全局变量errorCode也将被设置。在wait命令得到的返回信息中，还可能会出现其他元素。第五参数(可选的)表明了信息的级别。目前唯一可用的值是CHILDKILLED，这种情况下，接下来的两个值分别是C-Style的信号名和一个短的文字性描述。-i选项指定要wait的进程是由spawn_id指定的进程(不是进程ID)。在SIGCHLD的句柄里，通过指定spawn_id为-1，可以wait任何监视的进程。

-nowait选项使一旦成功执行wait之后，立即返回。这样当进程退出(以后)，不需要直接再显式执行wait。进程就可以自己消失。通过使用”-i -1”，wait还可以用于等候一个复制的进程。不像它在其它监视进程里面的用法，这里它可以在任何时候执行。这时对于“哪个进程返回了”没有控制，但可以通过检测每个进程ID对应的返回值。 

## 库文件

Expect自动关联两个为Expect内建的库文件。它们定义在变量exp_library和exp_exec_library定义的目录里。两个目录都是用来存放为其他脚本文件使用的有用文件。Exp_library包含有结构独立的文件。Exp_exec_library包含了体系依赖的文件。依赖于系统，两个文件夹可能都是空的。存在”$exp_exec_librara/cat-buffer”与否表明你的/bin/cat是否工作在默认的缓冲模式下。 

## 漂亮的格式化显示

规范化Expect脚本文件的格式是可行的。假设支持Expect的vgrind功能插件已经安装。你可以这样执行：vgrind –lexpect 脚本名 

## 例子

也许手册没有很直接的告诉你怎么把手册上的例子放到一起应用。我还是鼓励你把Expect目录下的例子全部尝试一遍。其中的一些是真实的程序。另外的一些也展示了一定的技巧。当然其中一些只是hacks。INSTALL文件中有一个程序的简介。Expect也是很有用的。其中的一些规则使用的是较早版本的Expect。其中的原理仍然有效，只不过现在可能又形成了比上面介绍的更多的细节。 

## 警告

扩展属性可能会与Expect命令有冲突。例如，在Tk中，send被定义为与现在完全不同的用途。同理，很多其他命令在Tk中是以exp_xxx方式来使用的。以exp，inter，spawn和timeout开头的命令和变量是没有别名的。如果你想保证兼容性，必须使用扩展的命令名称。 Expect提供了相对自由的作用范围。特别说明的是，从命令行为Expect程序读取的变量的赋值首先被当前作用域截取，如果没有相应的变量，则在全局中查找。例如，这避免了在每个执行expect的进程里面放上“global timeout”。另一方面，变量一般是以局部方式定义的(除非使用一个“全局命令“)。引起的最普遍的问题就是当在一个进程内部执行spawn命令的时候，在这个进程外部，spawn_id不再存在，所以由于作用范围的问题，使监视的进程不能再访问。在这样的进程里，可以加上一个全局变量spawn_id。如果你不能启用多进程监视(你的系统不支持选择，也不支持poll，或是类似的功能)，Expect只能一次控制一个单个的进程。这种情况下，不要试图设置spawn_id，也不要在一个监视进程运行的时候通过exec执行新的程序。这样就是说，你不能用Expect来同时监视多个进程。

终端参数对脚本也有很大的影响。例如，如果脚本想要回显，但如果终端关闭了回显，那么脚本执行将不能实现预期的结果。就是因为这样，Expect强制“完善“的终端模式。不幸的是，这可能对其他某些程序来说不太方便。例如，Emacs想改变常见的绘制规则：把多行映射到多行，而不是回车换行分隔的多行，并且关闭回显，这样可以使用户用Emacs编辑输入。但不幸的是，Expect不能做到这些。

你可以使Expect不覆盖终端的默认参数，不过你要为这种环境写脚本要非常小心。在Emacs的例子中，要避免做依赖于回显或是换行的工作。带有很多参数的命令被连成一列(expect变量和interact)，它需要一个标记来判断它是一个参数还是多个。当本来是一个参数的命令中有很多的”\n”，而且没有空格的时候，Expect将不能正确判断。这听起来好像完全不可能，然而-nobrace可以把一个参数强制按一个参数来解释。这可以应用在由机器产生的Expect码中。

 

## 漏洞

本来是要把这个程序命名为”sex” (for either "Smart EXec" or "Send-EXpect")。但最后还是以更文雅的方式命名了。在一些系统上，当监视一个Shell时，它会在不能访问tty时仍然运行。这就是说，你的系统有控制tty的机制，但Expect不了解。如果你知道为什么，请告诉我。

Ultrix 4。1(至少是目前最新的版本)会把timeout的值超过1000000时认为是0

Digital UNIX 4。0A(可能是其他版本)如果定义了SIGHLD，会拒绝分配ptys

IRIX 6。0不能正确处理的pty权限，所以如果尝试用前面某个人的pty，它将会失败。请更新到IRIX 6。1

如果没有设置TERM，Telnet会挂掉(只在Sun OS 4。1。2下面证实)。这也是cron，at，和cgi脚本下的问题，因为它们没有设置TERM。因此必须对TERM赋值，赋给它一个不会影响正常运行的类型。下面的设置应该适合大部分情况。

`set env(TERM) vt100`

如果没有设置SHELL和HOME的话，Tip会挂起(只在BSDI BSD/OS 3。1 i386下证实过)。这也是cron，at，和cgi脚本下的问题，因为它们没有设置这些环境变量。因此必须对这些环境变量赋值，赋给它们一个不会影响正常运行的类型。这些变量是必须要赋值的。下面的语句应该适合大部分情况。

```shell
set env(SHELL) /bin/sh
set env(HOME) /usr/local/bin
```

一些pty的执行被设置成：在进程把文件描述符关闭之后，如果10或是15秒没有读取输出，那么这些输出将被丢弃(具体的时间视执行情况而定)。因此像下面的Expect程序会失败。

```shell
spawn date
sleep 20
expect
```

为了避免这样，可能通过exec执行非交互式程序，而不是使用spawn。这虽然是可信的，但在实践当中，我从来没有遇到在一个交互式程序时，由于上面的BUG而出现丢失字符的现象。另一方面：Cray UNICOS pty会在进程关闭文件描述符的时候立即丢弃所有未读取的输出字符。我已经通知了Cray，它们正在修补这个BUG。有时候，在提示和响应之间需要做一个停顿。例如，当tty正在改变UART设置或是通过检测起始位来匹配波特率的时候。通常，这些都需要停顿1到2秒。一种更智能的技术就是不断尝试，直到硬件准备接收输入为止。下面的代码应用了这两种技术。

```shell
send "speed 9600\r";
sleep 1
expect {
    timeout {send "\r"; exp_continue}
    $prompt
}
```

trap –code不能正确处理位于Tcl事件环内部的命令，比如说sleep。问题的原因是：在Tcl事件环内部，Tcl会忽略异步事件的返回码。在trap码内部设置一个工作区，里面设置一个标记。然后在命令(也就是sleep)执行之后立即检查标记。

## Expect提示

这有一些关于Expect的非直观的东西。这一节通过一些建议讲述这些事情。常见的Expect问题是它怎样标识认出Shell的提示符。因为它们会因用户和Shell的不同而不同。自动登录在不知道提示符的情况下会变得很困难。一个可取的惯例是让用户把一个能够描述这此描述符的常规表达式赋值给一个环境变量EXPECT_PROMPT。代码可以写成如下所示，如果EXPECT_PROMPT不存在，代码也能正常运行。

```shell
set prompt "(%|#|\) " ;# default prompt
catch {set prompt $env(EXPECT_PROMPT)}
expect -re $prompt
```

我建议你把输出字符的结尾包含到你的pattern中，这样可以不用看到整个字符，就可以做出响应。另外，当你可以在不看到整个字符串做出响应的时候，如果你回答的过早，你的答案可能会被显示在问题的中间。换句话说，这样的话，虽然你的结果是对的，但会话记录看起来有点混乱。大多的提示符最后包含一个空格。例如ftp的提示符是“f”，”t”，”p”，”>” 和<blank>。要匹配这个字符串，你要考虑每个字符。丢掉空格是常见的错误，一定要加上空格。

你如果使用”X*”这种模式，那么*会匹配所有从X后开始到最后收到的东西。这听起来很直观，但是“最后收到的东西“让人感觉有点迷惑。这和机器的速度，Kernel和设备驱动处理I/O的方式都有关系。特别的是，人们趋向于认为输出是成块的传输。但实际上大部分程序每次只产生一行输出。如果是这样的话，那么上面的*将只会匹配当前的行尾。虽然可能还会有很多。但在执行匹配的时候，*里面已经是”所有收到的“了。除非模式充分考虑这些情况，要不然Expect不会知道还会有接下来的输出。

把程序设成工作在行缓冲模式下也是不可取的。不仅程序很少把它们的缓冲模式固定，系统处理也可能把输出分隔成多行的。而且分隔点可能在很随机的地方。因此如果在确定模式的时候能够描述出提示符的最近字符，建议你写上它们。

如果你想匹配程序最后输出的字符，但程序却输出了其他的字符，这样你就不可能通过timeout模式来检测到这种情况。原因是Expect不会有超时，相反它会得到一个“遇到文件尾”的信号。使用eof来代替timeout，或是两者都用更好。That way if that line is ever moved around， you won't have to edit the line itself。

终端驱动输出字符的时候一般会把输出中的换行转换为回车，换行的顺序。因此如果你想一个模式能够匹配分别在两行中的字符，例如printf(foo\nbar)的输出，你需要把模式设为"foo\r\nbar"。通过expect_user命令从用户读取信息时也会执行类似的转换。这种情况下，当你按下回车，它将被转换为“换行”，如果这时Expect把它传给一个工作在原始状态的终端上的程序，会产生一个错误，因为程序期望的是一个真正的回车(一些程序设计考虑并解决了这个问题，它会把“换行”转换为“回车”，但大部分不会)。不幸的是，没有办法检测一个程序是否把它的终端设成原始状态。

为了避免手工将“换行”转换为“回车”，解决方法是使用stty raw来阻止这种转换。注意：这样的话，你就不能使用“精加工”模式下的行编辑特性了。

Interact命令隐含的把终端设置成原始模式，这样，上面的问题就不会在它里面出现。

有时在Expect的脚本中存储密码(或其他个人信息)是很有用的。但不建议这样做。因为存在计算机上的东西很可能会受到其他用户的影响。因此，从一个脚本中交互的提示输入密码要比把密码嵌入到脚本内好一些。尽管如此，有时嵌入是唯一可行的办法。

不幸的是，UNIX文件系统不能直接创建可执行而且不可读的脚本文件。支持设置setgid的系统可以间接实现如下：创建Expect脚本文件(包含隐私的数据)，设置它的权限位为750(-rwxr-x---)，把所有权赋给一个可信任的组。也就是一个可以读取它的组。如果需要的话，可以创建一个这样的组。接下来，以权限2751(rwxr-s--x)建立一个/bin/sh脚本文件，拥有它的所有权限的组和上一个文件一样。结果就是可以被其他人读和执行的脚本。在调用的时候，它执行的是Expect脚本。