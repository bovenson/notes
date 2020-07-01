#include <iostream>

using namespace std;

class A {
    public:
    void f(int a=1, int b=2);
};

void A::f(int c, int d) {
    std::cout << c << " " << d << std::endl;
}

int main() {
    A a;
    a.f();
    return 0;
}   
/** OUTPUT
1 2
*/
