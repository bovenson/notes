#!/bin/python3
# coding: utf-8

"""
二分排序
"""


def insertion_sort_dichotomy(l):
    """二分排序
    :param l:   带排序数组
    :return:    排序后数组
    """

    for i in range(1, len(l)):
        pick = l[i]
        left = 0
        right = i - 1
        while left <= right:
            mid = (left + right) // 2
            if l[mid] > pick:
                right = mid - 1
            else:
                left = mid + 1
        for j in range(i-1, left-1, -1):
            l[j+1] = l[j]
        l[left] = pick
    return l


if __name__ == "__main__":
    arr = [4, 2, 3, 1, 0, 9, 8, 5, 8, 7, 6]
    print(insertion_sort_dichotomy(arr))

