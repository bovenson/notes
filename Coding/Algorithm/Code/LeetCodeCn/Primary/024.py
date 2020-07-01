# coding: utf-8

"""
合并两个有序链表
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
"""


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        cursor = None
        head = None
        while l1 and l2:
            if l1.val < l2.val:
                if not cursor:
                    head = l1
                else:
                    cursor.next = l1
                cursor = l1
                l1 = l1.next
            else:
                if not cursor:
                    head = l2
                else:
                    cursor.next = l2
                cursor = l2
                l2 = l2.next
        if cursor:
            cursor.next = l1 if l1 else l2
        else:
            return l1 if l1 else l2
        return head

