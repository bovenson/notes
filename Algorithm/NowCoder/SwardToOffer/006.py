#!/bin/bash
# -*- coding:utf-8 -*-
class Solution:
    def minNumberInRotateArray(self, rotateArray):
        # write code here
        if len(rotateArray) <= 1:
            return 0
        for i in range(1, len(rotateArray)):
            if rotateArray[i-1] > rotateArray[i]:
                return rotateArray[i]
        return 0


if __name__ == "__main__":
    s = Solution()
    print s.minNumberInRotateArray([3, 4, 5, 1, 2])
