# TITLE 旋转数组
# DESC  给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
# LIMIT
#   - in-place
# SOLVE
#   - 时间复杂度 O(n); 最多交换 len(nums) - 1 次
#   - 空间复杂度 O(1)
import time


class Solution:
    def rotate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        cnt = len(nums)         # 记录长度
        rep = 0                 # 记录交换次数
        for i in range(cnt):
            rep += 1            # 每次循环，会减少一次交换次数
            if rep >= cnt or i >= k:
                break
            j = (i + k) % cnt
            while j != i:
                t = nums[i]
                nums[i] = nums[j]
                nums[j] = t
                j = (j + k) % cnt
                rep += 1
        return

    def rotate_space_o_n(self, nums, k):
        length = len(nums)
        i = k % length
        nums[:] = nums[-i:] + nums[:-i]

        # 遍历和深拷贝的效率


if __name__ == '__main__':
    s = Solution()
    nums = [1, 2, 3, 4, 5, 6, 7]
    nums = [-1,-100,3,99]
    nums = [0, 1, 2, 3, 4, 5]
    nums = [1] * 10000000
    print(time.clock())
    s.rotate_space_o_n(nums, 4)
    print(time.clock())
    # print(nums)
    print(time.clock())
    s.rotate(nums, 4)
    print(time.clock())
    # print(nums)


