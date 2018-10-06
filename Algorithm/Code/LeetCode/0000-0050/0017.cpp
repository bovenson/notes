#include <vector>
#include <unordered_map>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> letterCombinations(string digits) {
        unordered_map<char, string> m = {
            {'2', "abc"},
            {'3', "def"},
            {'4', "ghi"},
            {'5', "jkl"},
            {'6', "mno"},
            {'7', "pqrs"},
            {'8', "tuv"},
            {'9', "wxyz"}
        };
        vector<string> res[2];
        res[0].reserve(100000);
        res[1].reserve(100000);
        int ptr = 0;
        unsigned int i, j, len = digits.size(), dlen;
        for (i = 0; i < len; ++i) {
            dlen = m[digits[i]].size();
            for (j = 0; j < dlen; ++j) {
                if (i != 0) {
                    for (auto &item : res[1-ptr]) {
                        res[ptr].push_back(item + m[digits[i]][j]);
                    }
                } else {
                    res[ptr].push_back(string(1, m[digits[i]][j]));
                }
            }
            ptr = 1 - ptr;
            res[ptr].clear();
        }
        return res[1-ptr];
    }
};

int main() {
    Solution s;
    auto res = s.letterCombinations("23");
    for (auto item : res) {
        cout << item << endl;
    }
    return 0;
}
