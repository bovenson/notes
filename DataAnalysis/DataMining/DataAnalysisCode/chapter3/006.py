#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 006.py
DATE: 17-9-26 下午2:42
DESC: 拉格朗日插值代码
"""
import pandas as pd                     # 导入数据分析库pandas
from scipy.interpolate import lagrange  # 导入拉格朗日插值函数

inputfile = './data/catering_sale.xls'  # 销量数据
outputfile = './tmp/sales.xls'          # 输出数据


data = pd.read_excel(inputfile)
data['销量'][(data['销量'] < 400) | (data['销量'] > 5000)] = None     # 过滤异常值， 将其变为空值

# print(data)


# 自定义列向量插值函数
# s为列向量, n为被插值的位置， k为取前后的数据个数, 默认为5

def ployinterp_column(s, n, k=5):
    y = s[list(range(n-k, n)) + list(range(n+1, n+1+k))]   # 取数
    y = y[y.notnull()]  # 剔除空值
    return lagrange(y.index, list(y))(n)    # 插值并返回插值结果


# 逐个元素判断是否需要插值
for i in data.columns:
    for j in range(len(data)):
        if (data[i].isnull())[j]:
            data[i][j] = ployinterp_column(data[i], j)

data.to_excel(outputfile)       # 输出结果, 写入文件
