#include <iostream>

using namespace std;

class A {
public:
    virtual void f() = 0;
};

class B : public A {
public:
    void f() {}
};

int main() {
    B b;
    return 0;
}
