#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 0014.py
DATE: 17-9-25 下午4:47
DESC: 
"""


class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if len(strs) == 0:
            return ''

        res = ''
        flag = True
        index = 0
        while flag:
            for i in range(len(strs)):
                if len(strs[i]) < index + 1 or (i > 0 and strs[i][index] != strs[i - 1][index]):
                    flag = False
                    break

            if not flag:
                break
            index += 1
        return strs[0][:index]



if __name__ == '__main__':
    print Solution().longestCommonPrefix(['a', 'b'])
    print Solution().longestCommonPrefix([])
    print Solution().longestCommonPrefix(['a'])
    print Solution().longestCommonPrefix(['ab', 'abv'])
