#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: random.py
DATE: 17-7-19 上午10:14
DESC: 
"""
import numpy as np


# print(np.float32(np.random.rand(2, 100)))
data_1 = np.random.rand(2, 2)
data_2 = np.random.rand(2, 2)
print(data_1)
print(data_2)
print(data_2 + 5)
print(np.dot(data_1, data_1))
