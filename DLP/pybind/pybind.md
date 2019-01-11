---
title: pybind11
---

# 编译测试样例

**安装依赖**

```shell
$ apt install python-dev python3-dev cmake git
$ pip3 intall pytest==4.0.2
```

**编译**

```shell
$ git clone https://github.com/pybind/pybind11.git
$ cd pybind11 && mkdir build && cd build
$ cmake ..
$ make check -j8
```

# 