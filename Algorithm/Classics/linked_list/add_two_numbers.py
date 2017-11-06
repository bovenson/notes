#!/bin/python3
# coding: utf-8

"""
You are given two non-empty linked lists representing two
non-negative integers.

Add this two numbers, and return the answer as a linked list.

Example:
    Normal order:
        Input:
            (2)->(1)->(9) + (2)->(1)->(3)
        Output:
            (4)->(3)->(2)
    Reverse order:
        Input:
            (9)->(1)->(9) + (2)->(1)->(3)
        Output:
            (1)->(3)->(2)->(1)
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"


class Node:
    """链表节点"""

    def __init__(self, data):
        self.data = data
        self.next = None

    def __str__(self):
        return str(self.data)


def add_two_reverse_numbers(left: "Node", right: "Node") -> Node:
    """两个链表表示的整数相加"""
    _res = Node(0)
    _cursor = _res
    _carry = 0
    while left or right or _carry > 0:
        _sum = (left.data if left else 0) + (right.data if right else 0) + _carry
        _carry = _sum // 10
        _node = Node(_sum % 10)
        _cursor.next = _node
        _cursor = _node
        if left:
            left = left.next
        if right:
            right = right.next
    return _res.next


def add_two_numbers(left: "Node", right: "Node") -> "Node":
    """相加两个链表表示的正整数"""
    return add_two_reverse_numbers(reverse_single_linked_list(left), reverse_single_linked_list(right))


def reverse_single_linked_list(li: "Node") -> "Node":
    """反转单链表"""
    _head = None
    while li:
        _li_next = li.next
        _cur_node = li
        _cur_node.next = _head
        _head = _cur_node
        li = _li_next
    return _head


def print_linked_list(li: "Node"):
    """打印链表"""
    while li:
        print(li, end=" ")
        li = li.next
    print()


if __name__ == "__main__":
    l = Node(9)
    l.next = Node(1)
    l.next.next = Node(9)

    r = Node(2)
    r.next = Node(1)
    r.next.next = Node(3)

    res = add_two_reverse_numbers(l, r)
    print_linked_list(res)
    # print_linked_list(reverse_single_linked_list(res))

    print_linked_list(add_two_numbers(reverse_single_linked_list(l), reverse_single_linked_list(r)))
