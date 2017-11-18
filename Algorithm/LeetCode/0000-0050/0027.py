#!/bin/python3
# coding: utf-8

"""
Given an array and a value, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input
array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-17 23:19"


class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        cursor = 0
        for i in range(len(nums)):
            if nums[i] != val:
                nums[cursor] = nums[i]
                cursor += 1
        return cursor


if __name__ == "__main__":
    li = [3, 2, 2, 3]
    print Solution().removeElement(li, 3)
    print li
