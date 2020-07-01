#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

unsigned long long res = 0;

void solve(int n, int m, vector<int> &marr, vector<int> rarr, unsigned int cur, unsigned long long count) {
    if (n < 0) return;
    if (cur == marr.size() || m <= 0 || n == 0) {
        res = max(res, count);
        return;
    }
    solve(n, m, marr, rarr, cur + 1, count);
    solve(n - marr[cur], m - 1, marr, rarr, cur + 1, count + rarr[cur]);
}

int main() {
    int n, m, tm, tr;
    vector<int> marr, rarr;
    cin >> n >> m;
    while (scanf("%d %d", &tm, &tr) != EOF) {
        marr.push_back(tm);
        rarr.push_back(tr);
    }
    solve(n, m, marr, rarr, 0U, 0ULL);
    cout << res << endl;
    return 0;
}
