# CMakeLists.txt

```shell
$ mkdir cmake-project
$ cd cmake-project
$ touch CMakeLists.txt
```

# Code

```shell
$ mkdir src && vim src/main.cpp
```

# Config

```cmake
cmake_minimum_required(VERSION 3.10)

# set the project name
project(Tutorial)

# add the executable
add_executable(Tutorial src/main.cpp)
```

# Build

```shell
$ mkdir build && cd build
$ cmake ..
$ make
```

# Run

```shell
$ ./Tutorial
Hello, CMake.
```

