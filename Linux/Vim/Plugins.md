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
m       文件操作：复制、删除、移动等
```

# vim-javacomplete2

## 安装

```shell
Plugin 'artur-shaik/vim-javacomplete2'
```



