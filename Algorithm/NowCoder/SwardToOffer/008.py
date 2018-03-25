#!/bin/python
# -*- coding:utf-8 -*-
class Solution:
    def jumpFloor(self, number):
        # write code here
        res = 0
        fb = [1, 1]
        for i in range(2, number+1):
            fb.append(fb[i-1] + fb[i-2])
        res = fb[number]
        return res


if __name__ == "__main__":
    s = Solution()
    print s.jumpFloor(3)
    print s.jumpFloor(7)
    print s.jumpFloor(2)

