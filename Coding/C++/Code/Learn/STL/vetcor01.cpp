#include <iostream>
#include <vector>

using namespace std;

class A {
public:
    A(int val) {
        this->val = val;
    }
    int val;
};

int main() {
    std::vector<A> v;
    A a = new A(1);
    v.push_back(a);
    
    for (auto item : v) {
        cout<<v<<endl;
    }

    delete a;
    return 0;
}
