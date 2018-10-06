#include <iostream>

using namespace std;

struct TreeLinkNode {
    int val;
    struct TreeLinkNode *left;
    struct TreeLinkNode *right;
    struct TreeLinkNode *parent;
    TreeLinkNode(int x) :val(x), left(NULL), right(NULL), parent(NULL) {
        
    }
};

class Solution {
public:
    TreeLinkNode* GetNext(TreeLinkNode* pNode) {
        if (pNode == nullptr) {
            return nullptr;
        }
        TreeLinkNode *next = nullptr;
        if (pNode->right != nullptr) {
            next = pNode->right;
            while (next->left != nullptr) { next = next->left; }
        } else if (pNode->parent != nullptr) {
            TreeLinkNode *parent = pNode->parent, *current = pNode;
            while (parent != nullptr && current == parent->right) {
                current = parent;
                parent = parent->parent;
            }
            next = parent;
        }
        return next;
    }
};

int main() {
    return 0;
}
