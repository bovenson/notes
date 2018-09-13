#include <iostream>

using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        string res;
        res.reserve(s.length());
        int pos = 0;
        for (int i=0; i < numRows; ++i) {
            int it = i, lastIt = -1;
            bool direction = true;
            while (it < s.length()) {
                if (it != lastIt) {
                    res += s[it];
                }
                lastIt = it;
                if (direction) {
                    it += 2 * (numRows - i - 1);
                    direction = !direction;
                } else {
                    it += 2 * i;
                    direction = !direction;
                }
            }
        }
        return res;
    }
};

int main() {
    Solution s;
    cout << s.convert("PAYPALISHIRING", 3) << endl;
    cout << s.convert("PAYPALISHIRING", 4) << endl;
    cout << s.convert("AB", 3) << endl;
    cout << s.convert("A", 1) << endl;
    return 0;
}
