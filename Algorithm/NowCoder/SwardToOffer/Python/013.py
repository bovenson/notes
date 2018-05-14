#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    def reOrderArray(self, array):
        # write code here
        odd = []
        even = []
        for i in array:
            if i % 2 == 0:
                even.append(i)
            else:
                odd.append(i)
        odd.extend(even)
        return odd

if __name__ == '__main__':
    print Solution().reOrderArray([1, 3, 4, 5, 2, 9, 0, 1])
