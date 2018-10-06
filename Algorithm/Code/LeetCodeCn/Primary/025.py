# coding: utf-8
"""
回文链表
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
"""


# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if not head or not head.next:
            return True

        cursor = head
        cnt = 0
        while cursor:
            cnt += 1
            cursor = cursor.next
        mid = cnt // 2
        pre = None
        cur = head
        while mid > 0:
            nxt = cur.next
            cur.next = pre
            pre = cur
            cur = nxt
            mid -= 1
        nxt = cur if cnt % 2 == 0 else cur.next
        while pre and nxt:
            # print(pre.val, nxt.val)
            if pre.val != nxt.val:
                return False
            pre = pre.next
            nxt = nxt.next
        return True

    def isPalindromeOther(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        fast = slow = head
        rev = None
        while fast and fast.next:
            fast = fast.next.next
            rev, rev.next, slow = slow, rev, slow.next
        
        if fast:
            slow = slow.next
        while rev and rev.val == slow.val:
            rev = rev.next
            slow = slow.next
        return not rev


def display(node):
    while node:
        print(node.val, end=' ')
        node = node.next
    print()

if __name__ == '__main__':
    s = Solution()
    h = ListNode(1)

    display(h)

    res = s.isPalindrome(h)
    print(res)
