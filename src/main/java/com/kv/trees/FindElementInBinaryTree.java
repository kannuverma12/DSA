package com.kv.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 *  @author karanverma
 *  Java program to find an element in a Binary Tree.
 */
public class FindElementInBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		System.out.println(findElementInBT(root, 1));
		
		System.out.println(findElementInBTWithoutRecursion(root,10));

	}

	//Using Recursion
	private static boolean findElementInBT(TreeNode root, int data) {
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
	
	// it can be done using a queue
	private static boolean findElementInBTWithoutRecursion(TreeNode root1, int data) {
		TreeNode temp;
		Queue<TreeNode> q = new LinkedList<>();
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

}
