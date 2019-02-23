package com.kv.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author karanverma
 * Find Maximum value in a Binary Tree
 *
 */
public class FindMaxInBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		System.out.println(findMaxInBT(root));
		
		System.out.println(findMaxInBTWithoutRecursion(root));
	}

    private static int findMaxInBT(TreeNode root) {
        int root_val, left, right, max = Integer.MIN_VALUE;
        if (root != null) {
            root_val = root.data;

            left = findMaxInBT(root.left);
            right = findMaxInBT(root.right);

            if (left > right)
                max = left;
            else
                max = right;

            if (root_val > max)
                max = root_val;

        }
        return max;
    }

    private static int findMaxInBTWithoutRecursion(TreeNode root) {
        TreeNode temp;
        int max = Integer.MIN_VALUE;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);

        while (!q.isEmpty()) {
            temp = q.poll();

            if (max < temp.data)
                max = temp.data;
            if (temp.left != null)
                q.add(temp.left);
            if (temp.right != null)
                q.add(temp.right);
        }
        return max;
    }

}
