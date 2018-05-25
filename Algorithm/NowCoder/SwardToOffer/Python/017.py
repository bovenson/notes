#!/bin/python
# -*- coding:utf-8 -*-
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def judge_same_structure(self, r1, r2):
        if r2 is None:
            return True
        if r1 is None or r1.val != r2.val:
            return False
        return self.judge_same_structure(r1.left, r2.left) and self.judge_same_structure(r1.right, r2.right)
    def HasSubtree(self, pRoot1, pRoot2):
        # write code here
        if pRoot1 is None or pRoot2 is None:
            return False
        if pRoot1.val == pRoot2.val and self.judge_same_structure(pRoot1, pRoot2):
            return True
        return self.HasSubtree(pRoot1.left, pRoot2) or self.HasSubtree(pRoot1.right, pRoot2)


if __name__ == '__main__':
    n1 = TreeNode(1)
    n2 = TreeNode(2)
    n3 = TreeNode(3)
    n4 = TreeNode(4)
    n5 = TreeNode(5)
    n6 = TreeNode(6)
    n7 = TreeNode(7)
    n8 = TreeNode(8)

    n1.left = n2
    n1.right = n3
    n2.left = n4
    n2.right = n5
    n3.left = n6
    n3.right = n7
    n4.left = n8

    nn1 = TreeNode(1)
    nn2 = TreeNode(2)
    nn3 = TreeNode(3)
    nn4 = TreeNode(4)
    nn5 = TreeNode(5)
    nn6 = TreeNode(6)
    nn7 = TreeNode(7)
    nn8 = TreeNode(8)

    nn1.left = nn2
    nn1.right = nn3
    nn2.left = nn4
    nn2.right = nn5
    nn3.left = nn6
    nn3.right = nn7
    nn4.left = nn8


    print Solution().HasSubtree(n1, nn1)
    print Solution().HasSubtree(n1, nn2)
    print Solution().HasSubtree(n1, nn3)
    

