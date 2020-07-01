#!/bin/python3
# coding: utf-8

"""
FILE: 
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-08-05 19:02"


s = input().strip()
l = (len(s) + 4) // 4
for i in range(l):
    if i == 0:
        print(s[0:l])
    elif i == l - 1:
        print(s[-(l-2+1):-(l+l-2+1):-1])
    else:
        print(s[-i], ' ' * (l - 2), s[l+i-1], sep='')
