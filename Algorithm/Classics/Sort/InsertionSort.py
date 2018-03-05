#!/bin/python3
# coding: utf-8

"""
插入排序
"""

def insertion_sort(l):
    """插入排序
    :param l:   带排序数组
    :return:    排序后数组
    """

    for i in range(1, len(l)):
        j = i - 1
        pick = l[i]
        while j >= 0 and l[j] > pick:
            l[j+1] = l[j]
            j -= 1
        l[j+1] = pick
    return l


if __name__ == "__main__":
    arr = [4, 2, 1, 3, 0, 9, 8, 7, 6, 5, 2]
    print(insertion_sort(arr))

