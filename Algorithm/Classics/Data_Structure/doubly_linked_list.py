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


class DoublyLinkedList:
    """双链表"""
    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def is_empty(self):
        """链表是否为空"""
        return self._size == 0

    def insert_head(self, data):
        """头部插入数据"""
        
