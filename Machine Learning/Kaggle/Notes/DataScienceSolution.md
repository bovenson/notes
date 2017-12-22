---
title: 泰坦尼克数据科学解决方案
---

[TOC]

# Workflow Stage

根据《Data Science Solutions》，完整的解答工作流程包含七个阶段：

- 问题或课题定义
- 获取训练及测试样例集
- 整理、预处理、清洗数据
- 分析、识别模式、探索数据
- 建模、预测、解决问题
- 可视化、汇报、呈现问题解决步骤和最终解决方案
- 提供或提交结果

上述工作流指出了通用的步骤顺序。但是，有时候会有些例外。

- 可以结合多个步骤在一起。我们可能通过可视化数据来分析它们。
- 将一些步骤提前。我们可能在数据wrangling之前和之后进行分析数据。
- 重复一些步骤多次。比如，可视化步骤可能使用多次。
- 有时候可能抛弃一些步骤。

# Question and problem definition

像Kaggle之类的竞赛平台，常常在给出问题的同时，给出数据集来训练和测试你的数据科学模型。

这里是Titanic Survival问题的定义：

>Knowing from a training set of samples listing passengers who survived or did not survive the Titanic disaster, can our model determine based on a given test dataset not containing the survival information, if these passengers in the test dataset survived or not.

# Workflow goals

数据科学解决方案流程通常有七个主要目标：

- 分类（Classifying）
- 关联（Correlating）
- 转换（Converting）
- Completing
- Correcting
- Creating
- Charting

```python
# data analysis and wrangling
import pandas as pd
import numpy as np
import random as rnd

# visualization
import seaborn as sns
import matplotlib.pyplot as plt
%matplotlib inline	# jupyter notebook 命令, matplotlib绘制的图显示在页面内而不是弹出窗口

# machine learning
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC, LinearSVC
from sklearn.ensemble import RandomForestClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.linear_model import Perceptron
from sklearn.linear_model import SGDClassifier
from sklearn.tree import DecisionTreeClassifier
```

# Acquire data

使用 Pandas 处理数据集。我们首先将训练和测试数据集导入Pandas DataFrames。

```python
# 使用 pandas 获取数据
train_df = pd.read_csv('../data/train.csv')
test_df = pd.read_csv('../data/test.csv')
combine = [train_df, test_df]
```

## 通过描述数据进行分析

在工程的早期，Pandas 也可以帮助描述数据集来解答下面的问题。

### 哪些数据集属性是可用的

```python
print(train_df.columns.values)
```

```shell
['PassengerId' 'Survived' 'Pclass' 'Name' 'Sex' 'Age' 'SibSp' 'Parch'
 'Ticket' 'Fare' 'Cabin' 'Embarked']
```

### 哪些特征是可分类的

这些值将样本分成几组相似的样本。在分类特征中，那些是名义值，序数，比率或基于区间的值？除此之外，这有助于我们选择合适的图表进行可视化。

### 哪些特征是数字的

### 哪些特征是混合类型的



# Reference

- [Ref 1](https://www.kaggle.com/startupsci/titanic-data-science-solutions)

