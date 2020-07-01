#include <iostream>

using namespace std;

class A {
public:
    int a;
};

int main() {
    A a;
    cout << a.a << endl;
    a.a = 1;
    cout << a.a << endl;
}
