#include <iostream>

using namespace std;

class A {
    int a_;
    double b_;
    public:
    A (int a) : a_(a) {}
    A (double b) : b_(b) { std::cout << "NEW A" << std::endl; }
    void print() { std::cout << a_ << std::endl; }
    void printb() { std::cout << b_ << std::endl; }
};

int main() {
    A a1(10); a1.print();   /// OUTPUT: 10
    A a2 __attribute__((unused)) = 12; a2.print(); /// OUTPUT: 12
    for (int i=1; i <= 3; ++i) {
        static A a3 __attribute__((unused)) = 1.1 * i;
        a3.printb();
    }
    return 0;
} /** OUTPUT
10
12
NEW A
1.1
1.1
1.1
*/
