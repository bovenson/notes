#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    def __init__(self):
        self.arr = []
    def push(self, node):
        # write code here
        self.arr.append(node)
    def pop(self):
        # write code here
        return self.arr.pop()
    def top(self):
        # write code here
        return self.arr[-1]
    def min(self):
        # write code here
        return min(self.arr)

if __name__ == '__main__':
    s = Solution()
    s.push(1)
    s.push(5)
    s.push(2)
    s.push(9)
    s.push(0)
    s.push(3)

    print s.pop()
    print s.top()
    print s.min()
