#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <climits>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;
const int MAXX = 1000 * 100;

int solve(int cur, int cnt, int foods[], int v[][MAXX], int n, int X, int &res) {
    if (cur >= n) {
        if (cnt >= X) return cnt;
        else return INT_MAX;
    }
    if (v[cur][cnt] > 0) return v[cur][cnt];
    if (cnt >= X) {
        return cnt;
    }
    int t = min(solve(cur+1, cnt, foods, v, n, X, res), solve(cur+1, cnt+foods[cur], foods, v, n, X, res));
    v[cur][cnt] = t;
    return t;
}

int main() {
    int n, X, res = INT_MAX;
    cin >> n >> X;
    int foods[n];
    int v[n][MAXX];
    memset(v, 0, sizeof(int) * n * MAXX);
    for (int i=0; i < n; ++i) {
        cin >> foods[i];
    }
    cout << solve(0, 0, foods, v, n, X, res) << endl;
    return 0;
}
