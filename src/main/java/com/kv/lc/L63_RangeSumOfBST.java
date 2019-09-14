package com.kv.lc;

import java.util.Stack;

/**
 * Given the root node of a binary search tree, return the sum of values of all nodes with
 * value between L and R (inclusive).
 *
 * The binary search tree is guaranteed to have unique values.
 *
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 *
 *          10
 *         /  \
 *        5    15
 *       / \     \
 *      3   7     18
 */
public class L63_RangeSumOfBST {

    int ans = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {

        // recursive(root, L, R);
        // return ans;
        int retVal = iterative(root, L, R);
        return retVal;
    }

    //method 1 : use this
    private int iterative(TreeNode root, int L, int R){
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }

    private void dfs(TreeNode root, int L, int R){
        while (root != null) {
            if(L<=root.val && root.val<=R)
                ans += root.val;
            if(L<root.val){
                dfs(root.left, L, R);
            }
            if(R>root.val){
                dfs(root.right, L, R);
            }
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}

