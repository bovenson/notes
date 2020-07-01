#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

class Solution {
public:
    int minNag = 0x80000000;
    int maxPos = 0x7fffffff;
    vector<int> nv, pv;
    Solution() {
        itov(minNag, nv);
        itov(maxPos, pv);
        // printVector(nv);
        // printVector(pv);
    }

    void itov(int v, vector<int>& tv) {
        if (v == 0)
            return;
        itov(v / 10, tv);
        tv.push_back(v % 10);
    }

    void itovr(int v, vector<int>& tv) {
        while (v != 0) {
            tv.push_back(v % 10);
            v /= 10;
        }
    }

    int vtoi(vector<int>& tv) {
        int res = 0;
        for (auto &i : tv) {
            res = res * 10 + i;
        }
        return res;
    }

    int reverse(int x) {
        vector<int> xv;
        itovr(x, xv);
        // printVector(xv);
        if (xv.size() == pv.size() && x > 0) {
            for (int i=0; i < pv.size(); ++i) {
                if (xv[i] > pv[i])
                    return 0;
                else if (xv[i] < pv[i])
                    break;
            }
        } else if (xv.size() == nv.size() && x < 0) {
            for (int i=0; i < nv.size(); ++i) {
                if (xv[i] < nv[i])
                    return 0;
                else if (xv[i] > nv[i])
                    break;
            }
        }
        return vtoi(xv);
    }
};

int main() {
    Solution s;
    int res = s.reverse(-123);
    cout << res << endl;
    // cout << s.minNag << endl;
    // cout << s.maxPos << endl;

    cout << s.reverse(120) << endl;
    cout << s.reverse(2147483647) << endl;
    cout << s.reverse(1563847412) << endl;
    cout << s.reverse(-2143847412) << endl;
    return 0;
}
