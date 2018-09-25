#include <iostream>

using namespace std;

int main() {
    int x = 0;
    int &r = x;
    int *p = &x;
    int *p2 = &r;
    cout << (p == p2) << endl;  // 1
    return 0;
}
