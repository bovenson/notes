#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: numpy001.py
DATE: 17-9-16 下午4:32
DESC: 
"""


import numpy as np

a = np.array([2, 0, 1, 5])
print(a)
print(a[3:])
print(a.max())
a.sort()
b = np.array([[1, 2, 3], [4, 5, 6]])
print(b * b)
