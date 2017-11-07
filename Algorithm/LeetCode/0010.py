#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0010.py
DATE: 17-9-10 下午3:22
DESC: 动态规划
"""


class Solution(object):

    def __init__(self):
        self.v = [[False for _ in range(100)] for _ in range(100)]
        self.r = [[False for _ in range(100)] for _ in range(100)]

    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        if self.v[len(s)][len(p)]:
            return self.r[len(s)][len(p)]

        # print(s, '\t', p)

        _res = False

        if len(s) == 0 and len(p) == 0:
            _res = True
        elif len(p) == 0:
            _res = False
        elif len(s) == 0:
            if len(p) > 1 and p[1] == '*':
                _res = self.isMatch(s, p[2:])
        else:
            if s[0] == p[0] or p[0] == '.':
                if self.isMatch(s[1:], p[1:]):
                    _res = True
                if not _res and len(p) > 1 and p[1] == '*':
                    if self.isMatch(s[1:], p) or self.isMatch(s[1:], p[2:]) or self.isMatch(s, p[2:]):
                        _res = True
            elif len(p) > 1 and p[1] == '*':
                _res = self.isMatch(s, p[2:])
        self.v[len(s)][len(p)] = True
        self.r[len(s)][len(p)] = _res
        return _res


print(Solution().isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c"))
print(Solution().isMatch("bbbba", ".*a*a"))
