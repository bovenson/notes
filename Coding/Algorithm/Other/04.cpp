#include <iostream>
#include <string.h>

using namespace std;

bool solve(int n, bool arr[][n]) {
    return true;
}

int main() {
    int num;
    cin >> num;
    for (int i=0; i < num; ++i) {
       int n, m;
       cin >> n >> m;
       bool arr[n][n];
       memset(arr, 0, sizeof(int) * n * n);
       for (int j=0; j < m; ++j) {
           int a, b;
           cin >> a >> b;
           arr[a][b] = true;
           arr[b][a] = true;
       }
       if (solve(n, arr)) {
           cout << "Yes" << endl;
       } else {
           cout << "No" << endl;
       }
    }
    return 0;
}
