---
title: Vim plugin - Command-T
tags:
    - Vim
    - Linux
categories:
    - Tools
---

# Command-T

## 安装

**安装Ruby**

- 源码安装

  ```shell
  $ ./configure --prefix=/home/work/sunzhenkai/bin/ruby --enable-shared
  $ make -j24
  $ make install
  ```

- 使用ruby-install安装

  - Github

    - https://github.com/postmodern/ruby-install.git

  - 安装

    ```shell
    $ cd ruby-install
    $ ./bin/ruby-install --install-dir /home/work/sunzhenkai/bin/ruby ruby
    ```

**异常**

- 找不到 libruby.so.***

  ```shell
  export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/work/sunzhenkai/bin/ruby/lib
  ```

- Command-T plugin error: could not load the C extension

  ```shell
  $ cd /path/to/command-t
  $ rake make
  ```

**Github**

- https://github.com/wincent/command-t

## 使用

# NERDTree

## 安装

```shell
Plugin 'scrooloose/nerdtree'
```

## 使用

```shell
#### 注册热键
map <C-n> :NERDTreeToggle<CR>

#### 切换工作台和目录
ctrl + w + h    光标 focus 左侧树形目录
ctrl + w + l    光标 focus 右侧文件显示窗口
ctrl + w + w    光标自动在左右侧窗口切换
ctrl + w + r    移动当前窗口的布局位置

# * 为常用
o       在已有窗口中打开文件、目录或书签，并跳到该窗口
go      在已有窗口 中打开文件、目录或书签，但不跳到该窗口
t       在新 Tab 中打开选中文件/书签，并跳到新 Tab
T       在新 Tab 中打开选中文件/书签，但不跳到新 Tab
i       split 一个新窗口打开选中文件，并跳到该窗口
gi      split 一个新窗口打开选中文件，但不跳到该窗口
s       vsplit 一个新窗口打开选中文件，并跳到该窗口
gs      vsplit 一个新 窗口打开选中文件，但不跳到该窗口
!       执行当前文件
O       递归打开选中 结点下的所有目录
m       * 文件操作：复制、删除、移动等
x       收起当前打开的目录
X       收起所有打开的目录
e       以文件管理的方式打开选中的目录
D       删除书签
P       大写，跳转到当前根路径
p       小写，跳转到光标所在的上一级路径
K       跳转到第一个子路径
J       跳转到最后一个子路径
<C-j> 和 <C-k>       在同级目录和文件间移动，忽略子目录和子文件
U       设置上级目录为跟路径，但是维持原来目录打开的状态
R       刷新当前根路径
I       * 显示或者不显示隐藏文件
f       打开和关闭文件过滤器
q       关闭 NERDTree
A       全屏显示 NERDTree，或者关闭全屏

r       * 刷新光标所在的目录
C       * 将根路径设置为光标所在的目录
u       * 设置上级目录为根路径
cd		  * 设置当前工作路径
```

# vim-javacomplete2

## 安装

```shell
Plugin 'artur-shaik/vim-javacomplete2'
```



# YouCompleteMe

```shell
# 1 下载
## .vimrc
Plugin 'Valloric/YouCompleteMe'
## vim
:PluginInstall

# 2 Mac OS
## 2.1 依赖
$ brew install mono
$ brew install go
## 2.2 编译
$ cd ~/.vim/bundle/YouCompleteMe
$ git submodule update --init --recursive
$ ./install.py --all --clangd-completer
```

