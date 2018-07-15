---
title: Python函数笔记
tags:
	- Python函数
categories:
	- Python
---

# 定义

```python
# f.py
def f(param):
    print(param)

if __name__ == '__main__':
    f('Hello Python Function')
    
# RUN
bovenson@ThinkCentre:~/Git/notes/Python/Code/LearnPythonCode/function$ python3 f.py 
Hello Python Function
```

# 嵌套

```python
# nest.py 
def f(param):
    def inner(parami):
        print('Inner: ', parami)
    print(param)
    inner('HI')
    

if __name__ == '__main__':
    f('Hello Python Function')
    # f.inner('Hello Inner')	# ERROR: AttributeError: 'function' object has no attribute 'inner'
bovenson@ThinkCentre:~/Git/notes/Python/Code/LearnPythonCode/function$ python3 nest.py 
Hello Python Function
Inner:  HI
```

