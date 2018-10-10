#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;
int arr[10010];

bool solve(int cur, int n, int c1, int c2, int mx) {
    if (c1 > n - 1 || c2 > n - 1) return false;
    if (cur >= n) {
        if (c1 == c2 && c1 == n - 1) return true;
        else return false;
    }
    if (solve(cur+1, n, c1+arr[cur], c2, mx)) return true;
    if (solve(cur+1, n, c1, c2+arr[cur], mx)) return true;
    return false;
}

int main() {
    int T, n;
    cin >> T;
    for (int i = 0; i < T; ++i) {
        cin >> n;
        for (int j = 0; j < n; ++j) {
            cin >> arr[j];
        }
        if (solve(0, n, 0, 0, n-1)) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl;
        }
    }
    return 0;
}
