/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int carry = 0, sum, val;
        ListNode *res, *iter;
        iter = res = nullptr;
        while (l1 || l2 || carry > 0) {
            sum = carry;
            if (l1) { sum += l1->val; l1 = l1->next; }
            if (l2) { sum += l2->val; l2 = l2->next; }
            carry = sum / 10;
            val = sum % 10;
            if (res == nullptr) {
                res = iter = new ListNode(val);
            } else {
                iter->next = new ListNode(val);
                iter = iter->next;
            }
        }
        return res;
    }
};
