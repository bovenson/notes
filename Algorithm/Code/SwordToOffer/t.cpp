#include <iostream>
#include <string.h>
using namespace std;

int main() {
    // char str[20];
    // strcpy(str, "Hello");
    // cout<<str<<endl;
    int *a = new int(1);
    cout << a << endl;
    cout << *a << endl;
    cout << (*a + 1) << endl;
    *a = *a + 1;
    cout << (*a) << endl;
    return 0;
}
