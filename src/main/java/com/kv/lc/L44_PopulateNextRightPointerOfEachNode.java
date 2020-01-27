package com.kv.lc;

import java.util.LinkedList;

/**
 * 
 * @author karanverma
 *
 *  You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. 
 *  The binary tree has the following definition:
 *  Populate each next pointer to point to its next right node. If there is no next right node,
 *   the next pointer should be set to NULL.
 *   
 *   Initially, all next pointers are set to NULL
 *   
 *   You may only use constant extra space.
 *   Recursive approach is fine, implicit stack space does not count as extra space for this problem.

 */
public class L44_PopulateNextRightPointerOfEachNode {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public void connect(Node root) {
        if (root == null)
            return;

        LinkedList<Node> nodeQueue = new LinkedList<>();
        LinkedList<Integer> depthQueue = new LinkedList<>();

        if (root != null) {
            nodeQueue.offer(root);
            depthQueue.offer(1);
        }

        while (!nodeQueue.isEmpty()) {
            Node topNode = nodeQueue.poll();
            int depth = depthQueue.poll();

            if (depthQueue.isEmpty()) {
                topNode.next = null;
            } else if (depthQueue.peek() > depth) {
                topNode.next = null;
            } else {
                topNode.next = nodeQueue.peek();
            }

            if (topNode.left != null) {
                nodeQueue.offer(topNode.left);
                depthQueue.offer(depth + 1);
            }

            if (topNode.right != null) {
                nodeQueue.offer(topNode.right);
                depthQueue.offer(depth + 1);
            }
        }
    }

    class Node {
        public int  val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
