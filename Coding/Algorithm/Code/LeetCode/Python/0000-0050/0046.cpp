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
    void solvePermute(vector<vector<int>>& res, vector<int> &nums, size_t pos) {
        if (pos == nums.size()) {
            res.emplace_back(nums);
        }
        for (size_t i = pos; i < nums.size(); ++i) {
            swap(nums[pos], nums[i]);
            solvePermute(res, nums, pos + 1);
            swap(nums[pos], nums[i]);
        }
    }

    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> res;
        solvePermute(res, nums, 0);
        return res;
    }
};
int main() {
    return 0;
}
