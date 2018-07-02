package com.kv.trees;

//Java program to check if all leaves are at same level

//A binary tree node
class Node1 {
	int data;
	Node1 left, right;

	
	Node1(int item) {
		data = item;
		left = right = null;
	}
}

class Leaf {
	int leaflevel = 0;
}

public class CHeckBTLeafs {

	Node1 root;
	Leaf mylevel = new Leaf();

	/*
	 * Recursive function which checks whether all leaves are at same level
	 */
	boolean checkUtil(Node1 node, int level, Leaf leafLevel) {
		// Base case
		if (node == null)
			return true;

		// If a leaf node is encountered
		if (node.left == null && node.right == null) {
			// When a leaf node is found first time
			if (leafLevel.leaflevel == 0) {
				// Set first found leaf's level
				leafLevel.leaflevel = level;
				return true;
			}

			// If this is not first leaf node, compare its level with
			// first leaf's level
			return (level == leafLevel.leaflevel);
		}

		// If this node is not leaf, recursively check left and right
		// subtrees
		return checkUtil(node.left, level + 1, leafLevel) && checkUtil(node.right, level + 1, leafLevel);
	}

	/*
	 * The main function to check if all leafs are at same level. It mainly uses
	 * checkUtil()
	 */
	boolean check(Node1 node) {
		int level = 0;
		return checkUtil(node, level, mylevel);
	}

	public static void main(String args[]) {
		// Let us create the tree as shown in the example
		CHeckBTLeafs tree = new CHeckBTLeafs();
		tree.root = new Node1(12);
		tree.root.left = new Node1(5);
		tree.root.left.left = new Node1(3);
		tree.root.left.right = new Node1(9);
		tree.root.left.left.left = new Node1(1);
		tree.root.left.right.left = new Node1(1);
		if (tree.check(tree.root))
			System.out.println("Leaves are at same level");
		else
			System.out.println("Leaves are not at same level");
	}
}
