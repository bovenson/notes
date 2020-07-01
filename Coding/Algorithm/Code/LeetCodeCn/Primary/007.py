# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc     加一

"""
给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。
"""


class Solution:
    def plusOne(self, digits: list):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        n = int(''.join([str(i) for i in digits]))
        n += 1
        return [int(i) for i in str(n)]

    def plusOneB(self, digits: list):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        p = len(digits) - 1
        up = 1
        while p >= 0 and up > 0:
            digits[p] += up
            up = digits[p] // 10
            digits[p] %= 10
            p -= 1
        if up:
            digits.insert(0, up)
        return digits


if __name__ == '__main__':
    s = Solution()
    res = s.plusOne([1, 9, 9, 9])
    print(res)
