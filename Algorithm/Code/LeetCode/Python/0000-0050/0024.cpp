#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        ListNode *nt, *ntt, *rtn = head, *last = nullptr;
        while (head != nullptr) {
            if (head->next == nullptr) {
                break;
            } else {
                nt = head->next;
                ntt = nt->next;
                if (last == nullptr) {
                    rtn = nt;
                    head->next = ntt;
                    nt->next = head;
                    last = head;
                    head = ntt;
                } else {
                    last->next = nt;
                    nt->next = head;
                    head->next = ntt;
                    last = head;
                    head = ntt;
                }
            }
        }
        return rtn;
    }
};

int main() {
    return 0;
}
