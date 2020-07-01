#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: test01.py
DATE: 17-9-8 下午7:57
DESC: 
"""

s = input()
pos = -1
for i in range(1, len(s)):
    flag = True
    j = 0
    _i = i
    while j < len(s) and _i < len(s):
        if s[_i] != s[j]:
            flag = False
            break
        j += 1
        _i += 1
    if flag:
        pos = i
        break

if pos > 0:
    print(s + s[len(s)-pos:])
else:
    print(s * 2)

