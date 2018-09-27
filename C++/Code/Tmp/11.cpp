#include <iostream>

using namespace std;

class A { };
class B : public A { };

int main() {
    B b;
    A a = b;
    return 0;
}
