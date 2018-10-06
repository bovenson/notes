#!/bin/python
# -*- coding:utf-8 -*-
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    def __init__(self):
        self.l = []
    def traversal(self, node):
        if node is None:
            return 
        self.traversal(node.left)
        self.l.append(node)
        self.traversal(node.right)

    def Convert(self, pRootOfTree):
        # write code here
        self.traversal(pRootOfTree)
        for i in range(len(self.l)):
            node = self.l[i]
            if i == 0:
                node.left = None
            else:
                node.left = self.l[i-1]
            if i == len(self.l) - 1:
                node.right = None
            else:
                node.right = self.l[i+1]
        return self.l[0] if len(self.l) > 0 else None

