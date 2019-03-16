# Mac OS X

```shell
## 1 download thrift
https://github.com/apache/thrift/releases

## 2 install requirement

#### 2.1 boost
	$ brew install boost@1.60
#### libevent [optional]
	$ brew install libevent
#### zlib [optional]
	$ brew install zlib
	
## 3 install thrift

## 4 problems
> fatal error: 'thrifty.h' file not found
  $ find . -type f -name '*.ll' -exec sed -i '' 's/thrifty.h/thrifty.hh/g' {} +

> fatal error: 'tr1/functional' file not found

```

```shell
--prefix=/usr/local CXXAUTOFLAGS='-std=c++11 -pthread' CXX=g++-4.9 CC=gcc-4.9
```



**Trash**

```shell
###### 2.1.2 compile
> 官方安装步骤 
	https://www.boost.org/doc/libs/1_68_0/more/getting_started/unix-variants.html
> 下载源码
	https://www.boost.org/users/history/
	$ wget https://dl.bintray.com/boostorg/release/1.68.0/source/boost_1_68_0.tar.bz2
  $ tar --bzip2 -xf boost_1_68_0.tar.bz2
  $ cd boost_1_68_0
  $ sudo ./bootstrap.sh --prefix=/usr/local
  $ sudo ./b2 install
```

