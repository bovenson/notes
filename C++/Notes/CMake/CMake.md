---
title: CMake 笔记
tags:
	- CMake
categoires:
	- C++
---

# 简介

CMake是一个比make更高级的编译配置工具，它可以根据不同平台、不同的编译器，生成相应的Makefile或者vcproj项目。通过编写CMakeLists.txt，可以控制生成的Makefile，从而控制编译过程。CMake自动生成的Makefile不仅可以通过make命令构建项目生成目标文件，还支持安装（make install）、测试安装的程序是否能正确执行（make test，或者ctest）、生成当前平台的安装包（make package）、生成源码包（make package_source）、产生Dashboard显示数据并上传等高级功能，只要在CMakeLists.txt中简单配置，就可以完成很多复杂的功能，包括写测试用例。如果有嵌套目录，子目录下可以有自己的CMakeLists.txt。

## 步骤

- 编写`CMakeLists.txt`
- 执行命令`cmake PATH` 或 `ccmake PATH` 生成`Makefile`
- 使用`make`命令编译

# 语法

CMakeLists.txt 的语法比较简单，由命令、注释和空格组成。`#` 后面的是注释。

`commond (args ...)`

- commond为命令名，大小写不敏感
- args为参数
  - 如果包含空格，使用双引号

**变量引用**

变量引用用 `${VAR}` 语法

# 命令

## **`set`**

`set`命令将多变量放在一起。

```cmake
set (Foo a b c)
```

- `commond(${Foo})` 等价于 `command(a b c)`
- `commond("${Foo}")` 等价于 `command("a b c")`

## **`cmake_minimum_required`**

指定运行此配置文件所需的CMake的最低版本。

`cmake_minimum_required (VERSION 2.8)`

## **`project`**

指定项目信息。

```cmake
project (Demo)
```

## `add_executable`

指定生成目标。

```cmake
add_executable(Demo demo.cpp)
```

## `aux_source_directory`

查找指定目录下的所有源文件，将结果存进指定变量名。

```cmake
aux_source_directory(<dir> <variable>)
```

```cmake
cmake_minimum_required (VERSION 2.8)
project (Demo)
aux_source_directory (. DIR_SRCS)
add_executable(Demo ${DIR_SRCS})
```

## `add_subdirectory`

指明项目包含一个子目录，这样该目录下的`CMakeLists.txt`文件和源文件也会被处理。

```cmake
add_subdirectory(math)
```

## `target_link_libraries`

指明可执行文件需要链接的库。

```cmake
target_link_libraries(Demo MathFunctions)
```

## `add_library`

将指定的源文件生成链接文件，然后添加到工程中去

```cmake
aux_source_directory(. DIR_LIB_SRCS)
add_library(MathFunctions ${DIR_LIB_SRCS})
```

## `link_directories`

指定连接器查找库的文件夹。此命令的相对路径被解释为相对于当前源目录。

**格式**

```shell
link_directories(directory1 directory2 ...)
```

**示例** 

```cmake
link_directories(${PROJECT_BINARY_DIR}/third_party/googletest/)
```

## `include_directories`

将给定的目录添加到编译器用来搜索头文件的目录中。相对路径被解释为相对于当前源目录。

**格式**

```cmake
include_directories([AFTER|BEFORE] [SYSTEM] dir1 [dir2 ...])
```

**示例**

```cmake
include_directories(${PROJECT_SOURCE_DIR}/third_party/glog/)
include_directories(${PROJECT_SOURCE_DIR}/third_party/hdfs/)
```

# 变量

## 常用变量

- PROJECT_BINARY_DIR
  - Full path to build directory for project.
- PROJECT_SOURCE_DIR
  - Top level source directory for the current project.
- CMAKE_CURRENT_SOURCE_DIR
  - This the full path to the source directory that is currently being processed by cmake.

# 示例

```shell
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ tree
.
├── CMakeLists.txt
└── t.cpp

0 directories, 2 files
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ cat t.cpp 
#include <iostream>

using namespace std;

int main() {
    cout<<"Hello"<<endl;
    return 0;
}
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ cat CMakeLists.txt 
project("test")
add_executable(Hello t.cpp)
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ cmake .
-- The C compiler identification is GNU 5.4.0
-- The CXX compiler identification is GNU 5.4.0
-- Check for working C compiler: /usr/bin/cc
-- Check for working C compiler: /usr/bin/cc -- works
-- Detecting C compiler ABI info
-- Detecting C compiler ABI info - done
-- Detecting C compile features
-- Detecting C compile features - done
-- Check for working CXX compiler: /usr/bin/c++
-- Check for working CXX compiler: /usr/bin/c++ -- works
-- Detecting CXX compiler ABI info
-- Detecting CXX compiler ABI info - done
-- Detecting CXX compile features
-- Detecting CXX compile features - done
-- Configuring done
-- Generating done
-- Build files have been written to: /home/bovenson/Git/notes/C++/Code/CMake/test
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ tree
.
├── CMakeCache.txt
├── CMakeFiles
│   ├── 3.5.1
│   │   ├── CMakeCCompiler.cmake
│   │   ├── CMakeCXXCompiler.cmake
│   │   ├── CMakeDetermineCompilerABI_C.bin
│   │   ├── CMakeDetermineCompilerABI_CXX.bin
│   │   ├── CMakeSystem.cmake
│   │   ├── CompilerIdC
│   │   │   ├── a.out
│   │   │   └── CMakeCCompilerId.c
│   │   └── CompilerIdCXX
│   │       ├── a.out
│   │       └── CMakeCXXCompilerId.cpp
│   ├── cmake.check_cache
│   ├── CMakeDirectoryInformation.cmake
│   ├── CMakeOutput.log
│   ├── CMakeTmp
│   ├── feature_tests.bin
│   ├── feature_tests.c
│   ├── feature_tests.cxx
│   ├── Hello.dir
│   │   ├── build.make
│   │   ├── cmake_clean.cmake
│   │   ├── DependInfo.cmake
│   │   ├── depend.make
│   │   ├── flags.make
│   │   ├── link.txt
│   │   └── progress.make
│   ├── Makefile2
│   ├── Makefile.cmake
│   ├── progress.marks
│   └── TargetDirectories.txt
├── cmake_install.cmake
├── CMakeLists.txt
├── Makefile
└── t.cpp

6 directories, 31 files
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ make
Scanning dependencies of target Hello
[ 50%] Building CXX object CMakeFiles/Hello.dir/t.cpp.o
[100%] Linking CXX executable Hello
[100%] Built target Hello
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ tree
.
├── CMakeCache.txt
├── CMakeFiles
│   ├── 3.5.1
│   │   ├── CMakeCCompiler.cmake
│   │   ├── CMakeCXXCompiler.cmake
│   │   ├── CMakeDetermineCompilerABI_C.bin
│   │   ├── CMakeDetermineCompilerABI_CXX.bin
│   │   ├── CMakeSystem.cmake
│   │   ├── CompilerIdC
│   │   │   ├── a.out
│   │   │   └── CMakeCCompilerId.c
│   │   └── CompilerIdCXX
│   │       ├── a.out
│   │       └── CMakeCXXCompilerId.cpp
│   ├── cmake.check_cache
│   ├── CMakeDirectoryInformation.cmake
│   ├── CMakeOutput.log
│   ├── CMakeTmp
│   ├── feature_tests.bin
│   ├── feature_tests.c
│   ├── feature_tests.cxx
│   ├── Hello.dir
│   │   ├── build.make
│   │   ├── cmake_clean.cmake
│   │   ├── CXX.includecache
│   │   ├── DependInfo.cmake
│   │   ├── depend.internal
│   │   ├── depend.make
│   │   ├── flags.make
│   │   ├── link.txt
│   │   ├── progress.make
│   │   └── t.cpp.o
│   ├── Makefile2
│   ├── Makefile.cmake
│   ├── progress.marks
│   └── TargetDirectories.txt
├── cmake_install.cmake
├── CMakeLists.txt
├── Hello
├── Makefile
└── t.cpp

6 directories, 35 files
bovenson@HP:~/Git/notes/C++/Code/CMake/test$ ./Hello 
Hello
```

# 参考

- [CMake学习之路](https://www.cnblogs.com/qixianyu/p/6574048.html)
- [CMake 入门实战](http://www.hahack.com/codes/cmake/)