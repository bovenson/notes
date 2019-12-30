#include <iostream>

using namespace std;

class SolutionB {
public:
    string multiply(string num1, string num2) {
        string res;
        for (int i = num2.length() - 1; i >= 0; --i) {
            for (int j = num1.length() - 1; j >= 0; --j) {
                int cur = num2.length() + num1.length() - 2 - i - j;
                int upg = 0;
                if (res.length() <= cur) {
                    int sum = (num2[i] - '0') * (num1[j] - '0') + upg;
                    upg = sum / 10;
                    sum %= 10;
                    res += sum;
                } else {
                    int sum = res[cur] + (num2[i] - '0') * (num1[j] - '0') + upg;
                    upg = sum / 10;
                    res[cur] = sum % 10;
                }

                if (upg > 0) {
                    if (res.length() <= cur + 1)
                        res += upg;
                    else
                        res[cur+1] += upg;
                }
            }
        }

        string buf;
        for (int i = res.size() - 1; i >= 0; --i) {
            if (buf.size() == 0 && res[i] == 0 && i != 0)
                continue;
            buf += '0' + res[i];
        }
        return buf; 
    }
};

class Solution {
    public:
        string multiply(string num1, string num2) {
            int len = num1.length() + num2.length();
            string res(len, '0');
            
            for (int i = num1.length() - 1; i >= 0; --i) {
                for (int j = num2.length() - 1; j >= 0; --j) {
                    int pos = 1 + i + j;
                    int sum = res[pos] - '0' + (num1[i] - '0') * (num2[j] - '0');
                    res[pos] = sum % 10 + '0';
                    res[pos-1] += sum / 10; 
                }
            }

            int idx = 0;
            while (idx < len - 1 && res[idx] == '0') ++idx;
            return res.substr(idx, res.length() - idx);
        }
};

int main() {
    Solution s;
    string a, b;
    while (true) {
        cin >> a >> b;
        cout << s.multiply(a, b) << endl;
    }
    return 0;
}
