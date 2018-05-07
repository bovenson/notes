---
title: Python2和Python3差异
tags: Python
---

# print

Python3 中`print`不再是语句, 而是函数

```python
#### python2
print 'abc'

#### python3
print('abc')

#### 在2.6+中使用3的print函数
from __future__ import print_function

#### python2 中print不换行
## 以逗号结束
print 1,
```

