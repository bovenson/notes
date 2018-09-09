#include <iostream>

using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        int l = 0, r = 0, maxLen=0, maxLeft=0, len = s.length();
        for (int i = 0; i < len;) {
            l = r = i;
            while (r + 1 < len && s[r] == s[r+1]) {++r;}
            i = r + 1;
            while (l >= 0 && r < len && s[l] == s[r]) { --l; ++r;}
            if (r - l + 1 > maxLen) {
                maxLeft = l + 1;
                maxLen = r - l + 1;
            }
        }
        return s.substr(maxLeft, maxLen);
    }
};

int main() {
    Solution s;
    cout << s.longestPalindrome("") << endl;
    cout << s.longestPalindrome("a") << endl;
    cout << s.longestPalindrome("aaaa") << endl;
    cout << s.longestPalindrome("aaiaa") << endl;
    cout << s.longestPalindrome("babad") << endl;
    return 0;
}
