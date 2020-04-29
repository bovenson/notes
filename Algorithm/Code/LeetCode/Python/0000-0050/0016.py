#!/bin/python3
# coding: utf-8

"""
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 17:15"


class Solution:
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        _nums = sorted(nums)
        _len = len(_nums)
        _closest = None
        _res = None

        for i in range(_len-2):
            _target = target - _nums[i]
            _l = i + 1
            _r = _len - 1
            while _l < _r:
                _sum = _nums[_l] + _nums[_r]
                _diff = abs(_sum - _target)
                if _res is None or _diff < _closest:
                    _closest = _diff
                    _res = _sum + _nums[i]
                if _sum > _target:
                    _r -= 1
                else:
                    _l += 1
        return _res


if __name__ == "__main__":
    print(Solution().threeSumClosest([-1, 2, 1, 4], 1))
    print(Solution().threeSumClosest([0, 0, 0], 1))
    print(Solution().threeSumClosest([1, 1, 1, 0], 100))
