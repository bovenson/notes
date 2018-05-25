#!/bin/bash

# -*- coding:utf-8 -*-
class Solution:
    def FindGreatestSumOfSubArray(self, array):
        # write code here
        if len(array) == 0:
            return 0
        summ = 0
        minn = 0
        res = array[0]
        for i in range(len(array)):
            summ += array[i]
            if summ - minn > res:
                res = summ - minn
            minn = min(minn, summ)
        return res


if __name__ == '__main__':
    solution = Solution()
    arr = [6, -3, -2, 7, -15, 1, 2, 2]
    print solution.FindGreatestSumOfSubArray(arr)
