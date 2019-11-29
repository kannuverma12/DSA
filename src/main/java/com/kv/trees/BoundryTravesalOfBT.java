package com.kv.trees;

/**
 * 
 * @author karanverma
 * 
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root. 
 * For example, boundary traversal of the following tree is “20 8 4 10 14 25 22”
 * 
 * We break the problem in 3 parts:
 *	1. Print the left boundary in top-down manner.
 *	2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
 *	…..2.1 Print all leaf nodes of left sub-tree from left to right.
 *	…..2.2 Print all leaf nodes of right subtree from left to right.
 *	3. Print the right boundary in bottom-up manner.
 */
public class BoundryTravesalOfBT {
    TreeNode root;

    public static void main(String[] args) {
        BoundryTravesalOfBT tree = new BoundryTravesalOfBT();
        tree.root = new TreeNode(20);
        tree.root.left = new TreeNode(8);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(12);
        tree.root.left.right.left = new TreeNode(10);
        tree.root.left.right.right = new TreeNode(14);
        tree.root.right = new TreeNode(22);
        tree.root.right.right = new TreeNode(25);

        tree.printBoundaryAntiClockWise(tree.root);
        // tree.printBoundaryClockWise(tree.root); //not working fine...need to be modified

    }

    private void printBoundaryAntiClockWise(TreeNode node) {
        if (node != null) {
            System.out.println(node.data + " ");

            printBoundryLeft(node.left);

            printLeaves(node.left);
            printLeaves(node.right);

            printBoundryRight(node.right);
        }
    }

    // A function to print all left boundry nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    private void printBoundryLeft(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                // to ensure top down order, print the node before calling itself for left subtree
                System.out.println(node.data + " BL L");
                printBoundryLeft(node.left);
            } else if (node.right != null) {
                System.out.print(node.data + " BL R");
                printBoundryLeft(node.right);
            }
            // do nothing if it is a leaf node, this way we avoid duplicates in output
        }
    }

    // A function to print all right boundry nodes, except a leaf node
    // Print the nodes in BOTTOM UP manner
    private void printBoundryRight(TreeNode node) {

        if (node != null) {
            if (node.right != null) {
                // to ensure bottom up order, first call for right subtree, then print this node
                printBoundryRight(node.right);
                System.out.println(node.data + " BR R");
            } else if (node.left != null) {
                printBoundryRight(node.left);
                System.out.println(node.data + " BR L");
            }
            // do nothing if it is a leaf node, this way we avoid duplicates in output
        }
    }

    private void printLeaves(TreeNode node) {
        if (node != null) {
            printLeaves(node.left);

            if (node.left == null && node.right == null)
                System.out.println(node.data + " LEAF");

            printLeaves(node.right);
        }
    }

    private void printBoundaryClockWise(TreeNode node) {
        if (node != null) {
            System.out.println(node.data + " ");

            printBoundryRight(node.right);
            printLeaves(node.right);
            printLeaves(node.left);

            printBoundryLeft(node.left);
        }
    }

}
