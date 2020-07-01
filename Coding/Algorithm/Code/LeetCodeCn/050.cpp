#include <iostream>
#include "utils.h"

using namespace std;

class Solution {
public:
    double myPow(double x, int n) {
        long ln = n;
        if (ln < 0) {
            x = 1 / x;
            ln = -ln;
        }

        double curPow = x;
        double res = 1;

        for (long i=ln; i > 0; i/=2) {
            if (i % 2 == 1) 
                res *= curPow;
            curPow = curPow * curPow;
        }

        return res;
    }
};

int main() {
    double st;
    int n;
    cin >> st >> n;
    Solution s;
    cout << s.myPow(st, n) << endl;
    return 0;
}
