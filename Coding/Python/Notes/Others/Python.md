---
title: Python使用指南
tags:
	- Python
categories:
	- Python
---

# path

## 判断类型相同

`isinstance(object, *)`

## 当前文件路径

```python
# __file__是当前执行的文件

# 获取当前文件__file__的路径
os.path.realpath(__file__)
os.path.abspath(__file__)

# 获取当前文件__file__的所在目录
os.path.dirname(os.path.realpath(__file__))
```

### realpath 和 abspath

如果是链接文件, 那么`realpath`显示的是所链接文件的路径.

```shell
ubuntu@ThinkCentre:/home/public/tmp$ tree
.
├── txtfile
└── txtfile_ln -> /home/public/tmp/txtfile

0 directories, 2 files
ubuntu@ThinkCentre:/home/public/tmp$ python3
Python 3.5.2 (default, Nov 17 2016, 17:05:23) 
[GCC 5.4.0 20160609] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import os
>>> os.path.realpath('txtfile_ln')
'/home/public/tmp/txtfile'
>>> os.path.abspath('txtfile_ln')
'/home/public/tmp/txtfile_ln'
>>> os.path.abspath('txtfile')
'/home/public/tmp/txtfile'
>>> os.path.realpath('txtfile')
'/home/public/tmp/txtfile'
```

# list

## 排序

```python
#### nums[:]
## 注意id
>>> nums = [1, 2, 2, 3, 3, 3, 3, 6]
>>> id(nums)
4362265608
>>> nums[:] = sorted(set(nums))
>>> id(nums)
4362265608
>>> nums = [1, 2, 2, 3, 3, 3, 3, 6]
>>> id(nums)
4362287304
>>> nums = sorted(set(nums))
>>> id(nums)
4362265608
```

## 两个list 合并

```python
# 1: list的extend方法
>>> l1 = [1, 2, 3]
>>> l2 = [10, 20, 30]
>>> l1.extend(l2)
>>> l1
[1, 2, 3, 10, 20, 30]

# 2: slice(切片)操作
# l1[len(l1):len(l1)] = l2 和上面的方法等价
>>> l1 = [1, 2, 3]
>>> l2 = [10, 20, 30]
>>> l1[len(l1):len(l1)] = l2
>>> l1
[1, 2, 3, 10, 20, 30]
# 切片操作更加灵活,可以出入任意位置
# 例如插入头部:
>>> l1 = [1, 2, 3]
>>> l2 = [10, 20, 30]
>>> l1[0:0] = l2
>>> l1
[10, 20, 30, 1, 2, 3]
```

## 两个dict合并

```python
# 1: update
>>> d1 = {1: 1, 2: 2}
>>> d2 = {2: 3, 3: 3, 4: 4}
>>> d1.update(d2)
>>> d1
{1: 1, 2: 3, 3: 3, 4: 4}
```

# Sys

## 获取命令行参数

```shell
import sys					# 引入sys
for arg in sys.argv[1:]:	# 遍历
	print int(arg)			# 转型并打印
```

