# -*- coding:utf-8 -*-
class Solution:
    def Fibonacci(self, n):
        # write code here
        if n == 0:
            return 0
        if n <= 2:
            return 1
        arr = [1, 1]
        for i in range(2, n):
            arr.append(arr[i-1] + arr[i-2])
        return arr[n-1]


