#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        m.reserve(nums.size());
        for (int i = 0; i < nums.size(); ++i) {
            int num = nums[i];
            std::unordered_map<int, int>::iterator it = m.find(target - num);
            if (it == m.end()) {
                m.emplace(nums[i], i);
            } else {
                return {it->second, i};
            }
        }
        return {0, 0};
    }
};

int main() {
    Solution s;
    int target = r<int>();
    vector<int> arr = rV<int>();
    pV(s.twoSum(arr, target));
    return 0;
}
