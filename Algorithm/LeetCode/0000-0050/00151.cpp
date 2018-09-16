#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        for (int i=0; i < nums.size(); i++) {
            for (int j=i+1; j < nums.size(); ++j) {
                int need = -nums[i] - nums[j];
                int k = lower_bound(nums.begin() + j + 1, nums.end(), need) - nums.begin();
                if (k > j && k < nums.size() && nums[k] == need) {
                    vector<int> tres = {nums[i], nums[j], nums[k]};
                    res.push_back(tres);
                }
                while (j < nums.size() - 1 && nums[j] == nums[j+1]) j++;
            }
            while (i < nums.size() - 1 && nums[i] == nums[i+1]) i++;
        }
        return res;
    }
};

int main() {
    Solution s;
    vector<int> v = {-1, 0, 1, -1, 2, -4};
    // vector<int> v = {-1, 0, 1};
    // vector<int> v = {0, 0, 0};
    auto res = s.threeSum(v);
    for (auto i : res) {
        for (auto j : i) {
            cout << j << " ";
        }
        cout << endl;
    }
    return 0;
}
