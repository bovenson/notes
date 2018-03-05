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
        node = Node(data)
        if self._head is None:
            self._head = node
            self._tail = node
        else:
            node.next = self._head
            self._head.prev = node
            self._head = node

    def insert_tail(self, data):
        """尾部插入"""
        node = Node(data)
        if self._tail is None:
            self._tail = node
            self._head = node
        else:
            node.prev = self._tail
            self._tail.next = node
            self._tail = node
        self._size += 1

    def delete_head(self):
        """从尾部删除节点"""
        if self._head is None:  # 链表为空
            raise IndexError()
        elif self._head.next is None:   # 链表只有一个元素
            self._head = None
            self._tail = None
        else:
            self._head.next.prev = None
            self._head = self._head.next
        self._size -= 1

    def delete_tail(self):
        """从尾部删除元素"""
        if self._tail is None:
            raise IndexError()
        elif self._tail.prev is None:
            self._head = None
            self._tail = None
        else:
            self._tail.prev.next = None
            self._tail = self._tail.prev
        self._size -= 1

    def print(self):
        agency = self._head
        while agency is not None:
            print(agency.data)
            agency = agency.next


if __name__ == "__main__":
    l = DoublyLinkedList()
    l.insert_head(10)
    l.insert_head(9)
    l.insert_tail(11)
    l.insert_tail(12)
    l.insert_head(8)
    l.print()
    print('-' * 20)
    l.delete_head()
    l.print()
    print('-' * 20)
    l.delete_tail()
    l.print()
