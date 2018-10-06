# class Solution:
#     def twoSum(self, nums, target):
#         """
#         :type nums: List[int]
#         :type target: int
#         :rtype: List[int]
#         """
#         s = set(nums)
#         for i in range(len(nums)):
#             _remain = target - nums[i]
#             if _remain in s:
#                 _remain_index = nums.index(_remain)
#                 if i != _remain_index:
#                     return [i, _remain_index]


class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        _hash = {}
        for i in range(len(nums)):
            if target - nums[i] in _hash:
                return [_hash[target-nums[i]], i]
            _hash[nums[i]] = i


if __name__ == '__main__':
    res = Solution().twoSum([1, 2, 3, 4, 5], 8)
    print(res)
