---
title: Protocol Buffer
tags:
	- Protocol Buffer
categories:
	- Architecture
---

# 下载

```shell
$ wget https://github.com/protocolbuffers/protobuf/releases/download/v3.6.1/protobuf-cpp-3.6.1.tar.gz
```

# 编译

```shell
$ tar -xzf protobuf-cpp-3.6.1.tar.gz 
$ cd protobuf-3.6.1/
$ mkdir build
$ pwd
/home/bovenson/Git/MI/Other/protobuf-3.6.1
$ ./configure --prefix=/home/bovenson/Git/MI/Other/protobuf-3.6.1/build
$ make -j8
$ make check -j8
$ make install
```

# 找不到依赖库

```shell
$ export LD_LIBRARY_PATH=LD_LIBRARY_PATH:/path/to/protobuf/lib
```



# 参考

- [Github ProtoBuf](https://github.com/protocolbuffers/protobuf/blob/master/src/README.md)

