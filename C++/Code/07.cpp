#include <iostream>

using namespace std;

class A {
public:
    int a;
    A() : a(1) {}
};

A &genARef() {
    A a;
    return a;
}

A genACopy() {
    return A();
}

int main() {
    A a = genARef();
    A b = genACopy();
    cout << a.a << endl;
    cout << b.a << endl;
    return 0;
}
