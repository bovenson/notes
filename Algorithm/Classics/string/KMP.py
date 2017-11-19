#!/bin/python3
# coding: utf-8

"""
KMP算法
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-19 13:08"


def get_next(f: str)->list:
    _next = [0 for _ in range(len(f)+1)]
    j = 0
    for i in range(1, len(f)):
        while j > 0 and f[i] != f[j]:
            j = _next[j]
        if f[i] == f[j]:
            j += 1
        _next[i+1] = j
    return _next


def kmp(o: str, f: str):
    """
    KMP字符串匹配算法
    :param o: 原始串
    :param f: 带查找串
    :return: 索引
    """
    j = 0
    next = get_next(f)
    for i in range(len(o)):
        while j > 0 and o[i] != f[j]:
            j = next[j]
        if o[i] == f[j]:
            j += 1
        if j == len(f):
            print(i-j+1, o[i-j+1:i+1])
            j = next[j]


if __name__ == "__main__":
    s = "abcdeabccde"
    kmp(s, "abccd")
    kmp(s, "a")
    # print(get_next('aaaaaa'))
