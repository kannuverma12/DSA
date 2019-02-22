package com.kv.trees;

/**
 * 
 * @author karanverma
 * 
 * Java program to print left view of binary tree
 * Class containing left and right child of current node and key value
 * */
class NodeBT {
    int data;
    NodeBT left, right;

    public NodeBT(int item) {
        data = item;
        left = right = null;
    }
}

/* Class to print the left view */
class BinaryTree {
    
    NodeBT root;
    static int max_level = 0;
    
    /* testing for example nodes */
    public static void main(String args[]) {
        /* creating a binary tree and entering the nodes */
        BinaryTree tree = new BinaryTree();
        tree.root = new NodeBT(12);
        tree.root.left = new NodeBT(10);
        tree.root.right = new NodeBT(30);
        tree.root.right.left = new NodeBT(25);
        tree.root.right.right = new NodeBT(40);

        tree.leftView();
    }

    // recursive function to print left view
    void leftViewUtil(NodeBT node, int level) {
        // Base Case
        if (node == null)
            return;

        // If this is the first node of its level
        if (max_level < level) {
            System.out.print(" " + node.data);
            max_level = level;
        }

        // Recur for left and right subtrees
        leftViewUtil(node.left, level + 1);
        leftViewUtil(node.right, level + 1);
    }

    // A wrapper over leftViewUtil()
    void leftView() {
        leftViewUtil(root, 1);
    }

    
}