#include <iostream>
#include "utils.h"

using namespace std;

class Solution {
public:
    bool canJump(vector<int>& nums) {
        int m = 0;
        for (int i=0; i < nums.size(); ++i) {
            if (i > m)
                return false;

            m = max(i + nums[i], m);
        }

        return true;
    }
};

int main() {
    // vector<int> nums = {3, 2, 1, 0, 4};
    // vector<int> nums = {2, 3, 1, 1, 4};
    vector<int> nums = {0};
    Solution s;
    cout << s.canJump(nums) << endl;
    return 0;
}
