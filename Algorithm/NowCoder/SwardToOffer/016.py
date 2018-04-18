#!/bin/python
# -*- coding:utf-8 -*-
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # 返回合并后列表
    def Merge(self, pHead1, pHead2):
        # write code here
        cur = None
        while pHead1 and pHead2:
            min_node = None
            if pHead1.val <= pHead2.val:
                min_node = pHead1
                pHead1 = pHead1.next
            else:
                min_node = pHead2
                pHead2 = pHead2.next

            if cur is None:
                cur = min_node
                res = min_node
            else:
                cur.next = min_node
                cur = min_node
        unempty = pHead1 if pHead1 is not None else pHead2
        if cur is not None:
            cur.next = unempty
        else:
            res = unempty
        return res


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

    nn1 = ListNode(1)
    nn2 = ListNode(2)
    nn1.next = nn2
    nn3 = ListNode(3)
    nn2.next = nn3
    nn4 = ListNode(4)
    nn3.next = nn4
    nn5 = ListNode(5)
    nn4.next = nn5
    
    res = Solution().Merge(n1, nn1)
    while res:
        print res.val
        res = res.next

