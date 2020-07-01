#include <iostream>

#define TOSTR(s)    #s
#define CONTACT(a, b)   a##b

using namespace std;

int main() {
    cout << TOSTR(hello kitty) << endl;
    cout << CONTACT(1, 2) << endl;
    // cout << CONTACT(1, 2)(3, 4) << endl;
    return 0;
}
