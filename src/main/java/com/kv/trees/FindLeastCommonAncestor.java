package com.kv.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author karanverma
 *  Java program to find Least Common Ancestor in a Binary Tree
 */
public class FindLeastCommonAncestor {
	
	
	/*
	 * The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest from the root. 
	 * Computation of lowest common ancestors may be useful, for instance, as part of a procedure for determining 
	 * the distance between pairs of nodes in a tree: the distance from n1 to n2 can be computed as the distance from 
	 * the root to n1, plus the distance from the root to n2, minus twice the distance from the root to their lowest 
	 * common ancestor.
	 */
	
	static Node root;
    private List<Integer> path1 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();

	public static void main(String[] args) {
		
		System.out.println("*********** Method 1 ************");
		FindLeastCommonAncestor tree = new FindLeastCommonAncestor();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
 
        System.out.println("LCA(4, 5): " + tree.findLCA(4,5));
        System.out.println("LCA(4, 6): " + tree.findLCA(4,6));
        System.out.println("LCA(3, 4): " + tree.findLCA(3,4));
        System.out.println("LCA(2, 4): " + tree.findLCA(2,4));
       /* System.out.println("LCA(4, 7): " + tree.findLCA(4,7));
        System.out.println("LCA(4, 8): " + tree.findLCA(4,8));
        System.out.println("LCA(1, 1): " + tree.findLCA(1,1)); */
        
        System.out.println("***********  Ends ************");
        System.out.println();
        System.out.println("*********** Method 2 ************");
        
        FindLeastCommonAncestor tree2 = new FindLeastCommonAncestor();
        tree2.root = new Node(1);
        tree2.root.left = new Node(2);
        tree2.root.right = new Node(3);
        tree2.root.left.left = new Node(4);
        tree2.root.left.right = new Node(5);
        tree2.root.right.left = new Node(6);
        tree2.root.right.right = new Node(7);
        System.out.println("LCA(4, 5) = " +
                            tree2.findLCA2(4, 5).data);
        System.out.println("LCA(4, 6) = " +
                            tree2.findLCA2(4, 6).data);
        System.out.println("LCA(3, 4) = " +
                            tree2.findLCA2(3, 4).data);
        System.out.println("LCA(2, 4) = " +
                            tree2.findLCA2(2, 4).data);
        
        System.out.println("***********  Ends ************");
        
        
        System.out.println();
        System.out.println("*********** Method 3 IDeserve ************");
        Node lca = tree.getLCA(tree.root, tree2.root.left.right, tree2.root.left.left);
        System.out.println("LCA(3, 6) = " +lca.data);
	}
	
	/*
	 * Method 1 (By Storing root to n1 and root to n2 paths):
		Following is simple O(n) algorithm to find LCA of n1 and n2.
		1) Find path from root to n1 and store it in a vector or array.
		2) Find path from root to n2 and store it in another vector or array.
		3) Traverse both paths till the values in arrays are same. Return the common element just before the mismatch.
	 */
	int findLCA(int n1, int n2) {
        path1.clear();
        path2.clear();
        return findLCAInternal(root, n1, n2);
    }
	
	private int findLCAInternal(Node root, int n1, int n2) {
		 
        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
            System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
            return -1;
        }
 
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
          //  System.out.println(path1.get(i) + " " + path2.get(i));
            if (!path1.get(i).equals(path2.get(i)))
                break;
        }
 
        return path1.get(i-1);
    }
	
	private boolean findPath(Node root, int n, List<Integer> path)
    {
        if (root == null) {
            return false;
        }
        path.add(root.data);
 
        if (root.data == n) {
            return true;
        }
        if (root.left != null && findPath(root.left, n, path)) {
            return true;
        }
        if (root.right != null && findPath(root.right, n, path)) {
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }
	
	// Method 1 ends
	
	
	/*
	 *  Method 2 (Using Single Traversal)
		The method 1 finds LCA in O(n) time, but requires three tree traversals plus extra spaces for path arrays. 
		If we assume that the keys n1 and n2 are present in Binary Tree, we can find LCA using single traversal of 
		Binary Tree and without extra storage for path arrays.
		The idea is to traverse the tree starting from root. If any of the given keys (n1 and n2) matches with root, 
		then root is LCA (assuming that both keys are present). If root doesn’t match with any of the keys, we recur 
		for left and right subtree. The node which has one key present in its left subtree and the other key present 
		in right subtree is the LCA. If both keys lie in left subtree, then left subtree has LCA also, otherwise LCA 
		lies in right subtree.
	 */
	
	Node findLCA2(int n1, int n2)
    {
        return findLCA2(root, n1, n2);
    }
	
	Node findLCA2(Node node, int n1, int n2)
    {
        if (node == null)
            return null;
 
        // If either n1 or n2 matches with root's key, report
        // the presence by returning root (Note that if a key is
        // ancestor of other, then the ancestor key becomes LCA
        if (node.data == n1 || node.data == n2)
            return node;
 
        // Look for keys in left and right subtrees
        Node left_lca = findLCA2(node.left, n1, n2);
        Node right_lca = findLCA2(node.right, n1, n2);
 
        // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (left_lca!=null && right_lca!=null)
            return node;
 
        // Otherwise check if left subtree or right subtree is LCA
        return (left_lca != null) ? left_lca : right_lca;
    }
	
	
	 Node getLCA(Node curr, Node a, Node b) {
		if(curr == null)
			return null;
		if(curr == a || curr == b)
			return curr;
		
		Node left = getLCA(curr.left, a, b);
		Node right = getLCA(curr.right, a, b);
		
		if(left != null && right != null)
			return curr;
		
		if(left == null)
			return right;
		else
			return left;
	}

}
