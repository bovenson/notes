#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int sum;
        vector<vector<int>> res;
        for (size_t i = 0; i + 2U < nums.size(); i++) {
            size_t j = i + 1, k = (int)(nums.size()) - 1;
            if (nums[i] > 0) break;
            while (j < k) {
                sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.push_back(vector<int>({nums[i], nums[j], nums[k]}));
                }
                if (sum <= 0) {
                    while (j < k && nums[j] == nums[++j]) {};
                }
                if (sum >= 0) {
                    while (j < k && nums[k] == nums[--k]) {};
                }
            }
            while (i + 1 < nums.size() && nums[i+1] == nums[i]) {i++;};
        }
        return res;
    }
};

void display(vector<vector<int>> v) {
    for (auto i : v) {
        for (auto j : i) {
            cout << j << " ";
        }
        cout << endl;
    }
}

int main() {
    vector<int> v1 = {-1, 0, 1, 2, -1, -4};
    vector<vector<int>> res;
    Solution s;
    res = s.threeSum(v1);
    display(res);
    v1 = {-1, 0, 1};
    res = s.threeSum(v1);
    display(res);
    v1 = {0, 0, 0, 0};
    res = s.threeSum(v1);
    display(res);
    v1 = {};
    res = s.threeSum(v1);
    display(res);
    return 0;
}
