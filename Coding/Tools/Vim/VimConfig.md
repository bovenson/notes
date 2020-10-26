---
title: Vim配置
tags: Vim, Linux
author: bovenson
email: szhkai@qq.com
---

[TOC]

# 配置选项

```shell
# 设置行号
set number

# 语法高亮
syntax on

# 鼠标
## 可用
set mouse=a

# tab
set tabstop=4
set shiftwidth=4
set expandtab
set smarttab

# 对齐与缩进
## 自动对齐
set autoindent
## 智能缩进
set smartindent

# 显示匹配
set showmatch

# 按键映射
inoremap jj <Esc>
```

# 安装插件

**论坛**

- [vimawesome](https://vimawesome.com/)

## 插件管理器

## Vundle

**安装**

```shell
git clone https://github.com/VundleVim/Vundle.vim.git ~/.vim/bundle/Vundle.vim
```

**配置**

```yaml
"""""""""""" vim config

"""""""""""" vbundle setting
set nocompatible              " be iMproved, required
filetype off                  " required

" set the runtime path to include Vundle and initialize
set rtp+=~/.vim/bundle/Vundle.vim
call vundle#begin()
" alternatively, pass a path where Vundle should install plugins
"call vundle#begin('~/some/path/here')

" let Vundle manage Vundle, required
Plugin 'VundleVim/Vundle.vim'


:" The following are examples of different formats supported.
" Keep Plugin commands between vundle#begin/end.
" plugin on GitHub repo
Plugin 'tpope/vim-fugitive'

" plugin from http://vim-scripts.org/vim/scripts.html
" Plugin 'L9'
" Git plugin not hosted on GitHub
Plugin 'git://git.wincent.com/command-t.git'

" git repos on your local machine (i.e. when working on your own plugin)
" Plugin 'file:///home/gmarik/path/to/plugin'

" The sparkup vim script is in a subdirectory of this repo called vim.
" Pass the path to set the runtimepath properly.

Plugin 'rstacruz/sparkup', {'rtp': 'vim/'}
" Install L9 and avoid a Naming conflict if you've already installed a
" different version somewhere else.
" Plugin 'ascenator/L9', {'name': 'newL9'}


""""""""""""" MY PLUGIN
Plugin 'rkulla/pydiction'
Plugin 'artur-shaik/vim-javacomplete2'
" Plugin 'klen/python-mode'
""""""""""""" END


" All of your Plugins must be added before the following line
call vundle#end()            " required
filetype plugin indent on    " required
" To ignore plugin indent changes, instead use:
"filetype plugin on
"
" Brief help
" :PluginList       - lists configured plugins
" :PluginInstall    - installs plugins; append `!` to update or just :PluginUpdate
" :PluginSearch foo - searches for foo; append `!` to refresh local cache
" :PluginClean      - confirms removal of unused plugins; append `!` to auto-approve removal
"
" see :h vundle for more details or wiki for FAQ
" Put your non-Plugin stuff after this line
"""""""""""" ./vbundle setting


"""""""""""" personal setting
set nocompatible
set backspace=indent,eol,start
" set mouse=a
set tabstop=4
set softtabstop=4
set guifont=consolas
set shiftwidth=4
set expandtab
set autoindent
set smartindent
set laststatus=2
set history=5000
syntax on
set number

" 设置{回车自动补全并缩进
inoremap { {}<ESC>i
inoremap {<CR> {<CR>}<ESC>O
"""""""""""" ./personal setting

"""""""""""" vbundle plugin setting
" set java autocomplete
autocmd FileType java setlocal omnifunc=javacomplete#Complete
nmap <F4> <Plug>(JavaComplete-Imports-AddSmart)
imap <F4> <Plug>(JavaComplete-Imports-AddSmart)

nmap <F5> <Plug>(JavaComplete-Imports-Add)
imap <F5> <Plug>(JavaComplete-Imports-Add)

nmap <F6> <Plug>(JavaComplete-Imports-AddMissing)
imap <F6> <Plug>(JavaComplete-Imports-AddMissing)

nmap <F7> <Plug>(JavaComplete-Imports-RemoveUnused)
imap <F7> <Plug>(JavaComplete-Imports-RemoveUnused)
"""""""""""" ./vbundle plugin setting
```

**参考**

- [Vundle](https://github.com/VundleVim/Vundle.vim/issues/383)