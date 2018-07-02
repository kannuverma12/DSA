package com.kv.trees;

import java.util.LinkedList;
import java.util.Queue;


public class FindElementInBinaryTree {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		System.out.println(findElementInBT(root, 1));
		
		System.out.println(findElementInBTWithoutRecursion(root,10));

	}

	
	// it can be done using a queue
	private static boolean findElementInBTWithoutRecursion(Node root1, int data) {
		Node temp;
		Queue<Node> q = new LinkedList<Node>();
		if(root1 == null) return false;
		q.add(root1);
		while(!q.isEmpty()) {
			temp = q.poll();
			if(temp.data == data)
				return true;
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
			
		}
		
		return false;
	}

	private static boolean findElementInBT(Node root, int data) {
		boolean temp;
		if(root == null)
			return false;
		else {
			if(root.data == data)
				return true;
			else {
				temp = findElementInBT(root.left, data);
				if(temp)
					return true;
				else
					return findElementInBT(root.right, data);	
			}
		}
	}

}
