---
title: Matplotlib Notes
tags:
	- Python
	- Matplotlib
categories:
	- Python
---

# 参数

## alpha

- 透明度
- `float [0, 1]`

## bins

- 分段区间
- 可以是一个整数或一个序列
- 如果是整数n，将会均匀分为n个区间，否则按指定的序列进行分段

# 配置

## subplot

绘制子图。

**原型**

```python
subplot(nrows, ncols, index, **kwargs)
```

**示例**

```python
#!/bin/python3

"""
@author	sunzhenkai
@file	subplot03.py
@date	2019-01-16 19:26:55
@desc	
"""

import matplotlib.pyplot as plt

y = [i for i in range(100)]
z = [i * 1.1 for i in range(100)]

plt.subplot(2, 2, 1)
plt.xlabel('x label')
plt.ylabel('y label')
plt.plot(y, label='y')
plt.plot(z, label='z')
plt.legend()

#### no legend
plt.subplot(2, 2, 2)
plt.xlabel('x label')
plt.ylabel('y label')
plt.plot(y, label='y')
plt.plot(z, label='z')

plt.subplot(2, 2, 4)
plt.xlabel('x label')
plt.ylabel('y label')
plt.plot(y, label='y')
plt.plot(z, label='z')
plt.legend()

# no NO.3 subplot 
plt.show()
```

![](img/04.png)

