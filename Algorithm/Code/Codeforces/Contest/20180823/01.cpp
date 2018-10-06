#include <iostream>

using namespace std;

int main() {
    int n, t;
    bool flag = false;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> t;
        if (t) {
            flag = true;
            break;
        }
    }
    if (flag) {
        cout << "HARD" << endl;
    } else {
        cout << "EASY" << endl;
    }
    return 0;
}
