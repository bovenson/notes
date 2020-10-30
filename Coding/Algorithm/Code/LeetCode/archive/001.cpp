#include <iostream>
#include "utils.h"
#include "leetcode.h"

using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        std::map<int, int> m;
        std::vector<int> res;
        for (int i = 0; i < nums.size(); ++i) {
            int remain = target - nums[i];
            auto it = m.find(remain);
            if (it != m.end()) {
                res.emplace_back(it->second);
                res.emplace_back(i);
                return res;
            } else {
                m.emplace(nums[i], i);
            }
        }
        return res;
    }
};

int main() {
    Solution s;
    std::vector<int> v = readVec("in");
    printVector(v);
    std::vector<int> res = s.twoSum(v, 9);
    printVector(res);
    return 0;
}
