#include <iostream>
#include <vector>

using namespace std;

class A {
    public:
    int a;
};

template <typename T>
class B {
    public:
    T t;
};

int main() {
    B<int> b;
    return 0;
}
