# 从排序数组中删除重复项
# TIP
#   - 排序数组
#   - 原地删除

class Solution:
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        l = 0
        for r in range(len(nums)):
            if r == 0:
                l += 1
                continue
            if nums[r] != nums[r-1]:
                nums[l] = nums[r]
                l += 1
        return l


if __name__ == '__main__':
    s = Solution()
    nums = [0, 1, 1, 2, 3, 3, 3, 4, 5]
    res = s.removeDuplicates(nums)
    print(res)
    print(nums)
    res = s.removeDuplicates([])
    print(res)

