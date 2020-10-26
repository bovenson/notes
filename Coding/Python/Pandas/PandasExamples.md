[toc]

# 打开文件

```python
data = pandas.read_csv('data.csv', delimiter='\t')
```

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

# 选取

## 选取列

```shell
# 列名
df = df[["Age", "Sex"]]

# 通过索引选取
df.iloc[:, 0]	# 第1列
```

## 过滤行

```shell
t_df = t_df[t_df.服务名称 == '离线计算平台']

# loc
df.loc[df['column_name'] == some_value]
df.loc[df['column_name'].isin(some_values)]
df.loc[(df['column_name'] >= A) & (df['column_name'] <= B)]
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

# 连接

```python
# 用法
merge(left, right, how='inner', on=None, left_on=None, right_on=None,
      left_index=False, right_index=False, sort=True,
      suffixes=('_x', '_y'), copy=True, indicator=False)

# 左连接
merge(data_x, data_y, how='left', on='uid')
```

