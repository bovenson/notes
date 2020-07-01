#!/bin/python3
# coding: utf-8

"""
散点图示例
"""
from matplotlib.font_manager import FontProperties

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-05-08 19:32"

from openpyxl import load_workbook
import matplotlib.pyplot as plt


def getChineseFont():
    return FontProperties(fname='/home/bovenson/Downloads/msyh_consola.ttf')


plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号
plt.rcParams['figure.figsize'] = 12, 6

workbook = load_workbook('data.xlsx', data_only=True)

x = []
values = [[], [], [], []]
keys = [u'AP1', 'AP2', u'AP3', u'AP4']

worksheet = workbook['4']
# 获取所有行
rows = worksheet.rows
for row in rows:
    # 将一行不为None的列组成列表
    # print([col.value for col in row if col.value is not None])
    values[0].append(row[0].value)
    values[1].append(row[1].value)
    values[2].append(row[2].value)
    values[3].append(row[3].value)
    x.append(len(x))

# 画图
# plt.scatter(x, values[0], s=10, c='r', marker="s", label=keys[0])
# plt.scatter(x, values[1], s=10, c='b', marker="x", label=keys[1])
# plt.scatter(x, values[2], s=10, c='g', marker="^", label=keys[2])
# plt.scatter(x, values[3], s=10, c='k', marker="*", label=keys[3])

# 折线图
plt.plot(x, values[0], c='r', marker="s", label=keys[0])
plt.plot(x, values[1], c='b', marker="x", label=keys[1])
plt.plot(x, values[2], c='g', marker="^", label=keys[2])
plt.plot(x, values[3], c='k', marker="*", label=keys[3])
plt.xlabel('路线上参考点编号', fontproperties=getChineseFont())
plt.ylabel('接收信号强度（dBm）', fontproperties=getChineseFont())
plt.legend(loc='top right', prop=getChineseFont())
plt.show()
