#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 003.py
DATE: 17-9-14 上午10:35
DESC: 
"""

n, k = (int(i) for i in str(input()).split())
nums = sorted([int(i) for i in str(input()).split()])
c = [None for _ in range(100)]

flag = False


def solve(num, cur, cnt):
    if num == 0:
        print('YES')
        for i in range(cnt):
            if i == cnt-1:
                print(c[i])
            else:
                print(c[i], ' ',)
        return True
    elif cur >= len(nums) or num < nums[cur]:
        return False
    else:
        c[cnt] = nums[cur]
        if solve(num-nums[cur], cur+1, cnt+1):
            return True
        elif solve(num, cur+1, cnt):
            return True
        else:
            return False

res = solve(k, 0, 0)
if not res:
    print('NO')

