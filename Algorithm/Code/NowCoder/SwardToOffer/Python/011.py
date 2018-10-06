#!/bin/bash
# -*- coding:utf-8 -*-
class Solution:
    def NumberOf1(self, n):
        # write code here
        def getBinaryCode(n):
            """低位在前"""
            res = []
            n = abs(n)
            while n > 0:
                res.append(n % 2)
                n //= 2
            return res
        def getBinaryCodeSub(n):
            bc = getBinaryCode(n)
            if n >= 0:
                return bc
            for i in range(len(bc)):
                bc[i] = 1 if bc[i] != 1 else 0
            up = 1
            for i in range(len(bc)):
                t = up + bc[i]
                bc[i] = t % 2
                up = t // 2
            if up == 1:
                bc.append(1)
            for i in range(len(bc), 32):
                bc.append(1)
            return bc
        
        bcs = getBinaryCodeSub(n)[0:32]
        # print bcs
        return bcs.count(1)


if __name__ == "__main__":
    s = Solution()
    import sys
    for arg in sys.argv[1:]:
        print s.NumberOf1(int(arg))

