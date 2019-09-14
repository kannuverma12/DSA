package com.kv.trees;

/**
 * 
 * @author karanverma
 *
 * Get Mirror image of binary tree
 */
public class MirrorOfBinaryTree {
	//https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/

    static TreeNode root;
    
	public static void main(String[] args) {
	    MirrorOfBinaryTree tree = new MirrorOfBinaryTree(); 
        tree.root = new TreeNode(1); 
        tree.root.left = new TreeNode(2); 
        tree.root.right = new TreeNode(3); 
        tree.root.left.left = new TreeNode(4); 
        tree.root.left.right = new TreeNode(5); 
  
        /* print inorder traversal of the input tree */
        System.out.println("Inorder traversal of input tree is :"); 
        tree.inOrder(root); 
        System.out.println(""); 
  
        /* convert tree to its mirror */
        tree.mirror(root); 
  
        /* print inorder traversal of the minor tree */
        System.out.println("Inorder traversal of binary tree is : "); 
        tree.inOrder(root); 

	}
	
	TreeNode mirror(TreeNode node){
	    if(node == null)
	        return node;
	    TreeNode left = mirror(node.left);
	    TreeNode right = mirror(node.right);
	    
	    node.right = left;
	    node.left = right;
	    
	    return node;
	}

	void inOrder(TreeNode node) {
	    if(node == null)
	        return;
	    
	        inOrder(node.left);
	        System.out.println(node.data + " ");
	        inOrder(node.right);
	}

}
