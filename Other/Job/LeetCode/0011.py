#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0011.py
DATE: 17-9-10 下午7:02
DESC: 
"""

class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        # arr = [int(i) for i in str(input()[0:-2]).split(',')]

        l = 0
        r = len(height) - 1
        res = 0

        while l < r:
            res = max(res, min(height[l], height[r]) * (r - l))
            if height[l] < height[r]:
                l += 1
            else:
                r -= 1

        return res

print(Solution().maxArea([1, 1]))
