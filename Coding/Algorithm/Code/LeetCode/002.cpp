#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptrptr) {}
 *     ListNode(int x) : val(x), next(nullptrptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *res = nullptr, *cur = nullptr;
        int carry = 0;
        while (l1 != nullptr || l2 != nullptr || carry > 0) {
            if (l1 != nullptr) {
                carry += l1->val;
                l1 = l1->next;
            }
            if (l2 != nullptr) {
                carry += l2->val;
                l2 = l2->next;
            }
            ListNode* nn = new ListNode(carry % 10);
            if (res == nullptr)
                cur = res = nn;
            else {
                cur->next = nn;
                cur = nn;
            }
            carry /= 10;
        }
        return res;
    }
};

int main() {
    std::string in0 = "002-in-0";
    std::string in1 = "002-in-1";
    ListNode *head = readList(in0);
    printList(head);

    Solution s;
    ListNode* r = s.addTwoNumbers(readList(in0), readList(in1));
    printList(r);
    return 0;
}
