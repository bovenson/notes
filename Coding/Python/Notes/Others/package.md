---
title: Pyton package
tags:
	- Python
categories:
	- Python
---

# Package

- 引用某个python文件，会初始化该文件的全局变量，而且只被初始化一次

```python
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/pyl$ tree
.
├── 002.py
├── a001.py
├── a002.py
├── __init__.py
└── __pycache__
    ├── a001.cpython-36.pyc
    └── a002.cpython-36.pyc

1 directory, 6 files
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/pyl$ cat a001.py 
#!/bin/python3

class A:
    def __init__(self):
        print("init class A")

g = [A()]

def fg():
    # global gb     # NameError: name 'gb' is not defined
    # print(gb)
    g.append(0)
    print("run fg, and g: ", g)
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/pyl$ cat a002.py 
#!/bin/python3

from a001 import fg, g

def cfg():
    g.append("in a001")
    fg()
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/pyl$ cat 002.py 
#!/bin/python3 

from a001 import fg, g
from a002 import cfg

gb = []

g.append('in 002')
fg()
g.append('in 002')
cfg()
g.append('over')
print(g)
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/pyl$ python3 002.py 
init class A
run fg, and g:  [<a001.A object at 0x7f6bf74f3d30>, 'in 002', 0]
run fg, and g:  [<a001.A object at 0x7f6bf74f3d30>, 'in 002', 0, 'in 002', 'in a001', 0]
[<a001.A object at 0x7f6bf74f3d30>, 'in 002', 0, 'in 002', 'in a001', 0, 'over']
```

