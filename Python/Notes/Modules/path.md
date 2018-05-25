## # path模块

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

## 获取路径下所有文件及文件夹

### 不含路径

#### 代码

```python
import os

print(os.listdir('.'))
```

#### 输出

```shell
/usr/bin/python3.5 /media/bovenson/Data/workspace/Pycharm/PurePythonProject/pathtest.py
['.idea', 'config.ini', 'config_parser.py', 'example.ini', 'example.log', 'lines.txt', 'log.py', 'logtest.py', 'pathtest.py', 'read_config_file.py', 'Test.py', 'Test01.py', 'Test02.py', 'Test03.py', 'Test04.py', 'Test05.py', 'Test06.py', 'Test07.py', 'Test08.py', 'Test09.py', 'Test10.py', 'url_test.py', 'write_config_file.py', '__pycache__']

Process finished with exit code 0
```

### 包含路径

#### 代码

```python
import os

file_path = '/home'
for item in os.listdir(file_path):
    print(os.path.join(file_path, item))
```

#### 输出

```shell
/usr/bin/python3.5 /media/bovenson/Data/workspace/Pycharm/PurePythonProject/pathtest.py
/home/ubuntu
/home/mint
/home/ftp
/home/public
/home/bovenson
/home/deepin
/home/elementary
/home/lost+found
/home/test.pcap

Process finished with exit code 0
```

## 递归获取某一路径下所有文件及文件夹

```python
#!/usr/bin python3
# coding: utf-8

"""
FILE: test.py
DATE: 17-6-26 上午11:15
DESC: 
"""

import os

p_file_path = '/media/bovenson/Data/媒体/音乐/网易云音乐'

def get_all_file_and_dir_recursion(file_path, recursion=False):
    res = []
    for item in os.listdir(file_path):
        full_path = os.path.join(file_path, item)
        res.append(full_path)
        if recursion and os.path.isdir(full_path):
            res.extend(get_all_file_and_dir_recursion(full_path, recursion=recursion))
    return res

if __name__ == "__main__":
    file_dir_s = get_all_file_and_dir_recursion(p_file_path, True)
    print(len(file_dir_s))
```





## 获取文件当前目录下文件

### 代码

```python
import os
import pathlib

for item in os.listdir('.'):
    if os.path.isfile(item):
        print("Path   : ", item)
        print("Suffix : ", pathlib.PurePath(item).suffix)
        print("Suffixs: ", pathlib.PurePath(item).suffixes)
        print("")
```

### 输出

```shell
/usr/bin/python3.5 /media/bovenson/Data/workspace/Pycharm/PurePythonProject/pathtest.py
Path   :  other.tar.gz
Suffix :  .gz
Suffixs:  ['.tar', '.gz']

Path   :  pathtest.py
Suffix :  .py
Suffixs:  ['.py']


Process finished with exit code 0
```

## 获取当前路径下特定后缀的文件

### 代码

```python
import os
import pathlib
import re

# 打印当前目录所有文件
for item in os.listdir('.'):
    if os.path.isfile(item):
        print(item)
print()

# 前缀
suffix = 'py'

for item in os.listdir('.'):
    # 忽略前面的'.'符号
    if os.path.isfile(item) and re.sub(r'^\.', '', pathlib.PurePath(item).suffix) == re.sub(r'^\.', '', suffix):
        print(item)
```

### 输出

```shell
/usr/bin/python3.5 /media/bovenson/Data/workspace/Pycharm/PurePythonProject/pathtest.py
other.tar.gz
pathtest.py

pathtest.py

Process finished with exit code 0
```

### 另一个

#### 代码

```python
import os
import pathlib
import re

suffix = 'py'

for item in os.listdir('.'):
    if os.path.isfile(item):
        print(item)

print()

# 如果给定的后缀不是'.'开头, 则添加'.'
suffix = suffix if suffix.startswith('.') else '.' + suffix
for item in os.listdir('.'):
    if os.path.isfile(item) and pathlib.PurePath(item).suffix == suffix:
        print(item)
```

#### 输出

```shell
/usr/bin/python3.5 /media/bovenson/Data/workspace/Pycharm/PurePythonProject/pathtest.py
other.tar.gz
pathtest.py

pathtest.py

Process finished with exit code 0
```

## 判断是否是特定后缀

### 代码

```python
import os
import pathlib


def is_specific_suffix(file_path, suffix):
    # 预处理给定后缀
    suffix = str(suffix).lower()
    suffix = suffix if suffix.startswith('.') else '.' + suffix
    if pathlib.PurePath(file_path).suffix.lower() == suffix:
        return True
    return False

print(is_specific_suffix("a.txt", 'txt'))
print(is_specific_suffix("a.txt", 'TXT'))
print(is_specific_suffix("a.txt", '.txt'))
print(is_specific_suffix("a.TXT", '.txt'))
print(is_specific_suffix("a.TXT", '.md'))
```

### 输出

```shell
/usr/bin/python3.5 /media/bovenson/Data/workspace/Pycharm/PurePythonProject/pathtest.py
True
True
True
True
False

Process finished with exit code 0
```

## 获取特定目录下特定后缀文件

### 代码

```python
def get_specific_file_list(dir_path=None, suffix=None, recursion=False):
    """
    获取目录下特定后缀文件
    :param dir_path: 目录路径
    :param suffix: 后缀, str或者list
    :param recursion: 是否递归搜索子文件夹
    :return: 文件列表
    """
    _res = []
    dir_path = os.path.abspath(dir_path)
    try:
        if dir_path is None or suffix is None or not os.path.exists(dir_path):
            return _res
        for item in os.listdir(dir_path):
            item = os.path.join(dir_path, item)

            # 如果是文件
            if os.path.isfile(item):
                # 如果后缀是字符串
                if isinstance(suffix, str) and is_specific_suffix(item, suffix):
                    _res.append(os.path.join(dir_path, item))
                # 如果后缀是列表
                elif isinstance(suffix, list):
                    for _suffix in suffix:
                        if is_specific_suffix(item, _suffix):
                            _res.append(os.path.join(dir_path, item))
            elif os.path.isdir(item) and recursion:
                # 如果是路径且递归查找
                _recursion_res = get_specific_file_list(os.path.join(dir_path, item), suffix, recursion=recursion)

                for _t_res in _recursion_res:
                    _res.append(_t_res)
    except:
        # import traceback
        # traceback.print_exc()
        return _res
    return _res


def is_specific_suffix(file_path, suffix):
    # 预处理给定后缀
    suffix = str(suffix).lower()
    suffix = suffix if suffix.startswith('.') else '.' + suffix
    if pathlib.PurePath(file_path).suffix.lower() == suffix:
        return True
    return False


if __name__ == "__main__":
    print(get_specific_file_list("./../../", "mp3", recursion=True))
```

### 输出

```shell
/usr/bin/python3.5 /home/bovenson/PycharmProjects/bovenson/common/util/utils.py
['/home/bovenson/PycharmProjects/bovenson/media/music/Home.mp3', '/home/bovenson/PycharmProjects/bovenson/media/music/Frances - Let It Out.mp3']

Process finished with exit code 0
```

