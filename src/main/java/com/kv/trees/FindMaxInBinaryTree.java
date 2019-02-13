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
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		System.out.println(findMaxInBT(root));
		
		System.out.println(findMaxInBTWithoutRecursion(root));
	}

	private static int findMaxInBT(Node root) {
		int root_val, left, right, max = Integer.MIN_VALUE;
		if(root != null) {
			root_val = root.data;
			
			left = findMaxInBT(root.left);
			right = findMaxInBT(root.right);
			
			if(left > right)
				max = left;
			else
				max = right;
			
			if(root_val > max)
				max = root_val;
			
		}
		return max;
	}
	
	private static int findMaxInBTWithoutRecursion(Node root) {
        Node temp;
        int max = Integer.MIN_VALUE;
        
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        
        while(!q.isEmpty()) {
            temp = q.poll();
            
            if(max < temp.data)
                max = temp.data;
            if(temp.left != null)
                q.add(temp.left);
            if(temp.right != null)
            q.add(temp.right);
        }
        return max;
    }

}
