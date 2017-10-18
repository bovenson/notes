#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 001.py
DATE: 17-9-27 上午9:27
DESC: 数据规范化
"""
import pandas as pd
import numpy as np


datafile = './data/normalization_data.xls'     # 参数初始化
data = pd.read_excel(datafile, header=None)  # 读取数据

print(u'最小-最大规范化')
print((data - data.min()) / (data.max() - data.min()))      # 最小-最大规范化

print('\n' + '+' * 40 + '\n')

print(u'零-均值规范化')
print((data - data.mean()) / data.std())                    # 零-均值规范化

print('\n' + '+' * 40 + '\n')

print(u'小数定标规范化')
print(data / 10 ** np.ceil(np.log10(data.abs().max())))         # 小数定标规范化
