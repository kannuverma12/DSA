package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *  Note: A leaf is a node with no children.
 *  Example:
 *  
 *  Given the below binary tree and sum = 22,
 *        5
 *       / \
 *      4   8
 *     /   / \
 *    11  13  4
 *   /  \    / \
 *  7    2  5   1
 *  
 *  Return:
 *  [
 *      [5,4,11,2],
 *      [5,8,4,5]
 *   ]
 */
public class DP8_PathSum2 {

    /*
     * This problem can be converted to be a typical depth-first search problem. A recursive 
     * depth-first search algorithm usually requires a recursive method call, a reference to 
     * the final result, a temporary result, etc.
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println("Is valid treenode = " + Arrays.deepToString(pathSum(root, 22).toArray()));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        List<Integer> l = new ArrayList<>();
        l.add(root.val);
        dfs(root, sum - root.val, result, l);
        return result;
    }

    public static void dfs(TreeNode t, int sum, List<List<Integer>> result, List<Integer> l) {
        if (t.left == null && t.right == null && sum == 0) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(l);
            result.add(temp);
        }

        // search path of left node
        if (t.left != null) {
            l.add(t.left.val);
            dfs(t.left, sum - t.left.val, result, l);
            l.remove(l.size() - 1);
        }

        // search path of right node
        if (t.right != null) {
            l.add(t.right.val);
            dfs(t.right, sum - t.right.val, result, l);
            l.remove(l.size() - 1);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
