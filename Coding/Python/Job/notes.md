---
title: Python面试笔记
tags:
	- Python
categories:
	- Python

---

# 字典

## 遍历

```python
# Python3
>>> d = {'a': 1, 'b': 2, 'c': 3}
>>> d
{'a': 1, 'b': 2, 'c': 3}
>>> for i in d:
...     print(d[i])
... 
1
2
3

# Python2	注意顺序
>>> d = {'a': 1, 'b': 2, 'c': 3}
>>> d
{'a': 1, 'c': 3, 'b': 2}
>>> for i in d:
...     print d[i]
... 
1
3
2

...

>>> for key, val in d.items():
...     print(key, val)
... 
a 1
b 2
c 3
d 4
```

