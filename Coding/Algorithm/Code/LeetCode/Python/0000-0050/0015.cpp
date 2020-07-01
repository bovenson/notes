#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    static inline int bfind(vector<int> &nums, int i, int j, int v) {
        int l = i, r = j, mid;
        while (l < r) {
            mid = (r - l) / 2 + l;
            if (nums[mid] == v) {
                return mid;
            } else if (nums[mid] > v) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    void addRes(vector<vector<int>> &res, vector<int> &nums, int i, int j) {
        if (i >= j || (i > 0 && nums[i] == nums[i-1]) || (j < nums.size() - 1 && nums[j] == nums[j+1])) return;
        int k = lower_bound(nums.begin() + i + 1, nums.begin() + j + 1, -nums[i] - nums[j]) - nums.begin();
        if (k > i && k < j) {
            vector<int> sres {nums[i], nums[k], nums[j]};
            res.push_back(sres);
        }
    }

    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> res;
        sort(nums.begin(), nums.end());

        int i = 0, j = nums.size() - 1, k = 0, bound;
        while (i < j) {
            addRes(res, nums, i, j);
            addRes(res, nums, i+1, j);
            addRes(res, nums, i, j-1);
            i++;
            j--;
        }
        return res;
    }
};

int main() {
    vector<int> inp = {-1, 0, -1, 2, -4};
    Solution s;
    auto res = s.threeSum(inp);
    for (auto i : res) {
        for (auto j : i) {
            cout << j << " ";
        }
        cout << endl;
    }
    return 0;
}
