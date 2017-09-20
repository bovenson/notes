#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 002.py
DATE: 17-9-20 上午10:36
DESC: 异常值检测
"""
import pandas
import matplotlib.pyplot as plt     # 导入图像库


catering_sale = './data/catering_sale.xls'
data = pandas.read_excel(catering_sale, index_col='日期')     # 读取数据, 制定日期列为索引列

# plt.rcParams['font.sans-serif'] = ['SimHei']    # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号

plt.figure()    # build figure
p = data.boxplot()  # boxplot figure, using
