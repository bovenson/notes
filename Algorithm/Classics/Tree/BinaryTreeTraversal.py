#!/bin/bash
# coding: utf-8

"""
二叉树遍历
    - 先序遍历
    - 中序遍历
    - 后序遍历
"""

class Node:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


def preOrderTravelsal(node):
    if node is None:
        return ''
    return node.val + preOrderTravelsal(node.left) + preOrderTravelsal(node.right)


def inOrderTravelsal(node):
    if node is None:
        return ''
    return inOrderTravelsal(node.left) + node.val + inOrderTravelsal(node.right)


def postOrderTravelsal(node):
    if node is None:
        return ''
    return postOrderTravelsal(node.left) + postOrderTravelsal(node.right) + node.val


def rebuildWithPreAndIn(preOrder, inOrder):
    if len(preOrder) == 0:
        return None

    # print preOrder + " - " + inOrder

    split = inOrder.split(preOrder[0])
    # print split
    # print preOrder[0]
    curNode = Node(preOrder[0])
    curNode.left = rebuildWithPreAndIn(preOrder[1:1+len(split[0])], split[0])
    curNode.right = rebuildWithPreAndIn(preOrder[-len(split[1])+len(preOrder):len(preOrder)], split[1])
    return curNode


def rebuildWithPreAndPost(preOrder, postOrder):
    """
    注: 只能部分还原源二叉树
    """
    if len(preOrder) == 0:
        return None
    elif len(preOrder) == 1:
        return Node(preOrder[0])

    # print preOrder + " - " + postOrder
    curNode = Node(preOrder[0])
    
    preOrder = preOrder[1:]
    postOrder = postOrder[:-1]

    if preOrder[0] != postOrder[-1]:
        nextLeftTreeLen = postOrder.index(preOrder[0]) + 1
    else:
        nextLeftTreeLen = 0
    curNode.left = rebuildWithPreAndPost(preOrder[0:nextLeftTreeLen], postOrder[0:nextLeftTreeLen])
    curNode.right = rebuildWithPreAndPost(preOrder[nextLeftTreeLen:], postOrder[nextLeftTreeLen:])
    return curNode



def rebuildWithInAndPost(inOrder, postOrder):
    if len(inOrder) == 0:
        return None
    # print inOrder + ' - ' + postOrder

    curNode = Node(postOrder[-1])
    split = inOrder.split(postOrder[-1])
    # print split
    curNode.left = rebuildWithInAndPost(split[0], postOrder[0:len(split[0])])
    curNode.right = rebuildWithInAndPost(split[1], postOrder[len(split[0]):-1])
    return curNode


def printBinaryTree(node):
    if node is None:
        return
    print 'cur node:' + node.val
    print 'left node:' + node.left.val if node.left is not None else 'None'
    print 'right node:' + node.right.val if node.right is not None else 'None'
    print ''
    printBinaryTree(node.left)
    printBinaryTree(node.right)


if __name__ == "__main__":
    nodeA = Node('A')
    nodeB = Node('B')
    nodeC = Node('C')
    nodeD = Node('D')
    nodeE = Node('E')
    nodeF = Node('F')
    nodeG = Node('G')
    nodeK = Node('K')
    nodeH = Node('H')

    nodeA.left = nodeB
    nodeA.right = nodeE
    nodeB.right = nodeC
    nodeC.left = nodeD
    nodeE.right = nodeF
    nodeF.left = nodeG
    nodeG.left = nodeH
    nodeG.right = nodeK

    # print "Preorder Travelsal:"
    preOrd = preOrderTravelsal(nodeA)

    # print "Inorder Travelsal:"
    inOrd = inOrderTravelsal(nodeA)

    # print "Postorder Travelsal:"
    postOrd = postOrderTravelsal(nodeA)

    print preOrd
    print inOrd
    print postOrd
    print "-" * 50
    printBinaryTree(rebuildWithPreAndIn(preOrd, inOrd))
    print "-" * 50
    printBinaryTree(rebuildWithPreAndPost(preOrd, postOrd))
    print '-' * 50
    printBinaryTree(rebuildWithInAndPost(inOrd, postOrd))
