#!/bin/python
# -*- coding:utf-8 -*-
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    # 返回从上到下每个节点值列表，例：[1,2,3]
    def PrintFromTopToBottom(self, root):
        # write code here
        queue = [root]
        res = []
        while len(queue) > 0:
            if queue[0] is not None:
                queue.append(queue[0].left)
                queue.append(queue[0].right)
                res.append(queue[0].val)
            queue.pop(0)
        return res

