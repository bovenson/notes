# coding: utf-8
"""
最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。
"""


class Solution:
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if len(strs) == 0 or len(strs[0]) == 0:
            return ''

        res = 0
        while True:
            flag = False
            for i in range(len(strs)):
                if len(strs[i]) <= res or (i > 0 and strs[i][res] != strs[i-1][res]):
                    flag = True
                    break
            if flag:
                return strs[0][:res]
            res += 1

if __name__ == '__main__':
    s = Solution()
    res = s.longestCommonPrefix(['fe', 'fellow'])
    print(res)

