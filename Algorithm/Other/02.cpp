#include <iostream>
#include <string.h>

using namespace std;

int solve(int arr[], int n, int m) {
    if (n < 1) {
        return 0;
    } else if (n == 1) {
        return 1;
    }
    if (arr[n] > 0) {
        return arr[n];
    }
    int res = n <= m ? 1 : 0;
    for (int i=1; i <= m; ++i) {
        res += solve(arr, n-i, m) % 10007;
        res %= 10007;
    }
    arr[n] = res;
    return res;
}

int main() {
    int n, m;
    cin >> n >> m;
    int arr[n+1];
    memset(arr, 0, sizeof(int) * (n+1));
    cout << solve(arr, n, m);
    return 0;
}
