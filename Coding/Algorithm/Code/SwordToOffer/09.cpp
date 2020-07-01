#include <iostream>
#include <stack>

using namespace std;

class Solution
{
public:
    void push(int node) {     
        this->stack1.push(node);
    }

    int pop() {
        if (this->stack2.empty()) {
            while (!this->stack1.empty()) {
                this->stack2.push(stack1.top());
                this->stack1.pop();
            }
        }
        int res = this->stack2.top();
        this->stack2.pop();
        return res;
    }

private:
    stack<int> stack1;
    stack<int> stack2;
};

int main() {
    Solution *s = new Solution();
    s->push(1);
    s->push(2);
    s->push(3);
    cout<<s->pop()<<s->pop()<<s->pop();
}
