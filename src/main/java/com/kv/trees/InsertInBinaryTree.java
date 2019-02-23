package com.kv.trees;

public class InsertInBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		System.out.println(insertInBT(root, 8));

	}

	private static char[] insertInBT(TreeNode root, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
