#include <iostream>

using namespace std;

class A {
public:
    A() {
        cout << "construct instance of A" << endl;
    }
    ~A() {
        cout << "destruct instance of A"
    }
    int a;
    void fi(int v) {
        cout << "fi -> " << v << endl;
    }
    void fo(int v);
};

void A::fo(int v) {
    cout << "fo -> " << v << endl;
}

int main() {
    A a;
    a.a = 1;
    a.fi(1);
    a.fo(2);
    return 0;
}
