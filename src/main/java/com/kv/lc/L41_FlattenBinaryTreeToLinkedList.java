package com.kv.lc;

import java.util.Stack;

/**
 * 
 * @author karanverma
 *
 *  Given a binary tree, flatten it to a linked list in-place.
 */
public class L41_FlattenBinaryTreeToLinkedList {
    /*
     * Go down through the left, when right is not null, push right to stack.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        flatten(root);
        System.out.println("Is valid treenode = "  );
    }

    public static void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
 
        while(p != null || !stack.empty()){
 
            if(p.right != null){
                stack.push(p.right);
            }
 
            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                TreeNode temp = stack.pop();
                p.right=temp;
            }
 
            p = p.right;
        }
        
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    

}
