---
title: Pandas 使用示例
tags:
	- Pandas
	- Python
categories:
	- Python
---

# 二维数组转DataFrame

```shell
>>> import pandas as pd
>>> arr = [[1, 2], [3, 4]]
>>> df = pd.DataFrame(arr, columns=['x', 'y'])
>>> df
   x  y
0  1  2
1  3  4
```

