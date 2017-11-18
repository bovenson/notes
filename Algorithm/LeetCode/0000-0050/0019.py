#!/bin/python3
# coding: utf-8

"""
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 19:24"


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        _l = []
        _cursor = head
        while _cursor:
            _l.append(_cursor)
            _cursor = _cursor.next
        if n == 1:
            if len(_l) == 1:
                return None
            else:
                _l[-n-1].next = None
        elif n == len(_l):
            head = head.next
        else:
            _l[-n-1].next = _l[-n].next
        return head


def print_ll(_ll):
    while _ll:
        print _ll.val,
        _ll = _ll.next


if __name__ == "__main__":
    ll = ListNode(1)
    ll.next = ListNode(2)
    ll.next.next = ListNode(3)
    ll.next.next.next = ListNode(4)
    ll.next.next.next.next = ListNode(5)
    print_ll(ll)
    print ''
    ll = Solution().removeNthFromEnd(ll, 2)
    Solution().removeNthFromEnd(ll, 1)
    Solution().removeNthFromEnd(ll, 1)
    ll = Solution().removeNthFromEnd(ll, 1)
    ll = Solution().removeNthFromEnd(ll, 1)
    print_ll(ll)

    ll = ListNode(1)
    ll.next = ListNode(2)
    ll = Solution().removeNthFromEnd(ll, 2)
    print_ll(ll)
