---
title: Preprocessing
tags:
	- Scikit-Learn
	- 机器学习
categories:
	- 机器学习
---

# `Imputer`

用于完成缺失值的插补变换器。

**定义**

`class sklearn.preprocessing.Imputer(missing_values=’NaN’, strategy=’mean’, axis=0, verbose=0, copy=True)`

**参数**

- `missing_values` 
  - integer or `NaN`, optional (`default=”NaN”`)
  - 缺失值的占位符
  - 所有缺失值都会被补全
  - 对于编码为 `np.nan` 的缺失值，使用字符串 “NaN”
- `strategy`
  - string, optional (`default=”mean”`)
  - 补全策略
  - 取值
    - `mean`
    - `median`
    - `most_frequent`
- `axis`
  - integer, optional (`default=0`)
  - 取值
    - `0` : 列 (columns)
    - `1` : 行 (rows)
- `verbose`
  - integer, optional (default=0)
- `copy`
  - boolean, optional (default=True)
  - 取值
    - `True` : 创建数据集 X 的副本
    - `False` : 就地修改
  - 以下情况，总是创建副本
    - 如果 X 不是 `floating` 类型的数组
    - 如果 X 是稀疏的，并且缺失值为 `0`
    - 如果 `axis=0` ，并且 X 被编码为 CSR 矩阵
    - 如果 `axis=1` ，并且 X 被编码为 CSC 矩阵

**属性**

- `statistics_`
  - array of shape (n_features,)

**方法**

- `fit(X, y=None)`
- `fit_transform(X, y=None, **fit_params)`
- `transform(X)`

# 参考

- [参考一](http://scikit-learn.org/stable/modules/generated/sklearn.preprocessing.Imputer.html)
- [参考一](http://scikit-learn.org/stable/modules/preprocessing.html#imputation)