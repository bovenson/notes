#!/bin/python3
# coding: utf-8

"""
双向链表
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"


class Node:
    """双链表节点"""
    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None


