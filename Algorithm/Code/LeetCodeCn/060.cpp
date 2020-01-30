#include <iostream>
#include "utils.h"

using namespace std;

class Solution {
public:
    string getPermutation(int n, int k) {
        int np = 1, mn = 1;
        vector<bool> v(n, false);
        string res;

        for (int i = 2; i <= n; ++i)
            np *= i;

        k -= 1;
        for (int i = n; i >= 1; --i) {
            np /= i;
            int p = k / np;
            if (p == 0) {
                res += to_string(mn);
                v[mn] = true;
                while (v[++mn]);
            } else {
                int st = mn;
                while (p > 0 && st <= n) {
                    if (v[++st] == false)
                        --p;
                }
                res += to_string(st + p);
                v[st+p] = true;
            }

            k %= np;
        }

        // printVector(rc);
        return res;
    }
};

int main() {
    Solution s;
    cout << s.getPermutation(3, 3) << endl;
    return 0;
}
