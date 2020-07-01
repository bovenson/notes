#include <iostream>

using namespace std;

class A { };

class B {
    public:
    B(A a) {}
};

void fun(B b) {
    cout << "OK? OK!" << endl;
}

int main() {
    A a;
    fun(a);
    return 0;
}
