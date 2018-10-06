"""
验证回文字符串
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false
"""


class Solution:
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        i = 0
        j = len(s) - 1
        while i < j:
            while i < j and not s[i].isalpha() and not s[i].isdigit():
                i += 1
            while i < j and not s[j].isalpha() and not s[j].isdigit():
                j -= 1
            if s[i].lower() != s[j].lower():
                return False
            i += 1
            j -= 1
        return True

    def isPalindromeB(self, s):
        """
        :type s: str
        :rtype: bool
        """
        s = list(filter(str.isalnum, s.lower()))
        return True if s == s[::-1] else False


if __name__ == '__main__':
    s = Solution()
    res = s.isPalindrome("A man, a plan, a canal: Panama")
    print(res)

