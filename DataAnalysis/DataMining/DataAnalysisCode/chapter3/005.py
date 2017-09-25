#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 005.py.py
DATE: 17-9-24 下午7:53
DESC: 餐饮销量数据相关性分析
"""
import pandas as pd


catering_sale = './data/catering_sale_all.xls'  # 餐饮数据
data = pd.read_excel(catering_sale, index_col='日期')

data.corr()     # 相关系数矩阵, 即给出了任意两款菜式之间的相关系数
print(data.corr()['百合酱蒸凤爪'])   # 只显示 百合酱蒸凤爪 与其他菜式的相关系数
print('*' * 50)
print(data['百合酱蒸凤爪'].corr(data['翡翠蒸香茜饺']))
