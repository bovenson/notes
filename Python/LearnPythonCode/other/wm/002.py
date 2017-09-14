#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 002.py
DATE: 17-9-14 上午9:47
DESC: 
"""


P, R = (int(i) for i in str(input()).split())
Rsi = [i for i in str(input()).split()]
Rs = {}

MAX_DIS = int(pow(2, 10))

for i in range(len(Rsi)):
    if Rs.get(Rsi[i]) is None:
        Rs[Rsi[i]] = len(Rs.keys())

dis = [[MAX_DIS if i != j else 0 for i in range(P)] for j in range(P)]
pt = [[-1 if i != j else i for i in range(P)] for j in range(P)]

i = 0
while i < len(Rsi):
    _a, _b = (Rsi[i], Rsi[i+1])
    _i_a = Rs.get(_a)
    _i_b = Rs.get(_b)
    dis[_i_a][_i_b] = 1
    pt[_i_a][_i_b] = int(i / 2)
    pt[_i_b][_i_a] = int(i / 2)
    i += 2

res = 0
for k in range(P):
    for i in range(P):
        for j in range(P):
            if dis[i][k] + dis[k][j] < dis[i][j] and dis[i][k] + dis[k][j] < MAX_DIS:
                dis[i][j] = dis[i][k] + dis[k][j]
                pt[i][j] = k
                pt[j][i] = k
                res = max(res, dis[i][j])


v = set()

for i in range(P):
    for j in range(P):
        if i != j and dis[i][j] < MAX_DIS:
            if len(v) == 0:
                v.add(i)
                v.add(j)
            elif i in v or j in v:
                v.add(i)
                v.add(j)

if len(v) == len(Rs.keys()):
    print(res)
else:
    print("DISCONNECTED")
