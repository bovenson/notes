/*
 Shuffle an Array
打乱一个没有重复元素的数组。

示例:

// 以数字集合 1, 2 和 3 初始化数组。
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
solution.shuffle();

// 重设数组到它的初始状态[1,2,3]。
solution.reset();

// 随机返回数组[1,2,3]打乱后的结果。
solution.shuffle();
 * */
import java.lang.Math;


class Solution {
    int ori[] = null;
    private final int nums[];

    public Solution(int[] nums) {
        this.ori = (int[])nums.clone();
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ori[i];
        }
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = nums.length - 1; i >= 0; i--) {
            int swapI = (int) (Math.random() * (i + 1));
            int t = nums[i];
            nums[i] = nums[swapI];
            nums[swapI] = t;
        }
        return nums;
    }

    public static void main(String args[]) {
        int[] l = {1, 2, 3, 4};
        Solution s = new Solution(l);
        for (int i : s.reset()) {
            System.out.println(i);
        }
        for (int i : s.shuffle()) {
            System.out.println(i);
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
