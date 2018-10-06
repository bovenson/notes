#!/bin/python

# -*- coding:utf-8 -*-
class Solution:
    def __init__(self):
        self.res = []
    
    def solve(self, ss, cur):
        if cur >= len(ss) - 1:
            if len(ss) > 0:
                self.res.append(ss)
            return
        s = sorted(ss[cur:])    # return sorted list
        for i in range(len(s)):
            if s[0] != s[i] or i == 0:
                t = s[i]
                s[i] = s[0]
                s[0] = t
                self.solve(ss[:cur] + ''.join(s), cur+1)
    
    def Permutation(self, ss):
        # write code here
        self.solve(ss, 0)
        return self.res


if __name__ == '__main__':
    import sys
    inp = sys.argv[1]
    so = Solution()
    res = so.Permutation(inp)
    print res

