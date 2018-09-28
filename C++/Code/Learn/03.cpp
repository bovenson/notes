#include <iostream>
#include <vector>

using namespace std;

class A {
public:
    A(int a) {
        cout << "A was constructed" << endl;
    }

    A(const A &a) = delete;
};

int main() {
    vector<A> as;
    A a(1);
    cout << "point 1" << endl;
    // as.emplace_back(a);
    cout << "point 2" << endl;
    // as.push_back(a);

    cout << endl;

    // as.emplace_back(2);
    cout << as.size() << endl;
    return 0;
}
