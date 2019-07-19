package com.kv.failed;

public class hotstar

{
}

/*
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Node{
    int data;
    Node left;
    Node right;
    public Node(int item)
    {
        data = item;
        left = right = null;
    }
}

public class Solution {
    public static void main(String args[] ) throws Exception {
        // Enter your code here. Read input from STDIN. Print output to STDOUT
Node a = new Node(4);
Node b = new Node(5);
Node c = new Node(8);
Node d = new Node(6);
Node e = new Node(9);
Node f = new Node(10);
Node g = new Node(3);

        a.left = b;
                a.right = c;
                b.left = d;
                b.right = e;
                c.left = f;
                c.right = g;

                a = reverseNode(a, 10);
                printTree(a);
                }

public static void printTree(Node a){
        if(a==null)
        return;

        // Queue q =
        System.out.println(a.data);
        printTree(a.left);
        printTree(a.right);
        }

public static Node reverseNode(Node root, int x){
        List<Integer> l = new ArrayList();


        boolean hasPath = findPath(root, l, x);
        System.out.println("l "+Arrays.toString(l.toArray()));
        if(hasPath){
        reverseNodePosition(root, l);
        }
        return root;
        }

static boolean findPath(Node root, List<Integer> l, int x){

        if (root == null)
        return false;
        l.add(root.data);
        if(root.data == x)
        return true;

        if( findPath(root.left,l, x) || findPath(root.right,l, x))
        return true;

        l.remove(l.size() -1);
        return false;
        }

static void reverseNodePosition(Node root, List<Integer> l){

        int i = l.size() - 1;
        if(root == null)
        return;

        while(l.size() > 0){


        if (l.contains(root.data)){
        root.data = l.get(i);
        l.remove(i);
        i--;
        }
        System.out.println("list size = "+l.size());
        if( root.left != null)
        reverseNodePosition(root.left, l);

        if(root.right != null)
        reverseNodePosition(root.right, l);

        }

        }
        }


        */
