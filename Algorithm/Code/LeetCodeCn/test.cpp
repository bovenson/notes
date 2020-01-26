#include <iostream>
#include "utils.h"

using namespace std;

int main() {
    int a = 123, b = 234;
    cout << (a ^ b) << " " << ((a ^ b) ^ a) << endl;
    cout << a << " " << b << endl;
    swapT(a, b);
    cout << a << " " << b << endl;
    return 0;
}
