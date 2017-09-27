#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: AB.py
DATE: 17-9-27 下午8:15
DESC: 
"""


class AB:
    MAX_N = 50
    MAX_K = 50 * 49 / 2
    def createString(self, N, K):
        _arr = [[] for i in range(50, -1, -1)]
        for i in range(N):
            for j in ['A', 'B']:
                if i == 0:
                    _arr[i].append(j)
                else:
                    for _item in _arr[i-1]:
                        _arr[i].append(_item + j)
        print(_arr)


AB().createString(50, 2)
