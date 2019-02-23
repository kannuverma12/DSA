package com.kv.trees;

public class PreOrderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		preOrderTraversal(root);
	}

	private static void preOrderTraversal(TreeNode root) {
		if(root != null) {
			System.out.println(root.data);
			preOrderTraversal(root.left);
			preOrderTraversal(root.right);
		}
	}

}
