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
import java.lang.Max;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}



public class Solution {

    /**
     * 获取高度
     */
    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    void rightRotate(TreeNode node) {
        TreeNode leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
    }

    void leftRightRotate(TreeNode node) {
        leftRotate(node.left);
        rightRotate(node);
    }

    void leftRotate(TreeNode node) {
        TreeNode rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
    }

    void rightLeftRotate(TreeNode node) {
        rightRotate(node.right);
        leftRotate(node);
    }

    /**
     * 插入节点
     */
    TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } 
        if (root.val > val) {
            root.left = insertNode(root.left, val);
        } else {
            root.right = insertNode(root.right, val);
        }
        int diff = height(root.left) - height(root.right);
        if (diff >= 2) {
            if (root.left.val > val) { // left left
                rightRotate(root);
            } else {    // left right
                leftRightRotate(root);
            }
        } else if (diff <= -2){
            if (root.right < val) {     // right right
                leftRotate(root);
            } else {    // right left
                rightLeftRotate(root);
            }
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        for (int i in nums) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        int nums[] = [1, 2, 3];
        sortedArrayToBST();
    }
}
