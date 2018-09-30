#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>
#include <limits.h>

using namespace std;

class Solution {
public:
    int divide(int dividendi, int divisori) {
        long long dividend = dividendi;
        long long divisor = divisori;
        int res = 0, t, td;
        bool flag = false;
        if (dividend == 0) {
            return 0;
        } else if (dividend == INT_MIN && divisor == -1) {
            return INT_MAX;
        } else if (dividend == INT_MIN && divisor == 1) {
            return INT_MIN;
        }

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            flag = true;
        }
        dividend = dividend < 0 ? -dividend : dividend;
        divisor = divisor < 0 ? -divisor : divisor;
        while (dividend >= divisor) {
            td = divisor;
            t = 1;
            while (dividend > (td << 1) && (td << 1) > 0) {
                td = td << 1;
                t = t << 1;
            }
            res += t;
            dividend -= td;
        }
        return flag ? -res : res;
    }
};

int main() {
    Solution s;
    cout << INT_MIN << " " << INT_MAX << endl;
    cout << s.divide(10, 3) << endl;
    cout << s.divide(7, -3) << endl;
    cout << s.divide(2147483647, 1) << endl;
    cout << s.divide(-2147483648, 1) << endl;
    return 0;
}
