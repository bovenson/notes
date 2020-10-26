[toc]

# 简介

- 强大的数据分析和探索工具
- 包含高级的数据结构和精巧的工具
- 构建在Numpy之上
- 需要安装`xlrd`和`xlwt`支持excel文件读写

# 数据结构

## Series

- 序列, 类似于一维数组

### 获取值

```python
s.values
```

## DataFrame

- 相当于一张二维的表格, 类似二维数组
- 每一列都是一个Series

# 处理CSV文件

```python
#### 读取csv文件，并转化为DataFrame
train_df = pd.read_csv('../data/train.csv')
```

# DataFrame方法

**选取数据**

- `iloc`
- `DF.clomun_name` : 通过列名选择

## `iloc`

从DataFrame中选择数据

```python
df.iloc[:]		# 选择所有行，所有列
df.iloc[:, :4]	# 选择所有行，前四列
df.iloc[[1, 2], [0, 1]]	# 选择第2,3 行， 第1,2列
```

**示例程序**

```shell
### With Series
bovenson@ThinkCentre:~/Git/notes/Python/Pandas$ python3
Python 3.5.3 (default, Jan 19 2017, 14:11:04) 
[GCC 6.3.0 20170118] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import pandas as pd
>>> import numpy as np
>>> s1 = pd.Series(np.random.randn(5), index=list(range(0, 10, 2)))
>>> s1
0    0.611873
2    1.709006
4   -0.191763
6    1.393935
8   -0.751302
dtype: float64
>>> s1.iloc[:3]
0    0.611873
2    1.709006
4   -0.191763
dtype: float64
>>> s1.iloc[3]
1.3939352812007684
>>> s1.iloc[:3] = 0
>>> s1
0    0.000000
2    0.000000
4    0.000000
6    1.393935
8   -0.751302
dtype: float64


#### With DataFrame
>>> df = pd.DataFrame(np.random.randn(6, 4), index=list(range(0, 12, 2)), columns=list(range(0, 8, 2)))
>>> df
           0         2         4         6
0   0.544588 -0.943141  0.656372 -0.468110
2  -2.093147 -0.122080  0.218309  0.172868
4  -0.529067 -0.418202  1.217741 -0.959730
6  -0.388783 -0.493095 -0.222721  1.224777
8   1.110017  0.786962  1.434934 -1.052975
10  1.945774  0.274088 -0.724777 -0.469002
>>> df.iloc[:3]
          0         2         4         6
0  0.544588 -0.943141  0.656372 -0.468110
2 -2.093147 -0.122080  0.218309  0.172868
4 -0.529067 -0.418202  1.217741 -0.959730
>>> df.iloc[1:5, 2:4]
          4         6
2  0.218309  0.172868
4  1.217741 -0.959730
6 -0.222721  1.224777
8  1.434934 -1.052975
>>> df.iloc[[1, 2], [0, 1]]
          0         2
2 -2.093147 -0.122080
4 -0.529067 -0.418202
>>> df.iloc[:, :3]
           0         2         4
0   0.544588 -0.943141  0.656372
2  -2.093147 -0.122080  0.218309
4  -0.529067 -0.418202  1.217741
6  -0.388783 -0.493095 -0.222721
8   1.110017  0.786962  1.434934
10  1.945774  0.274088 -0.724777
>>> df.iloc[1, 1]
-0.12207998453906502
>>> df.iloc[1]
0   -2.093147
2   -0.122080
4    0.218309
6    0.172868
Name: 2, dtype: float64
```

## `mean`

计算均值

```python
import pandas as pd

# 加载数据
train_df = pd.read_csv('./data/train.csv')

# 计算所有数值均值
train_df.mean()

# 计算某一列均值
train_df['Age'].mean()
```

## `dropna`

```python
DataFrame.dropna(axis=0, how='any', thresh=None, subset=None, inplace=False)
```

- `axis` 
  - `0` : 行
  - `1` : 列
  - 元组/列表 : 处理多维数据
- `how`
  - `any` : 只要有一个值为`NA`，则删除该标签
  - `all` : 所有值均为`NA`，则删除该标签
- `thresh`
  - 整数值，默认为空
  - 如果非`NA`值数量大于该值，则保留该标签
- `subset`
  - 序列格式参数(`array-like`)
  - 只考虑给定标签的值
- `inplace`
  - `True` : 在原始数据集上进行操作，返回`None`
  - `False`  : 返回新的数据集，保持原始数据集不变

## `drop`



# 示例程序

```python
import pandas as pd


s = pd.Series([1, 2, 3], index=['a', 'b', 'c'])     # 创建一个序列s
d = pd.DataFrame([[1, 2, 3], [4, 5, 6]], columns=['a', 'b', 'c'])   # 创建一个表
d2 = pd.DataFrame(s)

d.head()    # 预览前5行数据
d.describe()    # 数据基本统计量

# 读取文件
pd.read_excel('data.xls')
pd.read_csv('data.csv', encoding='utf-8')
```

