#!/bin/python3
# coding: utf-8

"""
FILE: 
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-08-05 20:12"

n, k = (int(item) for item in input().strip().split())
cnt = [0 for _ in range(n)]
f = [[0 for _ in range(n)] for j in range(n)]
for i in range(n):
    fs = input().strip().split()
    for item in fs:
        f[i][int(item)] = 1

for i in range(n):
    if f[k][i] != 0:
        for j in range(n):
            if f[i][j] != 0 and j != k and f[k][j] == 0:
                cnt[j] += 1

cntt = 0
res = -1
for i in range(n):
    if f[k][i] == 0 and cnt[i] > cntt:
        res = i
        cntt = cnt[i]

print(res)
# print(f)
# print(cnt)


