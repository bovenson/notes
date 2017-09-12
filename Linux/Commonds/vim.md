# vim

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

