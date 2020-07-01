#include <iostream>

using namespace std;

int main() {
    int a[2][3] = {1, 2, 3, 4, 5, 6};
    cout << *a[0] << " " << *a[1] << endl;
    cout << sizeof(int) << " " << sizeof(int*) << endl;
    return 0;
}
