#!/bin/python
# coding: utf-8
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    # 返回构造的TreeNode根节点
    def reConstructBinaryTree(self, pre, tin):
        # write code here
        if len(pre) == 0:
            return None

        index = tin.index(pre[0])
        split = [tin[0:index], tin[index+1:]]
        curNode = TreeNode(pre[0])
        curNode.left = self.reConstructBinaryTree(pre[1:1+len(split[0])], split[0])
        curNode.right = self.reConstructBinaryTree(pre[1+len(split[0]):], split[1])
        return curNode

def printBinaryTree(node):
    if node is None:
        return
    print 'cur node:' + str(node.val)
    print 'left node:' + str(node.left.val) if node.left is not None else 'None'
    print 'right node:' + str(node.right.val) if node.right is not None else 'None'
    print ''
    printBinaryTree(node.left)
    printBinaryTree(node.right)


if __name__ == "__main__":
    printBinaryTree(Solution().reConstructBinaryTree([1,2,4,7,3,5,6,8], [4,7,2,1,5,3,8,6]))
