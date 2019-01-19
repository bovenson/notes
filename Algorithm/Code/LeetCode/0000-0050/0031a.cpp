/**
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include "utils.cpp"
using namespace std;

class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        for (auto iter=nums.end()-1; iter != nums.begin()-1; --iter) {
            if (iter != nums.end()-1 && *iter < *(iter+1)) {
                iter_swap(iter, iter+1);
                sort(iter+1, nums.end());
                return;
            }
        }
        sort(nums.begin(), nums.end());
    }
};

int main() {
    // vector<int> v{1, 2, 5, 3, 4};
    vector<int> v{3, 2, 1};
    (new Solution())->nextPermutation(v);
    printVector(v);
    return 0;
}
