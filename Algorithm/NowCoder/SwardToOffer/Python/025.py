# -*- coding:utf-8 -*-
# class RandomListNode:
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None
class Solution:
    # 返回 RandomListNode
    def Clone(self, pHead):
        # write code here
        head = None
        last = None
        l_origin = []
        l_clone = []
        while pHead:
            tHead = RandomListNode(pHead.label)
            l_origin.append(pHead)
            l_clone.append(tHead)
            if last is None:
                head = tHead
            else:
                last.next = tHead
            last = tHead
            pHead = pHead.next
        for i in range(len(l_origin)):
            if l_origin[i].random is not None:
                l_clone[i].random = l_clone[l_origin.index(l_origin[i].random)]
        return head

