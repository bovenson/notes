#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if (head == nullptr)
            return head;

        ListNode* pt = head, *hd = head, *res = head;
        int n = 0, cur = 0;
        while (pt != nullptr) {
            pt = pt->next;
            ++n;
        }

        k %= n;
        pt = head;

        while (pt != nullptr && pt->next != nullptr) {
            if ((cur++) >= k) 
                hd = hd->next;
            pt = pt->next;
        }

        if (pt != hd) {
            pt->next = head;
            res = hd->next;
            hd->next = nullptr;
        }

        return res; 
    }
};

int main() {
    return 0;
}
