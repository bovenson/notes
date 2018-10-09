#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

bool isBeauty(int num, int a, int b) {
    while (num > 0) {
        if (num % 10 != a && num % 10 != b) return false;
        num /= 10;
    }
    return true;
}

int main() {
    int a, b, k, res = 0, i, j, sum, t, m;
    cin >> a >> b >> k;
    for (i = 1; i <= k; ++i) {
        j = k - i;
        sum = i * a + j * b;
        if (isBeauty(sum, a, b)) {
            t = 1;
            m = min(i, j);
            if (m != 0) {
                int arr[m];
                for (int z = 0; z < m; ++z) {
                    arr[z] = k - z;
                }
                int cnt = m;
                while (cnt > 1) {
                    for (int z = 0; z < m; ++z) {
                        if (arr[z] % cnt == 0) {
                            arr[z] /= cnt;
                            --cnt;
                        }
                    }
                }
                for (int z = 0; z < m; ++z) {
                    t *= arr[z];
                    t %= 1000000007;
                }
            }
            res += t;
            res %= 1000000007;
        }
    }
    cout << res << endl;
    return 0;
}
