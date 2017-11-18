#!/bin/python3
# coding: utf-8

"""
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-08 23:43"


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class SolutionA(object):
    """超时错误"""
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        _head = None
        _cursor = None
        while True:
            _small_one_val = None
            _small_one_index = None
            for _index in range(len(lists)):
                _l = lists[_index]
                if _l:
                    if _small_one_val is None or _small_one_val > _l.val:
                        _small_one_val = _l.val
                        _small_one_index = _index
            if _small_one_val is None:
                break
            else:
                if _head is None:
                    _head = lists[_small_one_index]
                    _cursor = _head
                else:
                    _cursor.next = lists[_small_one_index]
                    _cursor = lists[_small_one_index]
                lists[_small_one_index] = lists[_small_one_index].next
        return _head


class Solution(object):
    def mergeKLists(self, lists):
        _node_dict = []
        for _l in lists:
            while _l:
                _node_dict.append(_l)
                _l = _l.next
        _node_dict = sorted(_node_dict, key=lambda _d: _d.val)
        _head = None
        _cursor = None
        for _n in _node_dict:
            if _cursor:
                _cursor.next = _n
                _cursor = _n
            else:
                _cursor = _n
                _head = _n
        return _head


def print_ll(_ll):
    while _ll:
        print _ll.val,
        _ll = _ll.next
    print ''


if __name__ == "__main__":
    l1 = ListNode(1)
    l1.next = ListNode(5)
    l1.next.next = ListNode(9)

    l2 = ListNode(2)
    l2.next = ListNode(6)
    l2.next.next = ListNode(10)

    l3 = ListNode(3)

    l4 = None

    l5 = ListNode(-1)
    l5.next = ListNode(12)

    print_ll(Solution().mergeKLists([l3]))
