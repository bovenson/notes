#include <iostream>

using namespace std;

template <class T>
class A {
public:
    T a;
};

template <typename T>
class B {
public:
    T b;
};

int main() {
    int a = 1;
    int *b = &a;
    A<int> aa;
    A<int *> ab;
    B<int> ba;
    B<int *> bb;
    int::value_type c;
    return 0;
}
