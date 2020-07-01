# coding: utf-8
"""
反转链表
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
"""


# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
    
    def __str__(self):
        return str(self.val)

class Solution:
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if not head:
            return None
        last = None
        cur  = head
        nt   = head.next
        while nt:
            cur.next = last
            last = cur
            cur = nt
            nt = nt.next
        cur.next = last
        return cur

def display(node):
    while node:
        print(node.val, end=' ')
        node = node.next
    print()

if __name__ == '__main__':
    s = Solution()
    h = ListNode(1)
    h.next = ListNode(2)
    h.next.next = ListNode(3)
    h.next.next.next = ListNode(4)
    h.next.next.next.next = ListNode(5)

    display(h)

    res = s.reverseList(h)
    display(res)

