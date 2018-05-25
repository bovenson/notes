# -*- coding:utf-8 -*-
class Solution:
    # s 源字符串
    def replaceSpace(self, s):
        # write code here
        res = ''
        for i in range(len(s)):
            if s[i] == ' ':
                res += '%20'
            else:
                res += s[i]

        return res


if __name__ == "__main__":
    print(Solution().replaceSpace('ab bd  bdf'))
