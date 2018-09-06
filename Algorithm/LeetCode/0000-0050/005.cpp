#include<algorithm>
#include <iostream>

using namespace std;

class Solution {
public:
    int start;
    int solveDP(string &s, int l, int r, int *v) {
        // cout << "L:" << l << " R:" << r << " v:" << v[l * 1000 + r] << endl;
        if (l == r) {
            v[l * 1000 + r] = 1;
            return 1;
        }
        if (l > r) {
            v[l * 1000 + r] = 0;
            return 0;
        }

        if (v[l * 1000 + r] >= 0) {
            return v[1 * 1000 + r];
        }
        int res = 0, t = 0;
        if (s[l] == s[r]) {
            res = max(solveDP(s, l+1, r-1, v)+2, res);
        }

        res = max(solveDP(s, l+1, r, v), res);
        res = max(solveDP(s, l, r-1, v), res);
        v[l * 1000 + r] = res;
        return res;
    }
    string longestPalindrome(string s) {
        int v[1000 * 1000];
        fill_n(v, 1000 * 1000, -1);
        int res = solveDP(s, 0, s.length()-1, v);
        for (int i=0; i < s.length(); ++i) {
            for (int j=s.length(); j >= 0;--j) {
                if (v[i * 1000 + j] == res && j-i+1 == res) {
                    return s.substr(i, j+1);
                }
            }
        }
        return "";
    }
};

int main() {
    Solution s;
    string res = s.longestPalindrome("arrn");
    cout << res << endl;
    return 0;
}
