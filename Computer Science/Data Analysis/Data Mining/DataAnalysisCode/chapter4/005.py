#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 005.py
DATE: 17-9-27 下午3:04
DESC: 主成分分析降维
"""
from sklearn.decomposition import PCA
import pandas as pd


input_file = './data/principal_component.xls'
output_file = './output/dimention_reducted.xls'

data = pd.read_excel(input_file, header=None)

pca = PCA()
pca.fit(data)
print(pca.components_)  # 模型的各个特征向量
print('\n\n')
print(pca.explained_variance_ratio_)   # 各个成分各自的方差百分比
