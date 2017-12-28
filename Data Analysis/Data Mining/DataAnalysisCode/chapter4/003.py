#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 003.py
DATE: 17-9-27 上午11:04
DESC: 线损率属性构造
"""
import pandas as pd


# initialize parameters
input_file = './data/electricity_data.xls'
output_file = './output/electricity_data_extra.xls'

data = pd.read_excel(input_file)
data[u'线损率'] = (data[u'供入电量'] - data[u'供出电量']) / data[u'供入电量']

data.to_excel(output_file, index=False)
