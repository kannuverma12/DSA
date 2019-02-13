package com.kv.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karanverma
 * 
 *         Given a Binary Tree, find vertical sum of the nodes that are in same
 *         vertical line. Print all sums through different vertical lines.
 */
public class VerticalSumOfBT {
    private Node root;

    public VerticalSumOfBT(Node node) {
        this.root = node;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = (new Node(3));
        root.left.left = (new Node(4));
        root.left.right = (new Node(5));
        root.right.left = (new Node(6));
        root.right.right = (new Node(7));
        VerticalSumOfBT t = new VerticalSumOfBT(root);

        System.out.println("********* Method 1 : Using HashMap ************");

        System.out.println("Following are the values of vertical sums with the positions"
                + " of the columns with respect to root ");
        t.verticalSum(root);

        System.out.println();
        System.out.println("********* Method 2 : Using Doubly Linked List ************");

        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.left = new Node(6);
        root1.right.right = new Node(7);

        System.out.println("Vertical Sums are");
        verticalSumDLL(root1);
    }

    /*
     * We need to check the Horizontal Distances from root for all nodes. If two
     * nodes have the same Horizontal Distance (HD), then they are on same vertical
     * line. The idea of HD is simple. HD for root is 0, a right edge (edge
     * connecting to right subtree) is considered as +1 horizontal distance and a
     * left edge is considered as -1 horizontal distance. For example, in the above
     * tree, HD for Node 4 is at -2, HD for Node 2 is -1, HD for 5 and 6 is 0 and HD
     * for node 7 is +2. We can do inorder traversal of the given Binary Tree. While
     * traversing the tree, we can recursively calculate HDs. We initially pass the
     * horizontal distance as 0 for root. For left subtree, we pass the Horizontal
     * Distance as Horizontal distance of root minus 1. For right subtree, we pass
     * the Horizontal Distance as Horizontal Distance of root plus 1.
     */

    private void verticalSum(Node root) {
        if (root == null)
            return;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        // Calls the VerticalSumUtil() to store the vertical sum values in hM
        verticalSumUtil(root, 0, map);

        // Prints the values stored by VerticalSumUtil()
        if (map != null) {
            System.out.println(map.entrySet());
        }
    }

    // Traverses the tree in Inoorder form and builds
    // a hashMap hM that contains the vertical sum
    private void verticalSumUtil(Node root, int i, Map<Integer, Integer> map) {

        if (root == null)
            return;

        // Store the values in hM for left subtree
        verticalSumUtil(root.left, i - 1, map);

        // Update vertical sum for hD of this node
        int prevSum = (map.get(i) == null) ? 0 : map.get(i);
        map.put(i, prevSum + root.data);

        // Store the values in hM for right subtree
        verticalSumUtil(root.right, i + 1, map);
    }

    // Method 2 - Using Doubly LinkedList
    /*
     * The solution discussed here requires only n nodes of linked list where n is
     * total number of vertical lines in binary tree. Below is algorithm.
     */

    /*
     * verticalSumDLL(root) 
     * 1) Create a node of doubly linked list node with value 0. Let the node be llnode. 
     * 2) verticalSumDLL(root, llnode)
     * 
     * verticalSumDLL(tnode, llnode) 
     * 1) Add current node's data to its vertical line llnode.data = llnode.data + tnode.data; 
     * 2) Recursively process left subtree
     *   // If left child is not empty 
     *   if (tnode.left != null) { 
     *      if (llnode.prev == null) { 
     *          llnode.prev = new LLNode(0); 
     *          llnode.prev.next = llnode; 
     *      }
     *      verticalSumDLLUtil(tnode.left, llnode.prev); 
     *   } 
     *   
     * 3) Recursively process right subtree 
     *     if (tnode.right != null) { 
     *          if (llnode.next == null) { 
     *              llnode.next = new LLNode(0); 
     *              llnode.next.prev = llnode; 
     *          } 
     *          verticalSumDLLUtil(tnode.right, llnode.next); 
     *     }
     */

    // Prints vertical sum of different vertical lines in tree. This method mainly uses
    // verticalSumDLLUtil().
    static void verticalSumDLL(Node root) {
        // Create a doubly linked list node to store sum of lines going through root.
        // Vertical sum is initialized as 0.
        LLNode llnode = new LLNode(0);

        // Compute vertical sum of different lines
        verticalSumDLLUtil(root, llnode);

        // llnode refers to sum of vertical line going through root.
        // Move llnode to theleftmost line.
        while (llnode.prev != null)
            llnode = llnode.prev;

        // Prints vertical sum of all lines starting from leftmost vertical line
        while (llnode != null) {
            System.out.print(llnode.data + " ");
            llnode = llnode.next;
        }
    }

    // Constructs linked list
    static void verticalSumDLLUtil(Node tnode, LLNode llnode) {
        // Add current node's data to its vertical line
        llnode.data = llnode.data + tnode.data;

        // Recursively process left subtree
        if (tnode.left != null) {
            if (llnode.prev == null) {
                llnode.prev = new LLNode(0);
                llnode.prev.next = llnode;
            }
            verticalSumDLLUtil(tnode.left, llnode.prev);
        }

        // Process right subtree
        if (tnode.right != null) {
            if (llnode.next == null) {
                llnode.next = new LLNode(0);
                llnode.next.prev = llnode;
            }
            verticalSumDLLUtil(tnode.right, llnode.next);
        }
    }

    // Doubly Linked List Node
    static class LLNode {
        int data;
        LLNode prev, next;

        public LLNode(int d) {
            data = d;
        }
    }

}
