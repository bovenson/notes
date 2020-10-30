---
title: mac usage
categories: 
	- [linux, Mac]
tags:
	- usage
date: 2020/10/27 19:00:00
update: 2020/10/27 19:00:00
---

# homebrew

- 安装路径
  - `/usr/local/Cellar`
  - `/usr/local/Caskroom`

- 源
  - [清华源使用帮助](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew/)

# commond line tools

```shell
# install
$ xcode-select --install

# delete
$ sudo rm -rf `xcode-select -p` # 一般会在文件夹 /Library/Developer/CommandLineTools 内

# Problems
## Can’t install the software because it is not currently available from the Software Update server.
# 手动下载
https://developer.apple.com/download/more/?=command%20line%20tools
```

