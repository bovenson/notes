#!/bin/python3
# coding: utf-8

"""
选择排序
"""

def selection_sort(l):
    """选择排序
    :param l: 带排序数组
    :return: 排序后数组
    """
    for i in range(len(l) - 1):
        _min = l[i]
        _index_min = i
        for j in range(i+1, len(l)):
            if l[j] < _min:
                _min = l[j]
                _index_min = j
        if i != _index_min:
            t = l[i]
            l[i] = _min
            l[_index_min] = t

    return l


if __name__ == "__main__":
    arr = [4, 2, 1, 0, 9, 8, 3, 7 ,5, 6]
    print(selection_sort(arr))

