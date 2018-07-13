# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc     两个数组的交集 II
#           给定两个数组，写一个方法来计算它们的交集。
import collections

class Solution:
    def intersect(self, nums1: list, nums2: list):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums1.sort()
        nums2.sort()
        i = 0
        j = 0
        res = []
        while i < len(nums1) and j < len(nums2):
            if nums1[i] == nums2[j]:
                res.append(nums1[i])
                i += 1
                j += 1
            elif nums1[i] > nums2[j]:
                j += 1
            else:
                i += 1
        return res

    def intersectA(self, nums1: list, nums2: list):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        return list((collections.Counter(nums1) & collections.Counter(nums2)).elements())


if __name__ == '__main__':
    print(Solution().intersectA([1, 2, 2, 1], [2]))
