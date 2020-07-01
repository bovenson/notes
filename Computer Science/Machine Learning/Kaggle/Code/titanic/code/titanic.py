#!/bin/python3
# coding: utf-8

"""
Kaggle 泰坦尼克
"""
import pandas as pd
__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-12-21 18:29"


# 使用 pandas 获取数据
train_df = pd.read_csv('../data/train.csv')
test_df = pd.read_csv('../data/test.csv')
combine = [train_df, test_df]

# print(train_df.head(3))
print(train_df.columns.values)

train_df.head()