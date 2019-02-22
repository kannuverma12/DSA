package com.kv.trees;

/**
 * 
 * @author karanverma
 *
 * Get Mirror image of binary tree
 */
public class MirrorOfBinaryTree {
	//https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/

    static Node root;
    
	public static void main(String[] args) {
	    MirrorOfBinaryTree tree = new MirrorOfBinaryTree(); 
        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
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
	
	Node mirror(Node node){
	    if(node == null)
	        return node;
	    Node left = mirror(node.left);
	    Node right = mirror(node.right);
	    
	    node.right = left;
	    node.left = right;
	    
	    return node;
	}
	
	void inOrder(Node node) {
	    if(node == null)
	        return;
	    
	        inOrder(node.left);
	        System.out.println(node.data + " ");
	        inOrder(node.right);
	}

}
