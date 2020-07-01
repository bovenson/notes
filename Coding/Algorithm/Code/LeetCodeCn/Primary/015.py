"""
有效的字母异位词
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
"""

class Solution:
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        ss = sorted(s)
        ts = sorted(t)
        if len(ss) != len(ts):
            return False
        for i in range(len(ss)):
            if ss[i] != ts[i]:
                return False
        return True

    def isAnagranOther(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        return set(s) == set(t) and all(s.count(i) == t.count(i) for i in set(s))

