#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0009.py.py
DATE: 17-9-10 下午3:15
DESC: 
"""


class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x < 0:
            return False
        x_s = str(x)
        for i in range(int(len(x_s) / 2)):
            if x_s[i] != x_s[len(x_s)-1-i]:
                return False
        return True


if __name__ == '__main__':
    s = Solution()
    res = s.isPalindrome(124321)
    print(res)
