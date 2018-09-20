#include <iostream>

using namespace std;

int main() {
    int b;
    int *a = NULL ? NULL : &b;
    cout << a << endl;
    return 0;
}
