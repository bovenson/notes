# coding: utf-8
"""
验证二叉搜索树
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
"""


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        def solve(rt):
            if not rt:
                return True, None, None
            cmx = None
            cmn = None
            if rt and rt.right:
                rres, rmx, rmn = solve(rt.right)
                if not rres or rmn <= rt.val:
                    return (False, None, None)
                cmn = rmn
                cmx = rmx
            if rt and rt.left:
                lres, lmx, lmn = solve(rt.left)
                if not lres or lmx >= rt.val:
                    return (False, None, None)
                cmx = max(cmx, lmx) if cmx is not None else lmx
                cmn = min(cmn, lmn) if cmn is not None else lmn
            if rt and not rt.left and not rt.right:
                return (True, rt.val, rt.val)
            return (True, max(cmx, rt.val), min(cmn, rt.val))
        res, mx, mn = solve(root)
        return res


if __name__ == '__main__':
    tree = TreeNode(5)
    tree.left = TreeNode(14)
    tree.left.left = TreeNode(1)
    res = Solution().isValidBST(tree)
    print(res)
