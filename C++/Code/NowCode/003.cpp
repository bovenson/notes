#include <iostream>
#include <stdio.h>

using namespace std;

int cmp(const void *pa, const void *pb) {
    const int *pc = (const int *)pa;
    const int *pd = (const int *)pb;
    return pd[0] - pc[0];
}

int main() {
    int n;
    cin >> n;
    int arr[n][2];
    for (int i = 0; i < n; ++i) {
        scanf("%d %d\n", &arr[i][0], &arr[i][1]);
    }
    qsort(arr, n, sizeof(arr[0]), cmp);

    int cnt = 0, res[n][2];
    int max = -1;
    for (int i = 0; i < n; ++i) {
        if (arr[i][1] >= max) {
            res[cnt][0] = arr[i][0];
            res[cnt][1] = arr[i][1];
            cnt++;
            max = arr[i][1];
        }
    }

    for (int i = cnt-1; i >= 0; i--) {
        // cout << res[i][0] << " " << res[i][1] << endl;
        printf("%d %d\n", res[i][0], res[i][1]);
    }

    return 0;
}
