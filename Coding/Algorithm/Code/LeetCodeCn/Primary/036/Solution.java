/*
最大子序和
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * */


class Solution {
    public int maxSubArray(int[] nums) {
        int minSum = 0;
        int curSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            curSum += nums[i];
            if (i == 0) {
                res = nums[i];
            } else {
                res = curSum - minSum > res ? curSum - minSum : res;
            }
            minSum = curSum < minSum ? curSum : minSum;
        }
        return res;
    }

    public static void main(String args[]) {
        // int l[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int l[] = {1};
        Solution s = new Solution();
        int res = s.maxSubArray(l);
        System.out.println(res);
    }
}
