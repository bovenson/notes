#include <iostream>
#include <set>

using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int res = 0, l = 0;
        set<char> st;
        for (int i=0; i < s.length(); ++i) {
            if (st.find(s[i]) == st.end()) {
                st.insert(s[i]);
                if (st.size() > res) {
                    res = st.size();
                }
            } else {
                while (l < i && s[l] != s[i]) {
                    st.erase(s[l]);
                    ++l;
                }
            }
        }
        return res;
    }
};
