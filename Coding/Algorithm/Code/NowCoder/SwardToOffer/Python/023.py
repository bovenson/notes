#!/bin/bash
# -*- coding:utf-8 -*-
class Solution:
    def VerifySquenceOfBST(self, sequence):
        # write code here
        s = sequence
        if sequence is None or len(sequence) == 0:
            return False
        i = 0
        j = len(sequence) - 2
        while i < len(sequence) - 1 and sequence[i] < sequence[-1]:
            i += 1
        while j >= 0 and sequence[j] > sequence[-1]: 
            j -= 1
        # print i, j
        if j + 1 == i:
            if (len(s[0:i]) <= 1 or self.VerifySquenceOfBST(sequence[0:i])) and (len(s[i:len(s)-1]) <= 1 or self.VerifySquenceOfBST(sequence[i:len(sequence)-1])):
                return True
        else:
            return False


if __name__ == '__main__':
    s = Solution()
    print s.VerifySquenceOfBST([1, 3, 2])
    print s.VerifySquenceOfBST([4, 6, 7, 5])
    print s.VerifySquenceOfBST([])


