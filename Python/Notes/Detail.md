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