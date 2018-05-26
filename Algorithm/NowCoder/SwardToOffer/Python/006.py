#!/bin/bash
# -*- coding:utf-8 -*-
class SolutionA:
    def minNumberInRotateArray(self, rotateArray):
        # write code here
        if len(rotateArray) <= 1:
            return 0
        for i in range(1, len(rotateArray)):
            if rotateArray[i-1] > rotateArray[i]:
                return rotateArray[i]
        return 0


class Solution:
    def minNumberInRotateArray(self, rotateArray):
        if len(rotateArray) == 0:
            return 0
        left = 0
        right = len(rotateArray) - 1
        while left <= right:
            mid = (left + right) // 2
            if rotateArray[left] <= rotateArray[mid] and rotateArray[mid] <= rotateArray[right]:
                break
            elif rotateArray[left] <= rotateArray[mid]:
                left = mid + 1
            else:
                right = mid - 1
        return rotateArray[left]


if __name__ == "__main__":
    s = Solution()
    a = [6501,6828,6963,7036,7422,
            7674,8146,8468,8704,8717,9170,9359,9719,9895,9896,9913,9962,154,293,334,492,1323,1479,1539,1727,1870,1943,2383,2392,2996,3282,3812,3903,4465,4605,4665,4772,4828,5142,5437,5448,5668,5706,5725,6300,6335]    
    print s.minNumberInRotateArray(a)
