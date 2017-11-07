#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0012.py
DATE: 17-9-13 下午8:09
DESC: 
"""


class Solution(object):
    ch = {
        1000: 'M',
        900: 'CM',
        500: 'D',
        400: 'CD',
        100: 'C',
        90: 'XC',
        50: 'L',
        40: 'XL',
        10: 'X',
        9: 'IX',
        5: 'V',
        4: 'IV',
        1: 'I'
    }
    l = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]

    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        _res = ''
        for i in Solution.l:
            _b = int(num / i)
            _res += Solution.ch[i] * _b if i == 1000 or _b < 4 else Solution.ch[_b*i]
            num = num % i
        return _res


res = Solution().intToRoman(3109)
print(res)
res = Solution().intToRoman(3119)
print(res)
