#include <iostream>

using namespace std;

class A {
    public:
    int v;
    A () {}
    A (const A &a) {
        v = a.v;
        std::cout << "copied" << std::endl;
    }

    void copy(A *a) {
        *this = *a;
    }
};

int main() {
    A *a = new A();
    a->v = 10;
    A b;
    b.v = 20;
    b.copy(a);
    std::cout << b.v << std::endl;  // 10
    return 0;
}
