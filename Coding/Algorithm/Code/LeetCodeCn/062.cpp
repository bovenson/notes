#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

class Solution {
    static int fact(int n) {
        if (n <= 2)
            return n;
        return n * fact(n-1);
    }
public:
    int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1)
            return 1;
        long ft = 1;
        int mn = min(m, n) - 1;
        int sm = m + n - 2;
        int k = mn;
        while (--k >= 0) ft *= (sm - k);
        while (mn > 1) ft /= mn--;
        return ft;
        // return fact(m+n-2) / fact(m-1) / fact(n-1);
    }
};

int main() {
    Solution s;
    cout << s.uniquePaths(7, 3) << endl;
    return 0;
}
