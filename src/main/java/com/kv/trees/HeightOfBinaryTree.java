package com.kv.trees;

/**
 * 
 *  @author karanverma
 *  Program to find the height of a binary tree
 */
public class HeightOfBinaryTree {

    static NodeH root;

    public static void main(String args[]) {
        HeightOfBinaryTree tree = new HeightOfBinaryTree();
        tree.root = new NodeH(4);
        tree.root.left = new NodeH(2);
        tree.root.right = new NodeH(5);
        tree.root.left.left = new NodeH(1);
        tree.root.left.right = new NodeH(3);

        addMoreNodes(root);

        System.out.println("Height : " + tree.getHeight(root));
    }
    
    private int getHeight(NodeH node) {
        if (node == null)
            return 0;
        else
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private static void addMoreNodes(NodeH root) {
        for (int i = 0; i < 10; i++) {
            root.left = new NodeH(i + i);
            root.right = new NodeH(i + i + i);
            addMoreNodes(root.left);
            addMoreNodes(root.right);
        }
    }

    static class NodeH {
        int data;
        NodeH left, right;

        public NodeH(int item) {
            data = item;
            left = right = null;
        }
    }
}


