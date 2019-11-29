package com.kv.trees;

/**
 * @author karanverma
 * <p>
 * Given a Binary Tree, find the maximum sum path from a leaf to root
 * https://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/
 */

public class MaxSumLeafToRootPath {
    /*
     * Given a Binary Tree, find the maximum sum path from a leaf to root. For
     * example, in the following tree, there are three leaf to root paths 8->-2->10,
     * -4->-2->10 and 7->10. The sums of these three paths are 16, 4 and 17
     * respectively. The maximum of them is 17 and the path for maximum is 7->10.
     *
     * 1) First find the leaf node that is on the maximum sum path. In the following
     * code getTargetLeaf() does this by assigning the result to *target_leaf_ref.
     * 2) Once we have the target leaf node, we can print the maximum sum path by
     * traversing the tree. In the following code, printPath() does this.
     *
     */

    TreeNode root;
    TreeNode target_leaf = null;
    int      max         = Integer.MIN_VALUE;

    public static void main(String[] args) {
        MaxSumLeafToRootPath tree = new MaxSumLeafToRootPath();
        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(-2);
        tree.root.right = new TreeNode(7);
        tree.root.left.left = new TreeNode(8);
        tree.root.left.right = new TreeNode(-4);
        System.out.println("Following are the nodes on maximum sum path");

        int sum = tree.maxSumPath();
        System.out.println("");
        System.out.println("Sum of nodes is : " + sum);
    }

    // Returns the maximum sum and prints the nodes on max sum path
    private int maxSumPath() {
        if (root == null)
            return 0;

        // find the target leaf and maximum sum
        getTargetLeaf(root, max, 0);

        // print the path from root to the target leaf
        printPath(root, target_leaf);
        return max; // return maximum sum
    }

    // This function Sets the target_leaf_ref to refer the leaf node of the maximum
    // path sum. Also, returns the max_sum using max_sum_ref
    private void getTargetLeaf(TreeNode node, int max2, int curr_sum) {
        if (node == null)
            return;
        // Update current sum to hold sum of nodes on path from root to this node
        curr_sum = curr_sum + node.data;

        // If this is a leaf node and path to this node has maximum sum so far, the n
        // make this node target_leaf
        if (node.left == null && node.right == null) {
            if (curr_sum > max2) {
                max2 = curr_sum;
                max = max2;
                target_leaf = node;
            }
        }
        // If this is not a leaf node, then recur down to find the target_leaf
        getTargetLeaf(node.left, max2, curr_sum);
        getTargetLeaf(node.right, max2, curr_sum);

    }

    // A utility function that prints all nodes on the path from root to target_leaf
    private boolean printPath(TreeNode node, TreeNode target_leaf) {
        if (node == null)
            return false;

        // return true if this node is the target_leaf or
        // target leaf is present in one of its descendants
        if (node == target_leaf || printPath(node.left, target_leaf) || printPath(node.right,
                target_leaf)) {
            System.out.print(node.data + " ");
            return true;
        }
        return false;
    }

}

/*
// Data structure to store a Binary Tree node
class Node
{
	int data;
	Node left = null, right = null;

	Node(int data) {
		this.data = data;
	}
}

class Main {
	// Function to print root-to-leaf path having given sum in a binary tree
	public static boolean printPath (Node root, int sum)
	{
		// base case
		if (sum == 0) {
			return true;
		}

		// base case
		if (root == null) {
			return false;
		}

		// recur for left and right subtree with reduced sum
		boolean left = printPath(root.left, sum - root.data);
		boolean right = printPath(root.right, sum - root.data);

		// print current node if it lies on path having given sum
		if (left || right) {
			System.out.print(root.data + " ");
		}

		return left || right;
	}

	// Function to calculate maximum root-to-leaf sum in a binary tree
	public static int rootToLeafSum(Node root)
	{
		// base case: tree is empty
		if (root == null) {
			return 0;
		}

		// calculate maximum node-to-leaf sum for left child
		int left = rootToLeafSum(root.left);

		// calculate maximum node-to-leaf sum for right child
		int right = rootToLeafSum(root.right);

		// consider maximum sum child
		return (left > right ? left : right) + root.data;
	}

	// Function to print maximum sum root-to-leaf path in a given binary tree
	public static void findMaxSumPath(Node root)
	{
		int sum = rootToLeafSum(root);
		System.out.println("Maximum sum is " + sum);
		System.out.println("Maximum sum path is: ");

		printPath(root, sum);
	}

	// main function
	public static void main(String[] args)
	{
		Node root = null;
	    /* Construct below tree
		          1
		        /   \
		       /     \
		      2       3
		     / \     / \
		    8   4   5   6
		       /   / \   \
		     10   7   9   5


		root = new Node(1);
                root.left = new Node(2);
                root.right = new Node(3);
                root.left.left = new Node(8);
                root.left.right = new Node(4);
                root.right.left = new Node(5);
                root.right.right = new Node(6);
                root.left.right.left = new Node(10);
                root.right.left.left = new Node(7);
                root.right.left.right = new Node(9);
                root.right.right.right  = new Node(5);

                findMaxSumPath(root);
                }
                }
 */
