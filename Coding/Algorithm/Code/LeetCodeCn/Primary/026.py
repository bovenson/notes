# coding: utf-8

"""
环形链表
给定一个链表，判断链表中是否有环。

进阶：
你能否不使用额外空间解决此题？
"""


# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if not head:
            return False
        cursor = head.next
        head.next = None
        while cursor and cursor != head:
            nxt = cursor.next
            cursor.next = head
            cursor = nxt
        return cursor != None

