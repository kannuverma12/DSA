package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *         Given a binary tree, determine if it is a valid binary search tree
 *         (BST).
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println("Is valid treenode = " + isValidBST(root));

    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public static boolean isValidBST(TreeNode p, double min, double max) {
        if (p == null)
            return true;

        if (p.val <= min || p.val >= max)
            return false;

        return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
