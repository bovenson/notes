#!/bin/bash
# coding: utf-8

"""
希尔排序
"""


def ShellSort(l):
    """希尔排序
    :param l:   带排序序列
    :return:    排序后序列
    """
    
    n = len(l)
    h = len(l)  # 步长
    while h > 1:
        h = h // 3 if h // 3 >= 1 else 1  # 更新步长
        for i in range(h, n):
            j = i
            while j >= h and l[j] < l[j-h]:
                t = l[j]
                l[j] = l[j-h]
                l[j-h] = t
                j -= h
    return l


if __name__ == "__main__":
    arr = [1, 6, 4, 3, 2, 5, 9, 0, 8, 7]
    print(ShellSort(arr))

