#!/bin/python3
# coding: utf-8

"""
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 18:38"


class Solution(object):
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        _nums = sorted(nums)
        _len = len(_nums)
        _res = []

        for i in range(_len-3):
            if i > 0 and _nums[i] == _nums[i-1]:
                continue
            for j in range(i+1, _len-2):
                if j > i + 1 and _nums[j] == _nums[j-1]:
                    continue

                _target = target - _nums[i] - _nums[j]
                _l = j + 1
                _r = _len - 1
                while _l < _r:
                    _sum = _nums[_l] + _nums[_r]
                    # print(i, j, _l, _r)
                    # print(_target, _sum)
                    if _sum < _target or (_l > j+1 and _nums[_l] == _nums[_l-1]):
                        _l += 1
                    elif _sum > _target or (_r < _len-1 and _nums[_r] == _nums[_r+1]):
                        _r -= 1
                    else:
                        # print(_target, _sum)
                        _res.append([_nums[i], _nums[j], _nums[_l], _nums[_r]])
                        _r -= 1
                        _l += 1
        return _res


if __name__ == "__main__":
    print(Solution().fourSum([1, 0, -1, 0, -2, 2], 0))
    print(Solution().fourSum([0, 0, 0, 0], 0))
