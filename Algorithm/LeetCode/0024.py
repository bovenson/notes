#!/bin/python3
# coding: utf-8

"""
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space.
You may not modify the values in the list, only nodes itself can be changed.
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-09 23:12"


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None:
            return None
        _head = head if head.next is None else head.next
        _last = None
        _cursor = head

        while _cursor:
            if _cursor.next:
                _next = _cursor.next
                _cursor.next = _next.next if _next else None
                _next.next = _cursor
                if _last:
                    _last.next = _next
                _last = _cursor
                _cursor = _cursor.next
            else:
                _cursor = _cursor.next
        return _head


def print_ll(_ll):
    while _ll:
        print _ll.val,
        _ll = _ll.next
    print ''


if __name__ == "__main__":
    l = ListNode(1)
    l.next = ListNode(2)
    l.next.next = ListNode(3)
    l.next.next.next = ListNode(4)
    print_ll(Solution().swapPairs(l))
    print_ll(Solution().swapPairs(ListNode(1)))
    print_ll(Solution().swapPairs(None))

