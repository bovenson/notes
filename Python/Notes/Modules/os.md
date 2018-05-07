[TOC]

# Python os模块

## 获取当前路径

`os.getcwd()`

## path

### 当前文件路径

```python
# __file__是当前执行的文件

# 获取当前文件__file__的路径
os.path.realpath(__file__)
os.path.abspath(__file__)

# 获取当前文件__file__的所在目录
os.path.dirname(os.path.realpath(__file__))
```

#### realpath 和 abspath

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
## rename

`os.rename(src, dst, *, src_dir_fd=None, dst_dir_fd=None)`

-   重命名文件/文件夹`src`为`dst`
-   如果`dst`是一个存在的文件夹, 会抛出`OSError`异常
-   如果`dst`是一个存在的文件, 默认会被`src`覆盖
-   **注意:**在`windows`系统中, 如果`dst`存在, 无论是文件还是文件夹都会抛出`OSError`异常

## makedirs

-   递归建立文件夹
-   如果文件夹已经存在, 会抛出`FileExistsError`错误

```python
import errno    
import os


def mkdir_p(path):
    try:
        os.makedirs(path)
    except OSError as exc:  # Python >2.5
        if exc.errno == errno.EEXIST and os.path.isdir(path):
            pass
        else:
            raise
```

