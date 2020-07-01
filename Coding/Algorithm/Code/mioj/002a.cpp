#include <iostream>

using namespace std;

int main() {
    int cur = 0, num = 0;
    while (cin >> cur) { num ^= cur; }
    cout << num;
    return 0;
}
