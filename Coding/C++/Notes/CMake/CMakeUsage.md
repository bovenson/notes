# 设置cmake最小版本

```cmake
cmake_minimum_required(VERSION 2.8)
```

# 设置项目名称

```cmake
project("...")
```

# 判断OS

```cmake
if (APPLE)
  # do something
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}")
elseif (UNIX)
  # do something
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -pthread")
endif()
```

# 生成共享库

```cmake
add_library(name SHARED src)
```

# 生成可执行文件

```cmake
add_executable(MAIN src/main.cpp)
```

# 包含cmake文件

```cmake
include(path/to/cmake)
```

# 打印消息

```cmake
MESSAGE("msg...")
```

# 指定compiler

```cmake
set(CMAKE_C_COMPILER "gcc-5")
set(CMAKE_CXX_COMPILER "g++-5")
```

# 编译类型

```shell
set(CMAKE_BUILD_TYPE=Release)	# or Debug
```

# 指定FLAGS

```shell
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
```

