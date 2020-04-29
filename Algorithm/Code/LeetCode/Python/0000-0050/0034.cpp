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
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> res;
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        res.emplace_back(nums.size() > l && nums[l] == target ? l : -1);
        
        l = 0; r = nums.size() - 1; 
        while (l < r) {
            int mid = (r - l + 1) / 2 + l;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        res.emplace_back(nums.size() > l && nums[l] == target ? l : -1);
        return res;
    }
};

int main() {
    return 0;
}
