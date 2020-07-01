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

# Compile

**'tr1/functional' file not found**

```shell
#if __cplusplus >= 201103L
#include <functional>
#else
#include <tr1/functional>
#endif

 #if __cplusplus >= 201103L
 typedef std::function<void(boost::shared_ptr<Runnable>)> ExpireCallback;
 #else
 typedef std::tr1::function<void(boost::shared_ptr<Runnable>)> ExpireCallback;
 #endif
```

**flags**

```shell
 -Wno-c++11-narrowing
```

**ordered comparison between pointer and zero**

```shell
writerThreadId_ > (void *)0
```

**reference to 'shared_ptr' is ambiguous**

```shell
shared_ptr -> boost::shared_ptr
```

# Version

```shell
thrift 0.8.0
	- openssl 1.0.2
	- libevent 2.0.22
```

