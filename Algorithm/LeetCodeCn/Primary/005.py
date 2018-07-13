# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc     只出现一次的数字
import time


class Solution:
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        s = set()
        for i in range(len(nums)):
            if nums[i] in s:
                s.remove(nums[i])
            else:
                s.add(nums[i])
        return list(s)[0]

    def singleNumberA(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        return 2 * sum(set(nums)) - sum(nums)

if __name__ == '__main__':
    sl = Solution()
    print(sl.singleNumber([1, 1, 2, 3, 3, 4, 2]))
    s = [1, 2, 3] * 1000000
    print(time.clock())
    sum(s)
    print(time.clock())
    print(time.clock())
    res = 0
    for i in s:
        res += i
    print(time.clock())

