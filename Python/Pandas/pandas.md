# 简介

- 强大的数据分析和探索工具
- 包含高级的数据结构和精巧的工具
- 构建在Numpy之上
- 需要安装`xlrd`和`xlwt`支持excel文件读写

# 数据结构

## Series

- 序列, 类似于一维数组

## DataFrame

- 相当于一张二维的表格, 类似二维数组
- 每一列都是一个Series

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

