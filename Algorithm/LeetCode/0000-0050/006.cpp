#include <iostream>

using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        string res;
        res.reserve(s.length());
        int pos = 0;
        for (int i=0; i < numRows; ++i) {
            int it = i;
            while (it < s.length()) {
                res[pos++] = s[it];
                it += 2 * (numRows - i - 1);
                cout << "i: " << i << " it:" << it << " - " << s[it] << endl;
                if (it >= s.length()) {
                    break;
                }
                it += 2 * i;
                if (i != 0 && it < s.length()) {
                    res[pos++] = s[it];
                    cout << "i: " << i << " it:" << it << " - " << s[it] << endl;
                }
            }
        }
        cout << pos << endl;
        return res;
    }
};

int main() {
    Solution s;
    cout << s.convert("PAYPALISHIRING", 3) << endl;
    return 0;
}
