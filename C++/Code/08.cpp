#include <iostream>

using namespace std;

class A {
public:
    A () {}
    A (int v) : a(v) {}
    int a;
};

int main() {
    A a(1), b(2);
    cout << a.a << endl;
    cout << b.a << endl;
    a = b;
    cout << a.a << endl;
    return 0;
}
