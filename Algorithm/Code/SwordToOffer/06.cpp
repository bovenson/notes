#include <iostream>
#include <stack>
#include <vector>

/*
 * 链表从尾输出
 * */

using namespace std;

class ListNode {
public:
    ListNode *next;
    int val;
    ListNode(int val) {
        this->val = val;
        this->next = nullptr;
    }
};

void reverse(ListNode *node) {
    std::stack<ListNode*> s;
    while (node != nullptr) {
        s.push(node);
        node = node->next;
    }
    while (!s.empty()) {
        node = s.top();
        cout<<node->val<<" ";
        s.pop();
    }
    cout<<endl;
}

vector<int> reverseToVector(ListNode *head) {
    std::stack<ListNode *> s;
    std::vector<int> res;
    while (head != nullptr) {
        s.push(head);
        head = head->next;
    }
    while (!s.empty()) {
        res.push_back(s.top()->val);
        s.pop();
    }
    return res;
}

void display(ListNode *node) {
    while (node != nullptr) {
        cout<<node->val<<" ";
        node = node->next;
    }
    cout<<endl;
}

int main() {
    ListNode *head = nullptr, *iter, *tail;
    for (int i=0; i < 10; ++i) {
        iter = new ListNode(i);
        if (head == nullptr) {
            head = tail = iter;
        } else {
            tail->next = iter;
            tail = iter;
        }
    }
    display(head);
    reverse(head);
    vector<int> vs = reverseToVector(head);
    for (auto v : vs) {
        cout<<v<<" ";
    }
    cout<<endl;
    return 0;
}
