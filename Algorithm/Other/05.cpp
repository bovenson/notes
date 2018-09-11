#include <iostream>

using namespace std;

int main() {
    int a = 0;
    int &b = a;
    a = 2;
    cout << b << endl;
    int c = 2;
    const int &d = c;
    c = 3;
    
    // int &e = 2;      // non-const reference of type 'int&' from an rvalue of type 'int'
    const int &f = 2;
    double vd = 1.1;
    // int &g = vd;     // invalid initialization of reference of type 'int&' from expression of type 'double'
    const int &h = vd;
    return 0;
}
