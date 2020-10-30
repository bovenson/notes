#include <iostream>
#include "utils.h"

using namespace std;

int main() {
    int a = r<int>();
    cout << a << endl;

    vector<int> arr = rV<int>();
    pV(arr);
    return 0;
}
