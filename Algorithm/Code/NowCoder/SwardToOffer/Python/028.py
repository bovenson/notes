#!/bin/bash
# -*- coding:utf-8 -*-
class Solution:
    def MoreThanHalfNum_Solution(self, numbers):
        # write code here
        if len(numbers) == 0:
            return 0
        if len(numbers) == 1:
            return numbers[0]
        nums = sorted(numbers)
        l = len(nums)
        cnt = 1
        for i in range(1, len(nums)):
            if nums[i] == nums[i-1]:
                cnt += 1
                if cnt * 2 > l:
                    return nums[i]
            else:
                cnt = 1
        return 0

if __name__ == '__main__':
    solution = Solution()
    nums = [1, 2, 3, 2, 2, 2, 2, 2, 2]
    print(solution.MoreThanHalfNum_Solution(nums))
