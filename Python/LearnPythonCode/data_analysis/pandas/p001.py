#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: p001.py
DATE: 17-9-18 上午10:52
DESC: 
"""
import pandas as pd


s = pd.Series([1, 2, 3], index=['a', 'b', 'c'])     # 创建一个序列s
d = pd.DataFrame([[1, 2, 3], [4, 5, 6]], columns=['a', 'b', 'c'])   # 创建一个表
d2 = pd.DataFrame(s)

d.head()    # 预览前5行数据
d.describe()    # 数据基本统计量

# 读取文件
pd.read_excel('data.xls')
pd.read_csv('data.csv', encoding='utf-8')
