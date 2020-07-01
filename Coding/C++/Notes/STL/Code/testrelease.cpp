#include <iostream>

using namespace std;

class A {
public:
    int v;
};

int main() {
    A a;
    a.v = 1;
    A *b;
    b = a.release();
    return 0;
}
