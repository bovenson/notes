# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc     给定一个整数数组，判断是否存在重复元素。
#           如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。


class Solution:
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        return len(nums) != len(set(nums))
        # s = sorted(nums)
        # for i in range(1, len(nums)):
        #     if s[i] == s[i-1]:
        #         return True
        # return False


if __name__ == '__main__':
    s = Solution()
    print(s.containsDuplicate([1, 2, 3, 4, 5]))
