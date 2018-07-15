"""
字符串中的第一个唯一字符
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.


注意事项：您可以假定该字符串只包含小写字母。
"""


class Solution:
    def firstUniqChar(self, s):
        """
        :type s: str
        :rtype: int
        """
        se = set()
        st = set()
        for c in s:
            if c in st:
                st.remove(c)
            elif c not in se:
                st.add(c)
            se.add(c)
        for i in range(len(s)):
            if s[i] in st:
                return i
        return -1


if __name__ == '__main__':
    print(Solution().firstUniqChar('aadadaad'))
