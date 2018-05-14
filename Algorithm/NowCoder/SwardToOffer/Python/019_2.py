#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    # matrix类型为二维列表，需要返回列表
    def printMatrix(self, matrix):
        # write code here
        res = []
        row = len(matrix)
        column = len(matrix[0])

        circles = (min(row, column) - 1) // 2 + 1
        for i in range(circles):
            for j in range(i, column - i):
                res.append(matrix[i][j])
            for j in range(i+1, row - i):
                res.append(matrix[j][column - i - 1])

            if min(row, column) % 2 == 1 and i == circles - 1:
                break

            for j in range(column-i-2, i-1, -1):
                res.append(matrix[row-i-1][j])
            for j in range(row-i-2, i, -1):
                res.append(matrix[j][i])
        return res


if __name__ == '__main__':
    m = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
    print Solution().printMatrix(m)
    print Solution().printMatrix([[1, 2], [3, 4]])
