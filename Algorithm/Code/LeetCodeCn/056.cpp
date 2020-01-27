#include <iostream>
#include "utils.h"

using namespace std;

class Solution {
    inline static bool cmp(vector<int> &va, vector<int> &vb) {
        return va[0] < vb[0];
    }
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        vector<vector<int>> res;
        if (intervals.size() == 0)
            return res;

        sort(intervals.begin(), intervals.end(), cmp);
        // printVector(intervals);
        vector<int>& init = intervals[0];
        for (int i=1; i < intervals.size(); ++i) {
            vector<int>& cur = intervals[i];
            if (init[1] < cur[0]) {
                res.emplace_back(init);
                init = cur;
            } else if (cur[0] <= init[1] && init[1] <= cur[1]) {
                init[1] = cur[1];
            }
        }

        res.emplace_back(init);
        return res;
    }
};

int main() {
    // vector<vector<int>> intervals = {{2, 6}, {1, 3}, {8, 10}, {15, 18}};
    // vector<vector<int>> intervals = {{1, 4}, {4, 5}};
    // vector<vector<int>> intervals = {{4, 5}};
    // vector<vector<int>> intervals = {};
    vector<vector<int>> intervals = {{1, 4}, {2, 3}};
    Solution s;
    vector<vector<int>> res = s.merge(intervals);
    for (auto &tv: res)
        printVector(tv);
    return 0;
}
