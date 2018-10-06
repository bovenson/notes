#!/bin/python3
# coding: utf-8

"""
单链表
 - Node : 节点
 - 插入
 - 删除
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"


class Node:
    """单链表节点"""
    next = None  # 下一个节点
    data = None  # 当前节点数据


class SingleLinkedList:
    """单链表"""
    _head = None
    _tail = None

    def is_empty(self):
        """判断链表是否为空"""
        return self._head is None

    def insert_head(self, data):
        """头部插入数据"""
        node = Node()
        node.data = data
        if not self._head:  # 如果链表为空
            self._tail = node
        else:
            node.next = self._head
        self._head = node

    def insert_tail(self, data):
        """尾部插入数据"""
        if not self._tail:  # 如果没有数据, 从头部插入
            self.insert_head(data)
        else:  # 否则从尾部插入
            node = Node()
            node.data = data
            self._tail.next = node
            self._tail = node

    def insert_after(self, data, after_node):
        """
        在某一个节点后插入
        :param data: 需要插入的数据
        :param after_node: 特定节点后
        :return: None
        """
        if after_node is self._tail:
            self.insert_tail(data)
        else:
            node = Node()
            node.data = data
            node.next = after_node.next
            after_node.next = node

    def index_of(self, index):
        """
        获取第index个节点
        :param index: 索引
        :return: 目标节点
        """
        if index < 0:
            raise IndexError()

        agency = self._head
        while index > 0:
            index -= 1
            if agency is None:
                raise IndexError
            agency = agency.next
        return agency

    def delete_head(self):
        """从头部删除"""
        if self._head:
            self._head = self._head.next
        else:
            raise IndexError()

    def delete_node(self, node):
        """
        删除某个节点
        :param node: 需要删除的节点
        :return: None
        """
        agency = self._head
        while agency and agency.next and agency.next is not node:
            agency = agency.next
        agency.next = node.next

    def print(self):
        """打印单链表"""
        agency = self._head
        while agency:
            print(agency.data)
            agency = agency.next


if __name__ == "__main__":
    l = SingleLinkedList()
    l.insert_head(2)
    l.insert_head(3)
    l.insert_tail(1)
    l.insert_tail(-1)
    l.print()
    print('*' * 100)

    print(l.index_of(3).data)
    print('*' * 100)

    l.insert_after(-2, l.index_of(2))
    l.print()
    print('*' * 100)

    l.delete_head()
    l.print()
    print('*' * 100)

    l.delete_node(l.index_of(2))
    l.print()
