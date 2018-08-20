#include <iostream>
#include <stack>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left, *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    TreeNode* reConstructBinaryTree(vector<int> pre, vector<int> vin) {
        if (pre.size() == 0) {
            return nullptr;
        }
        vector<int> leftPre, leftVin, rightPre, rightVin;
        int vIndex = 0;
        while (vin[vIndex] != pre[0]) {
            leftVin.push_back(vin[vIndex++]);
            leftPre.push_back(pre[vIndex]);
        }
        ++vIndex;
        while (vIndex < vin.size()) {
            rightVin.push_back(vin[vIndex]);
            rightPre.push_back(pre[vIndex++]);
        }
        TreeNode *root = new TreeNode(pre[0]);
        root->left = this->reConstructBinaryTree(leftPre, leftVin);
        root->right = this->reConstructBinaryTree(rightPre, rightVin);
        return root;
    }
};

void boradFirstTraversal(TreeNode *node) {
    if (node == nullptr) {
        return;
    }
    std::stack<TreeNode *> s;
    s.push(node);
    while (!s.empty()) {
        TreeNode *t = s.top();
        s.pop();
        cout<<t->val<<" ";
        if (t->left != nullptr) {
            s.push(t->left);
        }
        if (t->right != nullptr) {
            s.push(t->right);
        }
    }
}

int main() {
    int preArray[] = {1, 2, 4, 7, 3, 5, 6, 8};
    int vinArray[] = {4, 7, 2, 1, 5, 3, 8, 6};
    vector<int> pre(preArray, preArray + sizeof(preArray) / sizeof(int));
    vector<int> vin(vinArray, vinArray + sizeof(vinArray) / sizeof(int));
    TreeNode *res = (new Solution())->reConstructBinaryTree(pre, vin);
    boradFirstTraversal(res);
    return 0;
}
