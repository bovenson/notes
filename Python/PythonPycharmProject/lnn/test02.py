#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: test02.py
DATE: 17-9-8 下午8:25
DESC: 
"""


s = input()
res = 1


def get_imp(s, i):
    _l = 0
    _r = 0
    _p = 0
    for j in range(i):
        if s[j] == "(" and _l >= _r:
            _p += 1
        if s[j] == "(":
            _l += 1
        elif s[j] == ")":
            _r += 1
    return _p - _r


for i in range(len(s)):
    if s[i] == ")":
        res *= get_imp(s, i)
print(res)
