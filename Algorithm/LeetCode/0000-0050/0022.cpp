#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    void solve(int l, int r, vector<string> &res, string &cur) {
        unsigned int size = cur.size();
        if (r > l && r > 0) {
            cur.resize(size+1, ')');
            solve(l, r - 1, res, cur);
            cur.resize(size);
        }
        if (l > 0) {
            cur.resize(size+1, '(');
            solve(l - 1, r, res, cur);
            cur.resize(size);
        }
        if (l == 0 && r == 0) {
            res.emplace_back(cur);
        }
    }

    vector<string> generateParenthesis(int n) {
        vector<string> res;
        string buff;
        buff.reserve(n * 2);
        solve(n, n, res, buff);
        return res;
    }
};

int main() {
    return 0;
}
