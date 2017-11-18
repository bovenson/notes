#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0013.py
DATE: 17-9-14 上午9:03
DESC: 
"""


class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        l = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        c = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I']
        _res = 0
        _pos = 0
        for i in range(len(c)):
            while _pos + len(c[i]) - 1 < len(s) and s[_pos:_pos+len(c[i])] == c[i]:
                _res += l[i]
                _pos += len(c[i])
        return _res


print(Solution().romanToInt('MMMCIV'))
print(Solution().romanToInt('MMMCIX'))
print(Solution().romanToInt('MMMCXIX'))
