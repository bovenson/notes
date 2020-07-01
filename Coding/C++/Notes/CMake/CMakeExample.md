---
title: CMake Examples
---

`CMakeLists.txt`

**语法示例**

```cmake
cmake_minimum_required(VERSION 2.8)
project("pybindcpp")
add_executable(HELLO src/pybindcpp.cpp)
```

**完整示例**

```cmake
cmake_minimum_required(VERSION 2.8)
project("pybindcpp")

add_executable(HELLO src/pybindcpp.cpp)
```

**编译**

```shell
$ mkdir build && cd build
$ cmake ..
$ make
```

