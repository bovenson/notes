#!/bin/python2
# coding: utf-8

"""
剑指offer - 二维数组中的查找

在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
"""

# -*- coding:utf-8 -*-
class SolutionA:
    """递归
    """
    # array 二维列表
    def Find(self, target, array):
        # write code here

        def RecursionSolution(target, array, r, c):
            if r >= len(array) or c >= len(array[0]):
                return False

            if array[r][c] == target:
                return True
            elif array[r][c] > target:
                return False
            else:
                return RecursionSolution(target, array, r+1, c+1) or RecursionSolution(target, array, r+1, c) or RecursionSolution(target, array, r, c+1)
        return RecursionSolution(target, array, 0, 0)


class Solution:
    """非递归
    """
    # array 二维列表
    def Find(self, target, array):
        # write code here
        if len(array) == 0 or len(array[0]) == 0:
            return False

        r = len(array)
        c = len(array[0])
        i = r - 1
        j = 0

        while i > 0 and j < c:
            if array[i][j] == target:
                return True
            elif array[i][j] > target:
                i -= 1
            else:
                j += 1
        return False


if __name__ == "__main__":
    arr = [[0, 1, 2, 3], [1, 2, 3, 4], [2, 3, 4, 5]]
    res = Solution().Find(5, arr)
    print(res)
