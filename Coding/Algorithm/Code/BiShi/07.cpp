#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

void solve(int n, int m, int64_t arr[], int cur, int res) {

}

int cmp(const void *pa, const void *pb) {
    const int64_t *pc = (const int64_t *)pa;
    const int64_t *pd = (const int64_t *)pb;
    return pc[0] - pd[0];
}

int main() {
    int n, m;
    cin >> n >> m;
    int64_t arr[n][2];
    for (int i = 0; i < n; ++i) {
        cin >> arr[i][0];
        arr[i][1] = i;
    }
    qsort(arr, n, sizeof(arr[0]), cmp);
    for (int i = 0; i < n; ++i) {
        cout << arr[i][0] << " " << arr[i][1] << endl;
    }
    while(m > 0) {

    }
    return 0;
}
