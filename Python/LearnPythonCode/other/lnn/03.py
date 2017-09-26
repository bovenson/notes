#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 03.py
DATE: 17-9-25 下午7:59
DESC: 
"""


def f(n):
    _mid = n // 2
    _res = [[] for _ in range(n)]
    _c1 = {
        0: '0',
        1: '1',
        8: '8'
    }
    _c2 = {
        6: '6',
        9: '9'
    }
    _c3 = {}
    _c3.update(_c1)
    _c3.update(_c2)
    for i in range(n):
        if i < _mid:
            for j in _c3.keys():
                if i > 0:
                    for _item in _res[i-1]:
                        _res[i].append(_item + _c3[j])
                elif i == 0 and j == 0:
                    continue
                elif i == 0:
                    _res[i].append(_c3[j])
        elif i == _mid and n % 2 == 1:
            for j in _c1.keys():
                if i > 0:
                    for _item in _res[i-1]:

                        pass
                else:
                    pass
        else:
            for _item in _res[i-1]:
                _res[i].append(_item + _item[2 * _mid - i])
    return _res[n-1] if n > 0 else []

print(f(2))
print(f(3))
