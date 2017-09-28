#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 001.py
DATE: 17-9-28 上午9:47
DESC: 对某银行在降低贷款拖欠率的数据进行逻辑回归建模
逻辑回归 自动建模
"""
import pandas as pd
from sklearn.linear_model import LogisticRegression as LR
from sklearn.linear_model import RandomizedLogisticRegression as RLR


file_name = './data/bankloan.xls'
data = pd.read_excel(file_name)

x = data.iloc[:, :8].as_matrix()
y = data.iloc[:, 8].as_matrix()

rlr = RLR()  # 建立随机逻辑回归模型，筛选变量
rlr.fit(x, y)   # 训练模型
rlr.get_support()   # 获取特征筛选结果，也可以通过.scores_方法获取各个特征的分数
print(u'通过随机逻辑回归模型筛选特征结束')
print(u'有效特征为: %s' % ','.join(data.columns[rlr.get_support()]))

x = data[data.columns[rlr.get_support()]].as_matrix()   # 筛选好特征

lr = LR()   # 建立逻辑货柜模型
lr.fit(x, y)    # 用筛选后的特征数据来训练模型
print(u'逻辑回归模型训练结束')
print(u'模型的平均正确率为: %s' % lr.score(x, y))    # 给出模型的平均正确率
