#include <iostream>
#include <memory>

using namespace std;

class A {
public:
    A () {}
    ~A() {
        cout << "Calling A's destructor" << endl;
    }
};

class B {
public:
    B () {}
    ~B() {
        cout << "Calling B's destructor" << endl;
    }
};

int main() {
    auto_ptr<A> a(new A());
    B *b = new B();
    return 0;
}
