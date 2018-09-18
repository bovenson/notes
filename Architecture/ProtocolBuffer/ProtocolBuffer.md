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
## 指定编译目的路径
$ ./configure --prefix=/home/bovenson/Git/MI/Other/protobuf-3.6.1/build	
$ make -j8
$ make check -j8
$ make install

## Problem
## can not be used when making a shared object
## 设置 fPIC 标志	
$ ./configure CXXFLAGS=-fPIC
```

# 找不到依赖库

```shell
$ export LD_LIBRARY_PATH=LD_LIBRARY_PATH:/path/to/protobuf/lib
```

# 使用

## 参数

```shell
--proto_path=PATH
	- 指定搜索proto文件的路径
	- 可以指定多次
	- 按指定顺序搜索proto文件
	- 如果未指定 默认搜索当前路径
--cpp_out=OUT_DIR
--csharp_out=OUT_DIR=OUT_DIR
--java_out=OUT_DIR=OUT_DIR
--javanano_out=OUT_DIR=OUT_DIR
--js_out=OUT_DIR
--objc_out=OUT_DIR
--php_out=OUT_DIR
--python_out=OUT_DIR
--ruby_out=OUT_DIR
	- 指定需要输出的语言类型及路径
```

**Examples**

```shell
# 添加protobuf库文件路径至 LD_LIBRARY_PATH
$ export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:`pwd`/deps/protobuf/lib	

# 生成
`pwd`/deps/protobuf/bin/protoc --proto_path=`pwd`/protobuf/proto --cpp_out=`pwd`/protobuf/gen-cpp define.proto

$ tree -L 3
.
├── build
│   ├── CMakeCache.txt
│   ├── CMakeFiles
│   │   ├── 3.5.1
│   │   ├── cmake.check_cache
│   │   ├── CMakeDirectoryInformation.cmake
│   │   ├── CMakeOutput.log
│   │   ├── CMakeTmp
│   │   ├── feature_tests.bin
│   │   ├── feature_tests.c
│   │   ├── feature_tests.cxx
│   │   ├── Makefile2
│   │   ├── Makefile.cmake
│   │   ├── Progress
│   │   ├── progress.marks
│   │   ├── protobufstruct.dir
│   │   ├── ProtoBufTextFormat.dir
│   │   ├── source.dir
│   │   └── TargetDirectories.txt
│   ├── cmake_install.cmake
│   ├── libprotobufstruct.a
│   └── Makefile
├── build-local.sh
├── build.sh
├── cmake-build-debug
│   ├── CMakeCache.txt
│   ├── CMakeFiles
│   │   ├── 3.12.0
│   │   ├── clion-environment.txt
│   │   ├── clion-log.txt
│   │   ├── cmake.check_cache
│   │   ├── CMakeDirectoryInformation.cmake
│   │   ├── CMakeOutput.log
│   │   ├── CMakeTmp
│   │   ├── feature_tests.bin
│   │   ├── feature_tests.c
│   │   ├── feature_tests.cxx
│   │   ├── Makefile2
│   │   ├── Makefile.cmake
│   │   ├── progress.marks
│   │   ├── protobufstruct.dir
│   │   ├── ProtoBufTextFormat.dir
│   │   ├── source.dir
│   │   └── TargetDirectories.txt
│   ├── cmake_install.cmake
│   ├── libsource.a
│   ├── Makefile
│   ├── ProtoBufDemo.cbp
│   ├── ProtoBufTextFormat
│   └── proto.out
├── CMakeLists.txt
├── conf
│   └── gfs.conf
├── deps
│   └── protobuf
│       ├── bin
│       ├── include
│       └── lib
├── protobuf
│   ├── gen-cpp
│   │   ├── define.pb.cc
│   │   └── define.pb.h
│   └── proto
│       └── define.proto
└── src
    ├── main.cpp
    └── TestProtoBuf.cpp
```

## 类型

### map

`map<key, value> m = 1;`

- `key`
  - 整数 or string
  - 不能为浮点数
  - 不能bytes
- `value`
  - 不能为map
- 不能使用repeated修饰

### any

```c++
// c++
Foo foo = ...;
Any any;
any.PackFrom(foo);
...
if (any.UnpackTo(&foo)) {
  ...
}
```

更多内容参考[官方文档](https://developers.google.com/protocol-buffers/docs/reference/csharp/class/google/protobuf/well-known-types/any)

# 参考

- [Github ProtoBuf](https://github.com/protocolbuffers/protobuf/blob/master/src/README.md)

