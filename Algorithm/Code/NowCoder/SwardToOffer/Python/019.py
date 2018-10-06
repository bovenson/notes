#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    # matrix类型为二维列表，需要返回列表
    def printMatrix(self, matrix):
        # write code here
        def next_direction(cur_dire):
            if cur_dire == (0, 1):
                return (1, 0)
            elif cur_dire == (1, 0):
                return (0, -1)
            elif cur_dire == (0, -1):
                return (-1, 0)
            elif cur_dire == (-1, 0):
                return (0, 1)
        dire = (0, 1)
        i = 0
        j = 0
        rno = len(matrix)
        cno = len(matrix[0])
        res = []
        v = [[False for _i in range(len(matrix[0]))] for _j in range(len(matrix))]
        while len(res) < rno * cno:
            if not v[i][j]:
                res.append(matrix[i][j])
                v[i][j] = True
            next_i = i + dire[0]
            next_j = j + dire[1]
            if next_i >= rno or next_i < 0 or next_j >= cno or next_j < 0 or v[next_i][next_j]:
                dire = next_direction(dire)
            else:
                i = next_i
                j = next_j
        return res

if __name__ == '__main__':
    m = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
    print Solution().printMatrix(m)

