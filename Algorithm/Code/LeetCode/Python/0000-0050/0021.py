#!/bin/python3
# coding: utf-8

"""
Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 20:33"


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        _head = None
        _cursor = None
        while l1 and l2:
            _small_one = l1 if l1.val < l2.val else l2

            if _cursor:
                _cursor.next = _small_one
                _cursor = _small_one
            else:
                _cursor = _small_one
                _head = _small_one
                
            if _small_one is l1:
                l1 = l1.next
            else:
                l2 = l2.next
        if _cursor:
            _cursor.next = l1 if l1 else l2
        else:
            _head = l1 if l1 else l2
        return _head


def print_ll(_ll):
    while _ll:
        print _ll.val,
        _ll = _ll.next
    print ''


if __name__ == "__main__":
    li1 = ListNode(1)
    li1.next = ListNode(3)
    li1.next.next = ListNode(5)
    li1.next.next.next = ListNode(7)
    li1.next.next.next.next = ListNode(9)
    print_ll(li1)

    li2 = ListNode(2)
    li2.next = ListNode(4)
    li2.next.next = ListNode(6)
    print_ll(li2)

    print_ll(Solution().mergeTwoLists(li1, li2))
    print_ll(Solution().mergeTwoLists(None, None))

    l3 = ListNode(1)
    print_ll(Solution().mergeTwoLists(l3, None))
    