#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: fp_test_01.py
DATE: 17-9-12 下午8:28
DESC: 
"""
from functools import reduce

a = [1, 2, 3]
b = [i + 2 for i in a]
print(b)

b = map(lambda x: x+2, a)
print(b)
b = list(b)
print(b)

b = [2, 3, 4, 7]
c = map(lambda x, y: x+y, a, b)
c = list(c)
print(c)

n = 3
res = reduce(lambda x,y: x*y, range(1, n+1))
print(res)
