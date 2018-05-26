#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    def IsPopOrder(self, pushV, popV):
        # write code here
        stack = []
        for i in pushV:
            stack.append(i)

            while len(stack) > 0 and stack[-1] == popV[0]:
                stack.pop()
                popV.pop(0)
        if len(stack) == 0:
            return True
        else:
            return False


if __name__ == '__main__':
    a = [1, 2, 3, 4, 5]
    b = [4, 5, 3, 2, 1]
    c = [4, 3, 5, 1, 2]
    s = Solution()
    print s.IsPopOrder(a, b)
    print s.IsPopOrder(a, c)
