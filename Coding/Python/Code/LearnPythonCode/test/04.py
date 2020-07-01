#!/bin/python3
# coding: utf-8

"""
FILE: 
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-08-05 20:25"


_ = input()
nums = [int(item) for item in input().strip().split()]
res = len(nums)
t_longest_sublist = []
longest_sublist = []
t_del_arr = []
del_arr = []


def get_longest_sublist(arr, level, flag):
    print(arr, level)
    global longest_sublist, del_arr, t_del_arr, t_longest_sublist, longest_sublist
    if level >= len(arr):
        if len(t_longest_sublist) > len(longest_sublist):
            longest_sublist = t_longest_sublist.copy()
            del_arr = t_del_arr.copy()
        return

    if flag:
        if len(t_longest_sublist) == 0 or t_longest_sublist[-1] < arr[level]:
            t_longest_sublist.append(arr[level])
            get_longest_sublist(arr, level+1, flag)
            t_longest_sublist.pop(-1)
    else:
        if len(t_longest_sublist) == 0 or t_longest_sublist[-1] > arr[level]:
            t_longest_sublist.append(arr[level])
            get_longest_sublist(arr, level+1, flag)
            t_longest_sublist.pop(-1)

    t_del_arr.append(arr[level])
    get_longest_sublist(arr, level+1, flag)
    t_del_arr.pop(-1)


def solve(arr, level):
    global longest_sublist, del_arr, t_del_arr, t_longest_sublist, longest_sublist, res
    if len(arr) == 0:
        res = min(res, level)
        return

    get_longest_sublist(arr, 0, True)
    tt_lg_sub = t_longest_sublist.copy()
    tt_del_arr = del_arr.copy()
    get_longest_sublist(arr, 0, False)
    ttt_lg_sub = t_longest_sublist.copy()
    ttt_del_arr = del_arr.copy()
    if len(tt_lg_sub) > len(ttt_lg_sub):
        solve(tt_del_arr, level + 1)
    else:
        solve(ttt_del_arr, level + 1)


print(solve(nums, 0))
