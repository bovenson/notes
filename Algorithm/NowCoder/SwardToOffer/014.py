#!/bin/python
# -*- coding:utf-8 -*-
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def FindKthToTail(self, head, k):
        # write code here
        cur = None
        kth = head
        i = k
        while kth and i > 0:
            kth = kth.next
            i -= 1
        if not kth and i > 0:
            return None
        else:
            cur = head
        while kth:
            kth = kth.next
            cur = cur.next
        return cur

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

    res = Solution().FindKthToTail(n1, int(sys.argv[1]))
    print res if res is None else res.val
    pass
