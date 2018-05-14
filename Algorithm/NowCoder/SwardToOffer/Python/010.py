#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    def rectCover(self, number):
        # write code here
        arr = [0, 1, 2]
        for i in range(3, number+1):
            arr.append(arr[i-1] + arr[i-2])
        return arr[number]

if __name__ == "__main__":
    print Solution().rectCover(4)
