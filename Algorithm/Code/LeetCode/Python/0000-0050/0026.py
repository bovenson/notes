#!/bin/python3
# coding: utf-8

"""
Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
extra memory.

Example:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-17 23:01"



class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        res = 0
        for i in range(len(nums)):
            if i == 0 or nums[i] != nums[i-1]:
                nums[res] = nums[i]
                res += 1
        return res


if __name__ == "__main__":
    l = [1, 1, 2, 3, 3, 3, 4]
    print Solution().removeDuplicates(l)
    print l
