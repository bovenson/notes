---
title: Numpy
tags: 
	- Python
	- Numpy
categories:
	- Python
---

# 简介

- Numpy提供了数组功能

**示例程序**

 ```python
#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: numpy001.py
DATE: 17-9-16 下午4:32
DESC: 
"""


import numpy as np

a = np.array([2, 0, 1, 5])
print(a)
print(a[3:])
print(a.max())
a.sort()
b = np.array([[1, 2, 3], [4, 5, 6]])
print(b * b)
 ```

# 生成随机数

```shell
>>> np.random.rand(3,2)
array([[ 0.14860045,  0.12085391],
       [ 0.60111547,  0.22030079],
       [ 0.06661041,  0.37634434]])
>>> np.random.rand(3)
array([ 0.00750005,  0.05250986,  0.79538867])
>>> np.random.rand(1)
array([ 0.47296359])
>>> np.random.rand(3, 2, 4)
array([[[ 0.66151425,  0.66879573,  0.38385449,  0.33088673],
        [ 0.95109961,  0.05667467,  0.15692034,  0.72006635]],

       [[ 0.7792517 ,  0.61330583,  0.78158503,  0.00783331],
        [ 0.29853045,  0.31145736,  0.91850084,  0.98779325]],

       [[ 0.12620205,  0.34380978,  0.77586762,  0.78010287],
        [ 0.65628447,  0.37491602,  0.91287225,  0.37615829]]])
```

# 生成服从正态分布随机数

```python
sampleNo = 1000
mu = 3
sigma = 0.1
np.random.seed(0)
s = np.random.normal(mu, sigma, sampleNo )
```

## **参考**

- [参考一](https://www.zhihu.com/question/39823283)