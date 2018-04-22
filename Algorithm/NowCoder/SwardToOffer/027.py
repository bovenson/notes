#!/bin/python

# -*- coding:utf-8 -*-
class Solution:
    def __init__(self):
        self.res = []
    @staticmethod
    def swap(arr, i, j):
        t = arr[i]
        arr[i] = arr[j]
        arr[j] = t
    def solve(self, arr, depth):
        if len(arr) == 0:
            return
        if depth == len(arr) - 1:
            # print arr
            # print ''.join(arr)
            self.res.append(''.join(arr))
            return

        arr[depth:] = sorted(arr[depth:])
        tarr = arr[0:depth+1].extend(sorted(arr[depth:]))

        for i in range(depth, len(arr)):
            if arr[depth] != arr[i] and i != 0:
                Solution.swap(arr, depth, i)
                self.solve(arr, depth+1)
                Solution.swap(arr, depth, i)
            else:
                self.solve(arr, depth+1)
        pass
    def Permutation(self, ss):
        # write code here
        self.solve(sorted(list(ss)), 0)
        return self.res


if __name__ == '__main__':
    import sys
    s = Solution()
    # a = [1, 2, 3]
    # print s.swap(a, 1, 2)
    # print a
    print s.Permutation(sys.argv[1])
    s = Solution()
    print s.Permutation('')
