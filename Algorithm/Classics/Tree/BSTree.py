#!/bin/python3
# coding: utf-8

"""
二叉排序树
"""
from collections import deque

class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
    def __str__(self):
        return str(self.val)


def add_node(tree, val):
    if tree is None:
        return TreeNode(val)
    
    par = tree
    while par:
        if par.val > val:
            if par.left is None:
                par.left = TreeNode(val)
                return tree
            else:
                par = par.left
        else:
            if par.right is None:
                par.right = TreeNode(val)
                return tree
            else:
                par = par.right
    return tree


def display(tree):
    cur = [tree]
    while len(cur) > 0:
        nxt = []
        cnt = 0
        for nd in cur:
            if nd is None:
                nxt += [None, None]
            else:
                cnt += 1
                print(nd.val, ' L ', nd.left, ' R ', nd.right)
                nxt += [nd.left, nd.right]
        if cnt == 0:
            break
        cur = nxt
        nxt = []
        print()



if __name__ == '__main__':
    s = [1, 0, 5, 4, 9, 8, 6, 2, 3]
    tn = None
    for v in s:
        tn = add_node(tn, v)
    display(tn)
