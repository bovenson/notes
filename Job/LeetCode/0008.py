#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0008.py
DATE: 17-9-10 下午3:00
DESC: 
"""


class Solution(object):
    def myAtoi(self, s):
        """
        :type str: str
        :rtype: int
        """
        _res = 0
        try:
            s = s.strip()
            _s = ''
            _plus = False
            _nav = False
            for i in range(len(s)):
                if s[i] == '-' and not _nav:
                    _nav = True
                    _s += s[i]
                elif s[i] == '+' and not _plus:
                    _plus = True
                    _s += s[i]
                elif '9' >= s[i] >= '0':
                    _s += s[i]
                else:
                    break
            _res = int(_s)
        except:
            pass

        if _res >= pow(2, 31):
            _res = pow(2, 31) - 1
        elif _res <= -pow(2, 31):
            _res = -pow(2, 31)
        return _res

if __name__ == '__main__':
    s = Solution()
    res = s.myAtoi("-2147483648")
    print(res)
