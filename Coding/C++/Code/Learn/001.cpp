#include <iostream>

using namespace std;

int func() { int a; return a; }
double fund() { double b; return b; }


int main() {
    const int  a = 1;
    // int *b = &a;     ERROR
    const int *c = &a;  

    cout << func << endl;
    cout << &fund << endl;
    return 0;
}
