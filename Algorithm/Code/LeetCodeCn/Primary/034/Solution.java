/*
爬楼梯
假设你正在爬楼梯。需要 n 步你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 步 + 1 步
2.  2 步
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 步 + 1 步 + 1 步
2.  1 步 + 2 步
3.  2 步 + 1 步
 * */
import java.util.Map;
import java.util.HashMap;
class Solution {
    Map<Integer, Integer> v = new HashMap();
    public int climbStairs(int n) {
        Integer ni = new Integer(n);
        if (v.containsKey(ni)) {
            return v.get(ni);
        }
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int res = climbStairs(n - 1) + climbStairs(n - 2);
        v.put(ni, new Integer(res));
        return res;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(s.climbStairs(0));
        System.out.println(s.climbStairs(1));
        System.out.println(s.climbStairs(2));
        System.out.println(s.climbStairs(44));
    }
}
