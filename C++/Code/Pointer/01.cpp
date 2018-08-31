#include <iostream>

using namespace std;

int main () {
    int i = 6;
    int *p_i = &i;
    cout << "  i : " << i << endl;
    cout << "  &i: " << &i << endl;
    cout << "p_i : " << p_i << endl;
    cout << "*p_i: " << *p_i << endl;
    return 0;
}
