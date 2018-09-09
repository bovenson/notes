#include <iostream>

using namespace std;

int main() {
    int a = 0;
    int &b = a;
    a = 2;
    cout << b << endl;
    int c;
    const int &d = c;
    return 0;
}
