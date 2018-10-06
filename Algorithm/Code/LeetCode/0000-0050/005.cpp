#include <iostream>
#include <algorithm>

using namespace std;

class Solution {
public:
    int v[1010][1010];
    int rm;
    int solve(string &s, int l, int r) {
        if (l == r) { v[l][r] = 1; rm = max(rm, 1); return 1; }
        if (l > r) { return 0; }
        if (v[l][r] > 0) {
            return v[l][r];
        }
        int res = 0;
        res = max(solve(s, l+1, r), solve(s, l, r-1));
        if (s[l] == s[r]) {
            int t = solve(s, l+1, r-1);
            if (t == r-l-1) {
                res = max(res, t+2);
            } else {
                res = max(res, t);
            }
        }
        v[l][r] =res;
        rm = max(res, rm);
        return res;
    }
    string longestPalindrome(string s) {
        rm = 0;
        memset(v, 0, sizeof(int) * 1010 * 1010);
        solve(s, 0, s.length()-1);
        cout << rm << endl;
        for (int i=0; i < s.length(); ++i) {
            for (int j=s.length(); j >= i; --j) {
                if (v[i][j] == rm && j-i+1==rm) {
                    return s.substr(i, rm);
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
    cout << s.longestPalindrome("") << endl;
    cout << s.longestPalindrome("a") << endl;
    cout << s.longestPalindrome("aaaaa") << endl;
    cout << s.longestPalindrome("aabaa") << endl;
    cout << s.longestPalindrome("abcda") << endl;
    return 0;
}
