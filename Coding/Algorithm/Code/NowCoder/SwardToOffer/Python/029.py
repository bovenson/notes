#!/bin/python

# -*- coding:utf-8 -*-
class Solution:
    def GetLeastNumbers_Solution(self, tinput, k):
        # write code here
        if len(tinput) == 0 or len(tinput) < k:
            return []
        return sorted(tinput)[:k]
