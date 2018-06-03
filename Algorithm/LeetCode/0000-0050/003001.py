#!/bin/python3
# coding: utf-8

"""
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-05-29 11:13"


class Solution:
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        if len(s) == 0 or len(words) == 0:
            return []

        word_length = len(words[0])
        dest = ''.join(sorted(words))

        for i in range(word_length):
            ts =



if __name__ == '__main__':
    sol = Solution()
    s = 'barfoothefoobarman'
    words = ["foo", "bar"]
    sol.findSubstring(s, words)