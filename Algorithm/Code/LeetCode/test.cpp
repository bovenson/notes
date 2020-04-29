#include <iostream>
#include "utils.h"

using namespace std;

int main() {
    int a[2<<8];
    cout << (2<<8) << " " << sizeof(a);
    return 0;
}
