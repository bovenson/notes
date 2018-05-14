#!/bin/python

# -*- coding:utf-8 -*-
class Solution:
    def NumberOf1Between1AndN_Solution(self, n):
        # write code here
        s = list(str(n))
        l = len(s)
        res = 0
        for i in range(len(s)):
            if l - i - 2 > 0:
                t = pow(10, l - i - 2)
                res += t * int(s[i])
            if int(s[i]) > 1:
                res += pow(10, l - i)
            if s[i] == '1':
                if i + 1 < l:
                    res += int(''.join(s[i+1:]))
            print res

        return res


if __name__ == '__main__':
    solution = Solution()
    import sys
    num = int(sys.argv[1])
    cnt = 0
    for i in range(num + 1):
        cnt += str(i).count('1')
    print(cnt)
    print(solution.NumberOf1Between1AndN_Solution(num))

