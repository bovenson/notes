#!/bin/python
# -*- coding:utf-8 -*-
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
class Solution:
    # 返回二维列表，内部每个列表表示找到的路径
    def FindPath(self, root, expectNumber):
        # write code here
        if root is None:
            return []
        sam = []
        parent = [-1]
        q = [root]
        i = 0
        res = []
        while i < len(q):
            ele = q[i]
            sam.append(ele.val + (0 if parent[i] < 0 or i == 0 else sam[parent[i]]))
            if ele is not None:
                if ele.left is not None:
                    q.append(ele.left)
                    parent.append(i)
                if ele.right is not None:
                    q.append(ele.right)
                    parent.append(i)
            i += 1
            # print sam
            # print parent
            # print q
        for i in range(len(sam)):
            if sam[i] == expectNumber and q[i].left is None and q[i].right is None:
                cur = [q[i].val]
                p = i
                while parent[p] >= 0:
                    cur.append(q[parent[p]].val)
                    p = parent[p]
                cur.reverse()
                res.append(cur)
        res.reverse()
        return res


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
    n3.left = n4
    n3.right = n5
    n2.left = n8
    n4.left = n6
    n4.right = n7

    s = Solution()
    print s.FindPath(n1, 9)
    print s.FindPath(n1, 11)
    print s.FindPath(n1, 8)
    print s.FindPath(n1, 14)
    
