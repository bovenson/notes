#!/bin/python3
# coding: utf-8

"""
FILE: 
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-08-05 19:19"

ip = input()
cnt = 0


def get_cnt(m):
    len_m = len(m)
    if len_m == 1:
        return 1
    else:
        zero_start = 0
        zero_tail = 0

        while zero_start < len_m and m[zero_start] == '0':
            zero_start += 1

        while zero_tail < len_m and m[len_m - zero_tail - 1] == '0':
            zero_tail += 1

        if zero_start == len_m:
            return 0
        if zero_start > 0 and zero_tail > 0:
            return 0
        elif zero_start == 0 and zero_tail == 0:
            return len_m
        else:
            return 1


def solve(m, n):
    global cnt
    cnt += get_cnt(m) * get_cnt(n)


for i in range(1, len(ip)):
    solve(ip[:i], ip[i:])

print(cnt)
