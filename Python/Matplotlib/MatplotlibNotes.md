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

