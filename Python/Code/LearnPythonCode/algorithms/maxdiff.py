#!/bin/python3
# coding: utf-8

"""
FILE: 
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-08-02 15:23"


def max_diff(nums):
    min_val = min_index = max_diff_val = res_i = res_j = None
    for i in range(len(nums)-1, -1, -1):
        if min_val is None:
            min_val = nums[i]
            min_index = i
        elif max_diff_val is None or nums[i] - min_val > max_diff_val:
            max_diff_val = nums[i] - min_val
            res_i = min_index
            res_j = i
        if nums[i] < min_val:
            min_val = nums[i]
            min_index = i

    return res_i, res_j


if __name__ == '__main__':
    l = [12, 2, 4, -3, 25, 19, 27]
    r_i, r_j = max_diff(l)
    print('A:', r_i, ' B:', r_j, ' Diff ', l[r_j] - l[r_i])
