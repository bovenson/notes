#!/bin/python3
# coding: utf-8

"""
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 20:21"


class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        _l = []
        _couple = {
            '(': ')',
            '[': ']',
            '{': '}',
            ')': '(',
            ']': '[',
            '}': '{'
        }
        for i in s:
            if len(_l) > 0 and _couple[_l[-1]] == i:
                _l.pop()
            else:
                _l.append(i)
        return len(_l) == 0


if __name__ == "__main__":
    print(Solution().isValid('()[](){}'))
    print(Solution().isValid('([)]'))
