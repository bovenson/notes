#include <iostream>

using namespace std;

int main() {
    int n = 1100, flag = 1, count = 0;
    while (flag) {
        if (flag & n) {
            ++count;
        }
        flag <<= 1;
    }
    cout << count << endl;

    count = 0;
    while (n) {
        ++count;
        n = (n - 1) & n;
    }
    cout << count << endl;
    return 0;
}
