#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int n, sum = 0, tsum;
    string str;
    cin >> n >> str;
    size_t i, len = str.length(), j;
    bool flagRes = false, hasOne;
    for (i = 0U; i < len; ++i) {
        hasOne = false;
        sum += str[i] - '0';
        j = i + 1;
        if (j == len) continue;
        tsum = 0;
        while (j < len) {
            tsum += str[j] - '0';
            if (tsum == sum) {
                tsum = 0;
                hasOne = true;
            } else if (tsum > sum) {
                break;
            }
            ++j;
        }
        if (j == len && tsum == sum) {
            flagRes = true;
            break;
        } else if (j == len && hasOne && tsum == 0) {
            flagRes = true;
            break;
        }
    }
    if (flagRes) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }
    return 0;
}
