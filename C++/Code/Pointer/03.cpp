#include <iostream>
#include <memory>

using namespace std;

class A {};

int main() {
    A *a = new A();
    // auto_ptr<A> ptr1(a);
    // auto_ptr<A> ptr2(a);
    // shared_ptr<A> spr1(a);
    // shared_ptr<A> spr2(a);
    // unique_ptr<A> spr1(a);
    // unique_ptr<A> spr2(a);
    return 0;
}
