#!/bin/bash
# -*- coding:utf-8 -*-
class Solution:
    def jumpFloorII(self, number):
        # write code here
        sum = 0
        cur = 0
        for i in range(1, number+1):
            cur = sum + 1
            sum += cur
        return cur


if __name__ == "__main__":
    print Solution().jumpFloorII(4)
