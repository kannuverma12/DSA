package com.kv.fb;

import java.util.ArrayList;

/**
 * 
 * @author karanverma
 * 
 * Given an arbitrary tree remove nodes which have data value 0. 
 * As it stats arbitrary tree, I assumed n-ary tree.
 *
 */
public class Remove0ValueNodeFromTree {

    public static void main(String[] args) {
        Node root = new Node(3, "Root");

        Node A1 = new Node(2, "A1");
        Node A2 = new Node(0, "A2");
        Node A3 = new Node(2, "A3");
        Node A4 = new Node(0, "A4");

        Node A11 = new Node(1, "A11");
        Node A12 = new Node(0, "A12");
        Node A13 = new Node(1, "A13");

        A1.childNodes.add(A11);
        A1.childNodes.add(A12);
        A1.childNodes.add(A13);

        Node A21 = new Node(0, "A21");
        Node A22 = new Node(0, "A22");
        A2.childNodes.add(A21);
        A2.childNodes.add(A22);

        Node A31 = new Node(1, "A31");
        Node A32 = new Node(0, "A32");
        Node A33 = new Node(0, "A33");

        A3.childNodes.add(A31);
        A3.childNodes.add(A32);
        A3.childNodes.add(A33);

        Node A41 = new Node(0, "A41");
        Node A42 = new Node(1, "A42");

        A4.childNodes.add(A41);
        A4.childNodes.add(A42);

        root.childNodes.add(A1);
        root.childNodes.add(A2);
        root.childNodes.add(A3);
        root.childNodes.add(A4);

        Node finalNode = removeZeroNodes(root);
        printNodeValues(finalNode);
    }
    
    public static Node removeZeroNodes(Node node) {

        if (node.childNodes.isEmpty()) {
            if (node.value == 0)
                return null;
            else
                return node;
        } else {

            ArrayList<Node> temp = new ArrayList<Node>();

            for (Node childNode : node.childNodes) {
                Node resultNode = removeZeroNodes(childNode);
                if (resultNode != null) {
                    temp.add(resultNode);
                }
            }
            node.childNodes = temp;
            if (node.value == 0 && node.childNodes.isEmpty()) {
                return null;
            } else if (node.value == 0 && !node.childNodes.isEmpty()) {
                Node tempRoot = node.childNodes.get(0);
                node.childNodes.remove(tempRoot);
                tempRoot.childNodes = node.childNodes;
                node = tempRoot;
                return node;
            } else {
                return node;
            }
        }
    }

    public static void printNodeValues(Node node) {

        System.out.println(node.name);
        if (!node.childNodes.isEmpty()) {
            for (Node childNode : node.childNodes)
                printNodeValues(childNode);

        }
    }

    public static class Node {
        int value;
        String name;
        ArrayList<Node> childNodes = new ArrayList<Node>();

        public Node(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

}
