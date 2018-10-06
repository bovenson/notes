#!/bin/python3
# coding: utf-8

"""
冒泡排序
"""


def bubble_sort(l):
    """
    冒泡排序
    :param l: 带排序列表
    :return: 排序后列表
    """
    for i in range(len(l)-1):
        for j in range(1, len(l) - i):
            if l[j-1] > l[j]:
                t = l[j-1]
                l[j-1] = l[j]
                l[j] = t
    return l


if __name__ == "__main__":
    arr = [3, 2, 1, 5, 6, 9, 0, 4, 2]
    # arr = [1, 2]
    print(bubble_sort(arr))

