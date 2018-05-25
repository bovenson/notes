#!/bin/python
# -*- coding:utf-8 -*-
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # 返回从尾部到头部的列表值序列，例如[1,2,3]
    def printListFromTailToHead(self, listNode):
        # write code here
        res = []
        while listNode:
            res.append(listNode.val)
            listNode = listNode.next
        res.reverse()
        return res
        # return res.reverse()


if __name__ == "__main__":
    head = ListNode(3)
    cursor = head
    cursor.next = ListNode(2)
    cursor = cursor.next
    cursor.next = ListNode(1)
    cursor = cursor.next

    print(Solution().printListFromTailToHead(head))

