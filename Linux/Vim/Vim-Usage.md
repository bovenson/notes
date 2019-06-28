---
title: Vim 使用
tags: 
	- Linux
	- vim
categories:
	- Linux
---

# 简介

**三种模式**: 

- 输入模式（`Insert mode`）
- 末行模式（`Last line mode`）
- 命令模式（`Command mode`）

**切换:**

| 按键      | 到达的模式        |
| ------- | ------------ |
| `i,o,r` | 输入模式 / 编辑模式  |
| `:`     | 末行模式 / 指令列模式 |
| `ESC`   | 命令模式 / 一般模式  |

# 命令

## 基础

| 命令              | 说明                                       |
| --------------- | ---------------------------------------- |
| `:e filename`   | Open *filename* for edition              |
| `:w`            | Save file                                |
| `:q`            | Exit Vim                                 |
| `:q!`           | Quit without saving                      |
| `:x`            | Write file (if changes has been made) and exit |
| `:sav filename` | Saves file as *filename*                 |
| `.`             | Repeats the last change made in normal mode |
| `5.`            | Repeats 5 times the last change made in normal mode |

## 移动

| 命令              | 说明                                        |
| ----------------- | ------------------------------------------- |
| `k or Up Arrow`   | move the cursor up one line                 |
| `j or Down Arrow` | move the cursor down one line               |
| `e`               | move the cursor to the end of the word      |
| `b`               | move the cursor to the begining of the word |
| `0`               | 行首                                        |
| `$`               | 行尾                                        |
| `G`               | 文尾                                        |
| `gg`              | 文首                                        |
| `L`               | move the cursor to the end of the file      |
| `:59`             | 跳转到指定（这里是第59）行                  |
| `20|`             | 移动到第20列                                |
| `%`               | Move cursor to matching parenthesis         |
| `[[`              | Jump to function start                      |
| `[{`              | Jump to block start                         |



## 搜索
| 命令                                   | 说明                                       |
| ------------------------------------ | ---------------------------------------- |
| `/word`                              | Search *word* from top to bottom         |
| `?word`                              | Search *word* from bottom to top         |
| `*`                                  | Search the word under cursor             |
| `/\cstring`                          | Search *STRING* or *string*, case insensitive |
| `/jo[ha]n`                           | Search *john* or *joan*                  |
| `/\< the`                            | Search the, theatre or *then*            |
| `/the\>`                             | Search *the* or *breathe*                |
| `/\< the\>`                          | Search *the*                             |
| `/\< ¦.\>`                           | Search all words of 4 letters            |
| `/\/`                                | Search *fred* but not *alfred* or *frederick* |
| `/fred\|joe`                         | Search *fred* or *joe*                   |
| `/\<\d\d\d\d\>`                      | Search exactly 4 digits                  |
| `/^\n\{3}`                           | Find 3 empty lines                       |
| `:bufdo /searchstr/`                 | Search in all open files                 |
| `bufdo %s/something/somethingelse/g` | Search *something* in all the open buffers and replace it with*somethingelse* |

## 剪切(删除)

```shell
#### 删除某行至文件结尾
G		# 转到文件结尾
:10,.d	# 删除第10行至当前行(文件结尾)

#### 删除10行至20行
20G		# 跳转至第20行
:10,.d	# 删除第10行至当前行
```



| 命令     | 说明                       | 示例             |
| ------ | ------------------------ | -------------- |
| dd     | 剪切当前行                    |                |
| ndd    | n表示大于1的数字，剪切n行           |                |
| dw     | 从光标处剪切至一个单子/单词的末尾，包括空格   |                |
| de     | 从光标处剪切至一个单子/单词的末尾，不包括空格  |                |
| d$     | 从当前光标剪切到行末               |                |
| d0     | 从当前光标位置（不包括光标位置）剪切至行首    |                |
| d3l    | 从光标位置（包括光标位置）向右剪切3个字符    |                |
| d5G    | 将当前行（包括当前行）至第5行（不包括它）剪切  |                |
| d3B    | 从当前光标位置（不包括光标位置）反向剪切3个单词 |                |
| dH     | 剪切从当前行至所显示屏幕顶行的全部行       |                |
| dM     | 剪切从当前行至命令M所指定行的全部行       |                |
| dL     | 剪切从当前行至所显示屏幕底的全部行        |                |
| n1,n2d | 剪切n1到n2行                 | 1,10d          |
| n，$d   | 剪切从某行开始至文本末尾             | 8,$d: 删除第8行至末尾 |

## 复制

| 命令   | 说明                       | 示例   |
| ---- | ------------------------ | ---- |
| yy   | 复制当前行                    |      |
| nyy  | n表示大于1的数字，复制n行           |      |
| yw   | 从光标处复制至一个单子/单词的末尾，包括空格   |      |
| ye   | 从光标处复制至一个单子/单词的末尾，不包括空格  |      |
| y$   | 从当前光标复制到行末               |      |
| y0   | 从当前光标位置（不包括光标位置）复制之行首    |      |
| y3l  | 从光标位置（包括光标位置）向右复制3个字符    |      |
| y5G  | 将当前行（包括当前行）至第5行（不包括它）复制  |      |
| y3B  | 从当前光标位置（不包括光标位置）反向复制3个单词 |      |



## 粘贴

| 命令   | 说明                                       |
| ---- | ---------------------------------------- |
| p    | 小写p代表贴至游标后（下），因为游标是在具体字符的位置上，所以实际是在该字符的后面 |
| P    | 大写P代表贴至游标前（上）                            |

整行的复制粘贴在游标的上（下）一行，非整行的复制则是粘贴在游标的前（后）.

## 撤销与恢复

- `u`   撤销上一步的操作 
- `Ctrl+r` 恢复上一步被撤销的操作

## 替换

利用`:substitute`命令, 可以将指定的字符替换成其他字符. 通常, 我们会使用命令的缩写形式`:s`, 格式如下:

`:[range] s/search/replace/[flags] [count]`

其中, range是指定范围, 也就是在那些行做替换. 而后是将字符串from替换成字符串to.

### 替换标记

默认情况下, 替换命令仅将本行中第一个出现的字符替换成给定字符. 如果我们想要将所有的字符都替换成给定字符, 可以在命令中使用g(global)标记:

`:%s/from/to/g`

标记(flags)包括: 

-   g(global): 将所有的字符都替换成给定字符

- p(print): 是要求打印所做的改动
- c(confirm): 是要求在做出改动以前先询问
- i(ignorecase): 不区分大小写

```shell
:1,$ s/Professor/Teacher/gc	# 显示将要做改动的文本并要求确认
replace with Teacher (y/n/a/q/l/^E/^Y)?	# 提示
# y Yes：执行这个替换
# n No：取消这个替换
# a All：执行所有替换而不要再询问
# q Quit：退出而不做任何改动
# l Last：替换完当前匹配点后退出
# CTRL-E 向上翻滚一行
# CTRL-Y 向下翻滚一行
```

## 指定范围

- 默认当前行
- $ : 至文档结尾
- % : 整个文件

如果没有在命令中指定范围, 那么将只会在当前行进行替换操作. 以下命令将把当前行中的I替换为We. 命令中的/i标记, 用于指定忽略大小写.

`:s/I/We/gi`

以下命令将文中所有的字符串idiots替换成managers:

`:1,$s/idiots/managers/g`

通常我们用%指代整个文件作为替换范围:

`:%s/search/replace/g`

以下命令指定只在第5 - 15行间进行替换:

`:5,15s/dog/cat/g`

以下命令指定只在当前行在内的以下四行内进行替换:

`:s/hello/hi/g4`

以下命令指定只在后续9行内进行替换:

`:,.+8s/dog/cat/g`

你还可以将特定字符做为替换范围。比如，将SQL语句从FROM至分号部分中的所有等号（=）替换为不等号（<>）：

`:/FORM/,/;/=/<>/g`

## 可视化选择复制

在可视化模式下, 首先选择替换范围, 然后输入 : 进入命令模式, 就可以利用s命令在选中的范围内进行文本替换.

## 精确替换

在搜索sig时, 也将匹配sig, signature, signing等多个单词. 如果希望精确替换某个单词, 可以使用`\<`来匹配单词的开头, 并用`\>`匹配单词的结尾:

`:s/\<term\>/replace/gc`

## 多项替换

如果想将单词Kang和Kodos都替换为alien, 那么可以使用 | 进行多项替换:

`%s/Kang\|Kodos/alien/gc`

## 变量替换

使用以下命令可以将文字替换为变量的内容:

`%s!\~!\= expand($HOME)!g`

## 示例

| 命令                      | 说明                                       | 中文                           |
| ----------------------- | ---------------------------------------- | :--------------------------- |
| `:%s/old/new/g`         | Replace all occurences of *old* by *new* in file | 使用 new 替换文件所有出现的 old         |
| `:%s/onward/forward/gi` | Replace onward by forward, case unsensitive | 使用 forward 替换掉 onward，大小写不敏感 |
| `:%s/old/new/gc`        | Replace all occurences with confirmation |                              |
| `:2,35s/old/new/g`      | Replace all occurences between lines 2 and 35 |                              |
| `:5,$s/old/new/g`       | Replace all occurences from line 5 to EOF |                              |
| `:%s/^/hello/g`         | Replace the begining of each line by *hello* |                              |
| `:%s/$/Harry/g`         | Replace the end of each line by *Harry*  |                              |
| `:%s/onward/forward/gi` | Replace *onward* by *forward*, case unsensitive |                              |
| `:%s/ *$//g`            | Delete all white spaces                  |                              |
| `:g/string/d`           | Delete all lines containing *string*     |                              |
| `:v/string/d`           | Delete all lines containing which didn’t contain *string* |                              |
| `:s/Bill/Steve/`        | Replace the first occurence of *Bill* by *Steve* in current line | 用Steve替换每一行的第一个Bill          |
| `:s/Bill/Steve/g`       | Replace *Bill* by *Steve* in current line |                              |
| `:%s/Bill/Steve`        |                                          | 使用Steve替换所有行的第一个Bill         |
| `:%s/Bill/Steve/g`      | Replace *Bill* by *Steve* in all the file |                              |
| `:%s/^M//g`             | Delete DOS carriage returns (^M)         |                              |
| `:%s/\r/\r/g`           | Transform DOS carriage returns in returns |                              |
| `:%s#<[^>]\+>##g`       | Delete HTML tags but keeps text          |                              |
| `:%s/^\(.*\)\n\1$/\1/`  | Delete lines which appears twice         |                              |
| `Ctrl+a`                | Increment number under the cursor        |                              |
| `Ctrl+x`                | Decrement number under cursor            |                              |
| `ggVGg?`                | Change text to Rot13                     |                              |

**示例**

```shell
:s/Linux/RHEL5		# 指将当前行中的第一个linux换为RHEL5
:s/Linux/RHEL5/g	# 指将当前行中所有的linux换为RHEL5
:%s/Linux/RHEL5		# 指将文件中每一行的第一个linux换为RHEL5
:%s/Linux/RHEL5/g	# 整个文档范围内的linux换为RHEL5
```



## 大小写

| 命令                | 说明                                       |
| ----------------- | ---------------------------------------- |
| `Vu`              | Lowercase line                           |
| `VU`              | Uppercase line                           |
| `g~~`             | Invert case                              |
| `vEU`             | Switch word to uppercase                 |
| `vE~`             | Modify word case                         |
| `ggguG`           | Set all text to lowercase                |
| `gggUG`           | Set all text to uppercase                |
| `:set ignorecase` | Ignore case in searches                  |
| `:set smartcase`  | Ignore case in searches excepted if an uppercase letter is used |
| `:%s/\<./\u&/g`   | Sets first letter of each word to uppercase |
| `:%s/\<./\l&/g`   | Sets first letter of each word to lowercase |
| `:%s/.*/\u&`      | Sets first letter of each line to uppercase |
| `:%s/.*/\l&`      | Sets first letter of each line to lowercase |



## 读写文件

| 命令                   | 说明                                       |
| -------------------- | ---------------------------------------- |
| `:1,10 w outfile`    | Saves lines 1 to 10 in *outfile*         |
| `:1,10 w >> outfile` | Appends lines 1 to 10 to *outfile*       |
| `:r infile`          | Insert the content of *infile*           |
| `:23r infile`        | Insert the content of *infile* under line 23 |



## 文件浏览器

| 命令                       | 说明                                       |
| ------------------------ | ---------------------------------------- |
| `:e .`                   | Open integrated file explorer            |
| `:Sex`                   | Split window and open integrated file explorer |
| `:Sex!`                  | Same as *:Sex* but split window vertically |
| `:browse e`              | Graphical file explorer                  |
| `:ls`                    | List buffers                             |
| `:cd ..`                 | Move to parent directory                 |
| `:args`                  | List files                               |
| `:args *.php`            | Open file list                           |
| `:grep expression *.php` | Returns a list of .php files contening *expression* |
| `gf`                     | Open file name under cursor              |

## 和 Unix 系统交互

| 命令      | 说明                                       |
| ------- | ---------------------------------------- |
| `:!pwd` | Execute the *pwd* unix command, then returns to Vi |
| `!!pwd` | Execute the *pwd* unix command and insert output in file |
| `:sh`   | Temporary returns to Unix                |
| `$exit` | Retourns to Vi                           |

## 对齐

| 命令       | 说明                                      |
| -------- | --------------------------------------- |
| `:%!fmt` | Align all lines                         |
| `!}fmt`  | Align all lines at the current position |
| `5!!fmt` | Align the next 5 lines                  |

## Tabs/Windows

| 命令                    | 说明                            |
| --------------------- | ----------------------------- |
| `:tabnew`             | Creates a new tab             |
| `gt`                  | Show next tab                 |
| `:tabfirst`           | Show first tab                |
| `:tablast`            | Show last tab                 |
| `:tabm n(position)`   | Rearrange tabs                |
| `:tabdo %s/foo/bar/g` | Execute a command in all tabs |
| `:tab ball`           | Puts all open files in tabs   |
| `:new abc.txt`        | Edit *abc.txt* in new window  |

## 多文件

```shell

```

## 分屏显示

| 命令                | 说明                                   |
| ----------------- | ------------------------------------ |
| `:e filename`     | Edit *filename* in current window    |
| `:split filename` | Split the window and open *filename* |
| `ctrl-w up arrow` | Puts cursor in top window            |
| `ctrl-w ctrl-w`   | Puts cursor in next window           |
| `ctrl-w_`         | Maximize current window vertically   |
| `ctrl-w\|`        | Maximize current window horizontally |
| `ctrl-w=`         | Gives the same size to all windows   |
| `10 ctrl-w+`      | Add 10 lines to current window       |
| `:vsplit file`    | Split window vertically              |
| `:sview file`     | Same as **:split** in readonly mode  |
| `:hide`           | Close current window                 |
| `:­nly`           | Close all windows, excepted current  |
| `:b 2`            | Open #2 in this window               |

### 调整窗口大小

```shell
## 高度
:resize 60
:res 60
:res +5
:res -5

## 宽度
:vertical resize 80
:vertical resize +5
:vertical resize -5

## hot key
nnoremap <silent> <Leader>+ :exe "resize " . (winheight(0) * 3/2)<CR>
nnoremap <silent> <Leader>- :exe "resize " . (winheight(0) * 2/3)<CR>

## ps
default <Leader> is \
```

## 自动完成

| 命令                               | 说明                             |
| -------------------------------- | ------------------------------ |
| `Ctrl+n Ctrl+p (in insert mode)` | Complete word                  |
| `Ctrl+x Ctrl+l`                  | Complete line                  |
| `:set dictionary=dict`           | Define *dict* as a dictionnary |
| `Ctrl+x Ctrl+k`                  | Complete with dictionnary      |

## Marks

| 命令        | 说明                                |
| --------- | --------------------------------- |
| `m {a-z}` | Marks current position as *{a-z}* |
| `' {a-z}` | Move to position *{a-z}*          |
| `''`      | Move to previous position         |

## 缩写

| 命令                           | 说明                                       |
| ---------------------------- | ---------------------------------------- |
| `:ab mail mail@provider.org` | Define *mail* as abbreviation of *mail@provider.org* |

## 文本缩进

**缩进多行**

- 按v进入visual状态，选择多行，用>或<缩进或缩出.

| 命令                  | 说明                                  |
| ------------------- | ----------------------------------- |
| `:set autoindent`   | Turn on auto-indent                 |
| `:set smartindent`  | Turn on intelligent auto-indent     |
| `:set shiftwidth=4` | Defines 4 spaces as indent size     |
| `ctrl-t, ctrl-d`    | Indent/un-indent in insert mode     |
| `>>`                | Indent                              |
| `<<`                | Un-indent                           |
| `=%`                | Indent the code between parenthesis |
| `1GVG=`             | Indent the whole file               |

```shell
# 缩进指定行
# In command mode
5>>

# In Visual Mode
j	# 下一行
j	# 下一行
j	# 下一行
>	# 缩进
```

## 语法高亮

| 命令                 | 说明                           |
| ------------------ | ---------------------------- |
| `:syntax on`       | Turn on syntax highlighting  |
| `:syntax off`      | Turn off syntax highlighting |
| `:set syntax=perl` | Force syntax highlighting    |

## 重复执行

- `.`： 命令可以重复上次普通命令
- `@`： 重复上次ex命令。
- `@@`： 重复执行。

### 参考

- [参考一](http://www.cnblogs.com/maowang1991/p/3572299.html)

## 示例配置

### 一

```shell
"设置语言为英文
let $LANG = 'en'  "set message language  
set langmenu=en   "set menu's language of gvim. no spaces beside '=' 

"兼容性设置
set nocompatible
set backspace=indent,eol,start

"if has("win32")
"	set encoding=gbk
"	set fileencoding=ansi
"	set termencoding=gbk
"else
"	set encoding=utf-8
"	set fileencoding=ansi
"	set termencoding=utf-8
"endif

:filetype plugin indent on		"打开文件类型检测
set showcmd						"在Vim窗口的右下角显示一个完整的命令已经完成的部分
set completeopt=longest,menu	"打开文件类型检测, 加了这句才可以用智能补全
set mouse=a						"使用鼠标
set selection=exclusive
set selectmode=mouse,key
set tabstop=4					"设置tab
set softtabstop=4
set shiftwidth=4
syntax on						"语法高亮
set showcmd						"显示输入的命令
set autoindent					"自动缩进
set	cindent
set smartindent
set showmatch					"高亮显示匹配的括号
set number 						"显示行号
colorscheme desert				"设置配色方案
set guifont=consolas			"设置字体
"set guifont=Courier_New:h12:b  "设置字体示例
set history=1000				"设置历史
"设置状态栏
set laststatus=2
set statusline=%F%m%r%h%w\ [FORMAT=%{&ff}]\ [TYPE=%Y]\[POS=%l,%v][%p%%]\ %{strftime(\"%d/%m/%y\ -\ %H:%M\")} 

autocmd GUIEnter * simalt ~x 	"窗口最大化



"自定义函数 - 新建文件时自动添加头部信息或者按F10添加头信息
"新建.c,.h,.sh,.java文件，自动插入文件头 
autocmd BufNewFile *.cpp,*.[ch],*.sh,*.java exec ":call AppendHeadInfo()" 
map<F10> :call AppendHeadInfo()<CR>
"定义函数AppendHeadInfo，自动插入文件头 
func AppendHeadInfo() 
	"如果文件类型为.sh文件 
	let filename=expand("%")
	let len=strlen(filename)
	let posPoint=strridx(filename,'.')
	let fileformat=strpart(filename,posPoint+1,len)
	if fileformat == 'sh' 
		call setline(1,"\#########################################################################") 
		call append(line("."), "\# File Name: ".expand("%")) 
		call append(line(".")+1, "\# Author: bovenson") 
		call append(line(".")+2, "\# Email:  szhkai@126.com") 
		call append(line(".")+3, "\# Created Time: ".strftime("%Y-%m-%d %H:%M:%S")) 
		call append(line(".")+4, "\#########################################################################") 
		call append(line(".")+5, "\#!/bin/bash") 
		call append(line(".")+6, "") 
	else 
		call setline(1, "\/*************************************************************************") 
		call append(line("."), "	> File Name: ".expand("%")) 
		call append(line(".")+1, "	> Author: bovenson") 
		call append(line(".")+2, "	> Email:  szhkai@126.com") 
		call append(line(".")+3, "	> Created Time: ".strftime("%Y-%m-%d %H:%M:%S")) 
		call append(line(".")+4, " ************************************************************************/") 
		call append(line(".")+5, "")
	endif
	if fileformat == 'cpp'
		call append(line(".")+6, "#include <iostream>")
		call append(line(".")+7, "using namespace std;")
		call append(line(".")+8, "")
		call append(line(".")+9, "int main()")
		call append(line(".")+10, "{")
		call append(line(".")+11, "	return 0;")
		call append(line(".")+12, "}")
		call append(line(".")+13, "")
	endif
	if fileformat == 'c'
		call append(line(".")+6, "#include <stdio.h>")
		call append(line(".")+7, "")
		call append(line(".")+8, "int main()")
		call append(line(".")+9, "{")
		call append(line(".")+10, "	return 0;")
		call append(line(".")+11, "}")
		call append(line(".")+12, "")
	endif
	"if &filetype == 'java'
	"	call append(line(".")+6,"public class ".expand("%")." {")
	"	call append(line(".")+7,"}")
	"endif
	"新建文件后，自动定位到文件末尾
	autocmd BufNewFile * normal G
endfunc 

"自定义函数 - 程序的一键运行与调试
"按F5编译运行
map <F5> :call CompileRunGcc()<CR>
func! CompileRunGcc()
	exec "w"
	if &filetype == 'c'
		exec "!g++ % -o %<"
		exec "! ./%<"
	elseif &filetype == 'cpp'
		exec "!g++ % -o %<"
		exec "! ./%<"
	elseif &filetype == 'java' 
		exec "!javac %" 
		exec "!java %<"
	elseif &filetype == 'sh'
		:!./%
	elseif &filetype == 'py'
		exec "!python %"
		exec "!python %<"
	endif
endfunc
"C,C++的调试
map <F8> :call Rungdb()<CR>
func! Rungdb()
	exec "w"
	exec "!g++ % -g -o %<"
	exec "!gdb ./%<"
endfunc
```



