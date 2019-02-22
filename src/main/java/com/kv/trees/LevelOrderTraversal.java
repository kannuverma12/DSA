package com.kv.trees;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 
 * @author karan.verma
 * 
 * printLevelorder(tree) or BFS
 * 1) Create an empty queue q
 * 2) temp_node = root /*start from root
 * 3) Loop while temp_node is not NULL
 *    a) print temp_node->data.
 *    b) Enqueue temp_node’s children (first left then right children) to q
 *    c) Dequeue a node from q and assign it’s value to temp_node
 */
public class LevelOrderTraversal {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		levelOrderTravesal(root);

	}

	private static void levelOrderTravesal(Node root) {
		Queue<Node> q = new LinkedList<Node>();
		
		if(root == null)
			return;
		
		q.add(root);
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			System.out.println(temp.data);
			
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
		}
		
	}

}
