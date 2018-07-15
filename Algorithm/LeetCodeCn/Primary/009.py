"""
两数之和
给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
"""

class Solution:
    def twoSum(self, nums, target):
        m = {}
        for i in range(len(nums)):
            if target - nums[i] in m:
                return [m[target-nums[i]], i]
            m[nums[i]] = i
        return []
    def twoSumA(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        s = sorted(nums)
        i = 0
        j = len(s) - 1
        while i < j:
            v = target - s[i]
            while s[j] > v and j > i:
                j -= 1
            if j > i and s[j] == v:
                break
            i += 1

        res = []
        if i != j:
            for k in range(len(nums)):
                if len(res) == 2:
                    break
                if nums[k] == s[i]:
                    res.append(k)
                    continue
                if nums[k] == s[j]:
                    res.append(k)
                    continue
        return res

if __name__ == '__main__':
    ns = [2, 5, 0, 11]
    s = Solution()
    print(s.twoSum(ns, 7))

