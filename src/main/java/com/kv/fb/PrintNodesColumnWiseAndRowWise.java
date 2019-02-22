package com.kv.fb;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;


/**
 * 
 * @author karanverma
 *
 *  "Given the root of a binary tree, print the nodes column wise and row wise.
 *  ..............6
 *  ............/....\
 *  ...........9......4
 *  ........../..\......\
 *  .........5....1.....3
 *  ..........\........./
 *  ...........0.......7
 *  
 *  The answer would be 5 9 0 6 1 4 7 3.
 */
public class PrintNodesColumnWiseAndRowWise {

    public static void main(String[] args) {

        Node root = Node.root(6);                //           6
        Node n9 = root.left(9);                  //       9         4
        n9.left(5).right(0);                     //   5       1          3
        n9.right(1);                             //       0         7
        root.right(4).right(3).left(7);

        List<Node> fringe = new LinkedList<>();

        traverse(fringe, root);

        Collections.sort(fringe);
        System.out.println(fringe);

        print(root);

        System.out.println("Method 3 :");
        printVerticalOrder(root); // use this - horizontal distance
    }

    // method 3
    // The main function to print vertical oder of a binary tree with given root
    static void printVerticalOrder(Node root) {
        // Create a map and store vertical oder in map using function getVerticalOrder()
        TreeMap<Integer, Vector<Integer>> m = new TreeMap<>();
        int hd = 0;
        getVerticalOrder(root, hd, m);

        // Traverse the map and print nodes at every horizontal distance (hd)
        for (Entry<Integer, Vector<Integer>> entry : m.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    static void getVerticalOrder(Node root, int hd, TreeMap<Integer, Vector<Integer>> m) {
        if (root == null)
            return;

        // get the vector list at 'hd'
        Vector<Integer> get = m.get(hd);

        // Store current node in map 'm'
        if (get == null) {
            get = new Vector<>();
            get.add(root.val);
        } else
            get.add(root.val);

        m.put(hd, get);

        // Store nodes in left subtree
        getVerticalOrder(root.left, hd - 1, m);

        // Store nodes in right subtree
        getVerticalOrder(root.right, hd + 1, m);
    }

    private static void traverse(List<Node> fringe, Node node) {
        fringe.add(node);
        if (node.left != null)
            traverse(fringe, node.left);
        if (node.right != null)
            traverse(fringe, node.right);
    }

    static class Node implements Comparable<Node> {
        Node left;
        Node right;
        int val;

        int displacement;
        int depth;
        Node parent;

        static Node root(int val) {
            Node node = new Node();
            node.val = val;
            return node;
        }

        Node left(int val) {
            Node node = new Node();
            node.val = val;
            node.depth = this.depth + 1;
            node.displacement = this.displacement + 1;
            node.parent = this;
            this.left = node;
            return node;
        }

        Node right(int val) {
            Node node = new Node();
            node.val = val;
            node.depth = this.depth + 1;
            node.displacement = this.displacement - 1;
            node.parent = this;
            this.right = node;
            return node;
        }

        @Override
        public int compareTo(Node other) {
            int d = Integer.compare(other.displacement, this.displacement);
            if (d != 0)
                return d;
            return Integer.compare(this.depth, other.depth);
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    static void print(Node n) {
        Deque<Node> stack = new ArrayDeque<>();
        while (n != null) {
            stack.push(n);
            n = n.left;
        }
        doStack(stack);
    }

    static void doStack(Deque<Node> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Node prev = stack.pop();
        System.out.println(prev.val);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.println(n.val);
            print(prev.right);
            prev = n;
        }
        print(prev.right);
    }

}
