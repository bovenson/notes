#include <iostream>
#include "12.h"

using namespace std;

class B : public A {
public:
    void f() {}
};

int main() {
    B b;
    return 0;
}
