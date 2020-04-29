#!/bin/python3
# coding: utf-8

"""
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 23:25"


class Solution(object):
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        _last = [{'s': '', 'l': n, 'r': 0}]
        _res_dict = []
        for i in range(n * 2):
            _res_dict = []
            for j in _last:
                if j['l'] > 0:
                    _res_dict.append({'s': j['s']+'(', 'l': j['l']-1, 'r': j['r']+1})
                if j['r'] > 0:
                    _res_dict.append({'s': j['s'] + ')', 'l': j['l'], 'r': j['r'] - 1})
            _last = _res_dict

        _res = []
        for i in _res_dict:
            _res.append(i['s'])
        return _res


if __name__ == '__main__':
    print Solution().generateParenthesis(3)
