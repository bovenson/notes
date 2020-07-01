#include <iostream>
#include <memory>

using namespace std;

class A {
public:
    A() {
        cout << "Construct A" << endl;
    }
    ~A() {
        cout << "Destruct A" << endl;
    }
};

class B {
public:
    shared_ptr<A> a;
};

void f() {
    B b;
    b.a = make_shared<A>();
}

int main() {
    f();
    return 0;
}
