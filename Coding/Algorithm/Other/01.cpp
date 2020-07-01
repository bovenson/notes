#include <iostream>
#include <algorithm>
#include <string.h>

using namespace std;

int n, ai[100], bi[100], pi[100], qi[100];
int v[100][121];


int solve(int cur, int left) {
    if (cur >= n) {return 0;}
    if (v[cur][left] > 0) {
        return v[cur][left];
    }
    int res = 0;
    res = max(res, solve(cur+1, left));
    if (left >= pi[cur]) {
        res = max(res, solve(cur+1, left - pi[cur]) + ai[cur]);
    }
    if (left >= qi[cur]) {
        res = max(res, solve(cur+1, left - qi[cur]) + bi[cur]);
    }
    v[cur][left] = res;
    return res;
}

int main() {
    memset(v, 0, sizeof(int) * 100 * 120);
    cin >> n;
    for (int i=0; i < n; ++i) {
        cin >> pi[i] >> ai[i] >> qi[i] >> bi[i];
        // cout << pi[i] << bi[i];
    }
    cout << solve(0, 120);
    return 0;
}
