package com.kv.trees;

public class PreOrderTraversal {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		
		preOrderTraversal(root);
	}

	private static void preOrderTraversal(Node root) {
		if(root != null) {
			System.out.println(root.data);
			preOrderTraversal(root.left);
			preOrderTraversal(root.right);
		}
	}

}
