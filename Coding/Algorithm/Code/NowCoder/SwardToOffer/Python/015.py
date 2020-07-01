#!/bin/python
# -*- coding:utf-8 -*-
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
class Solution:
    # 返回ListNode
    def ReverseList(self, pHead):
        # write code here
        last = None
        while pHead:
            cur = pHead
            nxt = pHead.next
            pHead.next = last
            last = cur
            pHead = nxt
        return last

if __name__ == '__main__':
    import sys
    n1 = ListNode(1)
    n2 = ListNode(2)
    n1.next = n2
    n3 = ListNode(3)
    n2.next = n3
    n4 = ListNode(4)
    n3.next = n4
    n5 = ListNode(5)
    n4.next = n5

    res = Solution().ReverseList(n1)
    while res:
        print res.val
        res = res.next

