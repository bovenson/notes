#!/bin/python3
"""
归并排序
"""


def merge(arr, l, mid, r):
    aux = arr[l:r]
    cur = l
    lo = l
    hi = mid
    for i in range(l, r):
        if aux[lo] < aux[hi] and lo < mid:
            arr[cur] = aux[lo]
            lo += 1
        elif hi :


