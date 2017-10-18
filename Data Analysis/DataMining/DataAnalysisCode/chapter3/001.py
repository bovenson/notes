#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 001.py
DATE: 17-9-20 上午10:30
DESC: 使用Pandas库对excel文件做简单分析
"""
import pandas as pd


catering_sale = './data/catering_sale.xls'
data = pd.read_excel(catering_sale, index_col='日期')     # 读取数据, 制定日期列为索引列
print(data.describe())

'''
运行结果:

/home/public/installed/anaconda3/bin/python3.6 /home/public/Git/notes/DataAnalysis/数据挖掘/DataAnalysisCode/chapter3/001.py
                销量
count   200.000000
mean   2755.214700
std     751.029772
min      22.000000
25%    2451.975000
50%    2655.850000
75%    3026.125000
max    9106.440000

Process finished with exit code 0
'''
