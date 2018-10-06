# coding: utf-8

"""
对称二叉树
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
说明:

如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
"""


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isSymmetricA(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        cur = [root]
        nxt = []
        while len(cur) > 0:
            for nd in cur:
                if nd:
                    nxt.append(nd.left)
                    nxt.append(nd.right)
            for i in range(len(nxt) // 2):
                if nxt[i] is None and nxt[-i-1] is None:
                    continue
                elif nxt[i] is None or nxt[-i-1] is None:
                    return False
                elif nxt[i].val != nxt[-i-1].val:
                    return False
            cur = nxt
            nxt = []
        return True

    def isSymmetric(self, root):
        def solve(l, r):
            if l is None and r is None:
                return True
            if l is None or r is None:
                return False
            if l.val != r.val or not solve(l.left, r.right) or not solve(l.right, r.left):
                return False
            return True
        if root is not None:
            return solve(root.left, root.right)
        else:
            return True
