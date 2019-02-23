package com.kv.trees;

/**
 * 
 * @author karanverma Given a Binary Tree, find the maximum sum path from a leaf to root
 *
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
    int max = Integer.MIN_VALUE;

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
        if (node == target_leaf || printPath(node.left, target_leaf) || printPath(node.right, target_leaf)) {
            System.out.print(node.data + " ");
            return true;
        }
        return false;
    }

}
