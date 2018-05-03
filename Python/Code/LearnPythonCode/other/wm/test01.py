#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: test01.py
DATE: 17-9-10 下午4:06
DESC: 
"""


n = int(input())
arr = [int(i) for i in str(input()).split()]


def solve(start, end):
    if end >= n or start >= end:
        return 0

    _res = 0
    xor = arr[start]
    for i in range(start+1, end+1):
        xor = xor ^ arr[i]
    if xor == 0:
        _res = 1

    _res = max(_res, solve(start+1, end), solve(start, end+1))
    return _res


res = 0
for i in range(1, n):
    res = max(res, solve(0, i), solve(i, n-1)) if arr[i] != 0 else max(res, solve(0, i) + 1, solve(i, n-1) + 1)
print(res)

