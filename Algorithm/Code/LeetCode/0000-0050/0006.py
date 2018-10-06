# coding: utf-8

class Solution(object):
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        if numRows == 1:
            return s

        res = ''
        T = numRows * 2 - 2
        for r in range(numRows):    # 行
            for i in range(len(s) / T  + 1): # 周期
                _start = i * T
                _end = _start + T
                if _start + r < len(s):
                    res = res + s[_start+r]
                if r > 0 and _end - r != _start + r and _end - r < len(s):
                    res = res + s[_end-r]
        return res

if __name__ == '__main__':
    s = Solution()
    res = s.convert("", 1)
    print(res)