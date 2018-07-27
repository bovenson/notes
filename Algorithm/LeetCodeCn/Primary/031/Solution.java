/*
将有序数组转换为二叉搜索树
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 * */


// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}



public class Solution {
    void insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } 

        TreeNode parent = root;
        while (true) {
            if (parent.val > val) {
                if (parent.left == null) {
                    break;
                }
                parent = parent.left;
            } else {
                if (parent.right == null) {
                    break;
                }
                parent = parent.right;
            }
        }
        if (parent.val > val) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
        while ()
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        
    }
}
