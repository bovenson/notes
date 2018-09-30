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
    void nextPermutation(vector<int>& nums) {
        bool flag = false;
        for (size_t i = nums.size() - 1; i > 0; --i) {
            if (nums[i-1] < nums[i]) {
                flag = true;
                size_t pos;
                for (pos = nums.size() - 1; pos > i; --pos) {
                    if (nums[i-1] < nums[pos]) {
                        break;
                    }
                }
                swap(nums[i-1], nums[pos]);
                sort(nums.begin() + i, nums.end());
                break;
            }
        }
        if (!flag) {
            reverse(nums.begin(), nums.end());
        }
    }
};

int main() {
    return 0;
}
