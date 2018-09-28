#include <iostream>

using namespace std;

class A {
  //  private :
    public:
        A() {}
};

class B {
    public:
        B(int a) {}
};

int main() {
    A a;
    B b(1);
    B c = b;
    return 0;
}
