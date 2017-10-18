#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 003.py
DATE: 17-9-22 下午3:58
DESC: Analysis on sales data of catering
"""


import pandas as pd

catering_sale = './data/catering_sale.xls'  # sales data of catering
data = pd.read_excel(catering_sale, index_col='日期')     # read data; specify the data as index
data = data[(data['销量'] > 400) & (data['销量'] < 5000)]   # filter out the exception data

statistics = data.describe()

statistics.loc['range'] = statistics.loc['max'] - statistics.loc['min']
statistics.loc['var'] = statistics.loc['std'] / statistics.loc['mean']
statistics.loc['dis'] = statistics.loc['75%'] - statistics.loc['25%']

print(statistics)
