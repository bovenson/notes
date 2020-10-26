---
title: Vim Tips
tags: 
    - Vim
categories:
    - Tools
---

# Tips

```shell
# 命令
;  			重复上一个动作
:!! 		重复上一条命令
:shell	运行shell

# 窗口
:tabnew			新建窗口
:resize/res 60/+5/-5	调整窗口高度 
## 宽度
:vertical resize 80

# 复制粘贴
## 复制一个当前单次
byw		b: 到单次首; y: yank; w: for word
## command mode 粘贴内容
C-r"  ": default register

# 目录
## NERDTree
r       刷新光标所在的目录
C       将根路径设置为光标所在的目录
u       设置上级目录为根路径
cd		  设置当前工作路径
m       文件操作：复制、删除、移动等

# 查找
## 文件内查找
/{pattern}
## 跨文件查找:q
:vimgrep /{pattern}/g [file]	# :vimgrep /foobar/g **
:cn[f]		下一个匹配[文件]
:cp[f]		上一个匹配[文件]
:cr/cla   回到开始/结束
:copen		打开匹配列表
```

# 行内移动

```shell
h	 左移一位
l	 右移一位
0	 行首
$  行尾
^  当前行的第一个非空白符位置
fx 移动当当前行的下一个x处
Fx 移动当当前行的上一个x处
tx 移动到x的左边一个位置
w  往后移动一个词
b  往前移动一个词
)  移动到下一个句子
(  移动到上一个句子
```

# 文件内移动

```shell
<C-F>  向下移动一屏
<C-B>  向上移动一屏
G      移动到文件末尾
nG     移动到第n行
gg     文件首
H      移动光标到屏幕上部
M      移动光标到屏幕中部
L      移动光标到屏幕底部
*      移动到光标所在字符串的下个位置
#      移动到光标所在字符串的上个位置
/s     向后搜索字符串s
?s     向前搜索字符串s
ma     打标签，标签名为a
`a     跳转到标签a
`.     跳转到上次编辑的地方
```

# Commands

```shell
# run shell commands
## 1
C-z			vim 后台运行
fg 			调回vim

## 2
:!{cmd}
```

# 查找替换

```shell
# 搜索
[ESC]
/word
n: next; N: previous

# 替换
:%s/foo/bar/g
:5,10s/foo/bar/gc   # with confirm
```

# 多行注释

**注释**

```shell
Esc
Ctrl + v
Shift + i (I)
# select multi lines
# input comments
Esc
```

**取消注释**

```shell
Esc
Ctrl + v
# select 
d / x
```

