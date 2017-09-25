#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0015.py
DATE: 17-9-25 下午5:12
DESC: 
"""

class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        _s = set(nums)
        _nums = sorted(nums)
        _res = []
        for i in range(len(_nums)):
            for j in range(i+1, len(_nums)):
                _complement = -(_nums[i] + _nums[j])
                if _complement in _s and _nums.index(_complement) > j:
                    _res.append([_nums[i], _nums[j], -(_nums[i]+_nums[j])])
        return _res

print Solution().threeSum([-1,0,1,2,-1,-4])
