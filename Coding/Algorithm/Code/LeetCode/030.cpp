#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

class Solution {
public:
    int findString(vector<string>& words, string& target) {
        for (int i = 0; i < words.size(); ++i) {
            if (words[i].compare(target) == 0) {
                return i; 
            }
        }

        return -1;
    }

    vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> res;
        int endIdx = s.length() - words.size() * words[0].length();
        int wl = words[0].length();

        for (int i = 0; i < endIdx; ++i) {
            string ss = s.substr(i, wl);
            
        }
        return res;
    }
};

int main() {
    string ins = r<string>();
    vector<string> inp = rV<string>();
    Solution s;
    auto res = s.findSubstring(ins, inp);
    pV(res); 
    return 0;
}
