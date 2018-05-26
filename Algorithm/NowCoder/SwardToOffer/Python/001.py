# -*- coding:utf-8 -*-
class Solution:
    # array 二维列表
    def Find(self, target, array):
        # write code here
        if len(array) == 0 or len(array[0]) == 0:
            return False

        i = 0
        j = len(array[0]) - 1

        while True:
            if array[i][j] == target:
                return True
            elif array[i][j] > target and j > 0:
                j -= 1
            elif array[i][j] < target and i < len(array) - 1:
                i += 1
            else:
                return False


if __name__ == "__main__":
    arr = [[1,2,3,4,5], [2,3,4,5,6], [5,6,7,8,9]]
    print(Solution().Find(6, arr))
