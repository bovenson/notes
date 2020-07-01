#!/bin/python3
# coding: utf-8

"""
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-17 23:25"


class Solution(object):
    def strStr(self, haystack, needle):
        """
        :type haystack: str
        :type needle: str
        :rtype: int
        """
        if len(needle) == 0:
            return 0

        def get_next(nd):
            _next = [0 for _ in range(len(nd)+1)]
            _j = 0
            for _i in range(1, len(nd)):
                while _j > 0 and nd[_i] != nd[_j]:
                    _j = _next[_j]
                if nd[_i] == nd[_j]:
                    _j += 1
                _next[_i+1] = _j
            return _next

        next_arr = get_next(needle)
        j = 0
        for i in range(len(haystack)):
            while j > 0 and haystack[i] != needle[j]:
                j = next_arr[j]
            if haystack[i] == needle[j]:
                j += 1
            if j == len(needle):
                return i - j + 1
        return -1


if __name__ == "__main__":
    print Solution().strStr("mississippi", "issip")
    print Solution().strStr("aaaaa", "ba")
    print Solution().strStr("", "a")
    print Solution().strStr("a", "")
