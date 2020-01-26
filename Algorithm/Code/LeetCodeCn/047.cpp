#include <iostream>
#include <vector>
#include <algorithm>
#include "utils.h"

using namespace std;

class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<vector<int>> res;
        std::sort(nums.begin(), nums.end());
        while (true) {
            // printVector(nums);
            res.push_back(nums);

            if (nums.size() < 2)
                break;

            int i, j;
            for (i = nums.size() - 2; i >= 0; --i) {
                if (nums[i] < nums[i+1])
                    break;
            }

            if (i < 0)
                break;

            for (j = nums.size() - 1; j > i; --j) {
                if (nums[j] > nums[i])
                    break;
            }

            swapT(nums[i], nums[j]);
            std::sort(nums.begin()+i+1, nums.end());
        }
        return res;
    }
};

int main() {
    vector<int> nums = {1, 2, 3, 4};
    Solution s;
    s.permuteUnique(nums);

    int ip;
    nums.clear();
    while (cin >> ip) {
        nums.push_back(ip);
    }
    vector<vector<int>> res = s.permuteUnique(nums);
    for (auto v: res)
        printVector(v);

    return 0;
}

