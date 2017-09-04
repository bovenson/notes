class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        re_s = s[::-1]
        res = ''
        for i in range(len(s)):
            for j in range(len(re_s)):
                if s[i] == re_s[j]:
                    _i = i
                    _j = j
                    _k = 0
                    while _i < len(s) and _j < len(re_s) and s[_i] == re_s[_j]:
                        _i += 1
                        _j += 1
                        _k += 1
                    res = s[i:i+_k] if len(res) < _k else res
        return res
                
                
if __name__ == '__main__':
    s = Solution()
    res = s.longestPalindrome("abcdasdfghjkldcba")
    print(res)