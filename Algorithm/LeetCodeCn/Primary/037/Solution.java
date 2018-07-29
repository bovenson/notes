/*
打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * */
import java.lang.Math;

class Solution {
    public int solve(int[] nums, int[] v, int pos) {
        if (pos >= nums.length) {
            return 0;
        }
        if (v[pos] >= 0) {
            return v[pos];
        }
        return v[pos] = Math.max(solve(nums, v, pos + 2) + nums[pos], solve(nums, v, pos+1));
    }

    public int rob(int[] nums) {
        int v[] = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) { v[i] = -1; }
        return solve(nums, v, 0);
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        int l[] = {2, 7, 9, 3, 1};
        // int l[] = {1, 2, 3, 1};
        System.out.println(s.rob(l));
    }
}
