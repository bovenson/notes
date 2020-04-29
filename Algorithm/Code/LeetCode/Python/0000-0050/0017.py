#!/bin/python3
# coding: utf-8

"""
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
"""
from __future__ import print_function

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 17:31"


class Solution(object):
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        d_s = {
            '2': 'abc',
            '3': 'def',
            '4': 'ghi',
            '5': 'jkl',
            '6': 'mno',
            '7': 'pqrs',
            '8': 'tuv',
            '9': 'wxyz'
        }
        _last = ['']
        _res = []
        for i in range(len(digits)):
            _res = []
            for c in d_s[digits[i]]:
                for s in _last:
                    _res.append(s + c)
            _last = _res
        return _res


if __name__ == "__main__":
    res = Solution().letterCombinations('23')
    print(res)
