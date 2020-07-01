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

# 过滤

## 过滤列

```shell
df = pd.read_csv(...)
df = df[["Age", "Sex"]]
```

## 过滤行

```shell
t_df = t_df[t_df.服务名称 == '离线计算平台']
```

## 示例

```shell
# read
t_df = pd.read_csv("fn", index_col=None, header=0)
# filter column
t_df = t_df[['Brand', 'Name', 'Cost']]
# group && sum
t_df = t_df[t_df.Brand == 'Apple'].groupby(['Name'])['Cost'].sum().reset_index()
# sort 
t_df = t_df.sort_values(by=['Cost'], ascending=False)
# save
t_df.to_csv('Cost.cvs')
```

