---
title: pybind11
---

# 流程

```flow
start=>start: 开始
end=>end: 结束
define_cpp=>operation: 编写C++程序
define_adapter=>operation: 编写Adapter
generate_shared_lib=>operation: 生成共享lib
ref_shared_lib=>operation: Python引用共享lib
call_func=>operation: Python中调用函数

start->define_cpp->define_adapter->generate_shared_lib->ref_shared_lib->call_func->end
```

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

