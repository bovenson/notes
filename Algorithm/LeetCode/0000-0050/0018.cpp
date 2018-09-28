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
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int> > res;
        if (nums.size() < 4) return res;
        sort(nums.begin(), nums.end());
        int i, j, k, l, sum, diff, len = nums.size(), need, tsum;
        vector<int> item;
        for (i = 0; i < len - 3;) {
            for (j = i + 1; j < len - 2;) {
                need = target - nums[i] - nums[j];
                k = j + 1;
                l = len - 1;
                while (k < l) {
                    tsum = nums[k] + nums[l];
                    if (tsum > need) {
                        while (k < l && nums[l] == nums[--l]) {}
                    } else if (tsum < need) {
                        while (k < l && nums[k] == nums[++k]) {}
                    } else {
                        item.push_back(nums[i]);
                        item.push_back(nums[j]);
                        item.push_back(nums[k]);
                        item.push_back(nums[l]);
                        res.push_back(item);
                        item.clear();
                        while (k < l && nums[l] == nums[--l]) {}
                        while (k < l && nums[k] == nums[++k]) {}
                    }
                }
                while (j < len - 2 && nums[j] == nums[++j]) {}
            }
            while (i < len - 3 && nums[i] == nums[++i]) {}
        }
        return res;
    }
};

int main() {
    return 0;
}
