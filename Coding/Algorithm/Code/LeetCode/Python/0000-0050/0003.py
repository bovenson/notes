class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        res = 0
        pos_tail = 0
        se = set()
        for i in range(len(s)):
            if s[i] in se:
                while pos_tail < i:
                    se.remove(s[pos_tail])
                    pos_tail += 1
                    if s[pos_tail-1] == s[i]:
                        break
            se.add(s[i])
            res = max(res, i - pos_tail + 1)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.lengthOfLongestSubstring("dvdf"))
