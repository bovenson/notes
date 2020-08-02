[toc]

# globals()

整个程序运行的全局变量

```shell
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/tmp$ tree
.
├── a.py
└── b.py

0 directories, 2 files
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/tmp$ cat a.py 
#!/usr/bin/python
# coding=utf-8

globals()["G"] = "Global Var"
bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/tmp$ cat b.py 
#!/usr/bin/python
# coding=utf-8

from a import *

print(globals()["G"])

bovenson@Dell:~/Git/notes/Python/Code/LearnPythonCode/tmp$ python b.py 
Global Var
```

# import

**包名不能数字开头！**

# Date Time

## date

```shell
import datetime

# today
dt = datetime.date.today()

# year
>>> dt.year
2020

# month
>>> dt.month
8
>>> '%02d' % dt.month
'08'
>>> dt.strftime('%m')
'08'
>>> '{:02d}'.format(dt.month)
'08'
>>> f'{dt.month:02d}'
'08'

# day
>>> dt.day
1
>>> '%02d' % dt.day
'01'

# format
>>> dt.strftime('%y-%m')
'20-08'
>>> dt.strftime('%Y-%m')
'2020-08'
>>> dt.strftime('%Y-%m-%d')
'2020-08-01'

# datetime
>>> now = datetime.datetime.now()
>>> now.year, now.month, now.day, now.hour, now.minute, now.second
(2020, 8, 1, 20, 8, 3)
```

## time

```shell
import datetime

>>> now = datetime.datetime.now()
>>> now.strftime('%H-%M-%S')
'20-10-51'
```

