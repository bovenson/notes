#!/bin/python3
# coding: utf-8

"""
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-09 23:35"


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if head is None:
            return None
        _cursor = head
        _res_head = None
        _res_tail = None
        _append_head = None
        _append_tail = None
        _cnt = 0
        _len = 0
        while _cursor is not None:
            _len += 1
            _cursor = _cursor.next

        _cursor = head
        _cycle = _len // k
        while _cursor is not None and _cycle > 0:
            _next_node = _cursor.next
            if _append_tail is None and _append_head is None:
                _append_tail = _cursor
                _append_tail.next = None
                _append_head = _cursor
            else:
                _cursor.next = _append_head
                _append_head = _cursor

            _cnt += 1
            if _cnt == k:
                _cycle -= 1
                if _res_tail is None and _res_head is None:
                    _res_tail = _append_tail
                    _res_tail.next = None
                    _res_head = _append_head
                else:
                    _res_tail.next = _append_head
                    _res_tail = _append_tail
                    _res_tail.next = None

                _cnt = 0
                _append_head = None
                _append_tail = None
            _cursor = _next_node

        if _res_tail is not None:
            _res_tail.next = _cursor
            return _res_head
        else:
            return head
#         if head is None:
#             return head
#
#         _tail = None
#         _last = None
#         _current = head
#         _cnt = 0
#         _head = None
#         _last_segment_tail = None
#
#         while _current:
#             _cursor = _current
#             _k = k
#             while _k > 0 and _cursor:
#                 _cursor = _cursor.next
#                 _k -= 1
#             if _k > 0:
#                 if _last_segment_tail:
#                     _last_segment_tail.next = _current
#                 if _head is None:
#                     _head = head
#                 break
#
#             if _tail is None:
#                 _tail = _current
#             _next = _current.next
#             _current.next = _last
#             _last = _current
#             _current = _next
#             _cnt += 1
#
#             if _cnt >= k:
#                 _cnt = 0
#                 if _head is None:
#                     _head = _last
#                 if _last_segment_tail:
#                     _last_segment_tail.next = _last
#                 _last_segment_tail = _tail
#                 _last = None
#         return _head
#


def print_ll(_ll):
    while _ll:
        print _ll.val,
        _ll = _ll.next
    print ''


if __name__ == "__main__":
    l = ListNode(1)
    # l.next = ListNode(2)
    # l.next.next = ListNode(3)
    # l.next.next.next = ListNode(4)
    # l.next.next.next.next = ListNode(5)
    print_ll(Solution().reverseKGroup(l, 2))

