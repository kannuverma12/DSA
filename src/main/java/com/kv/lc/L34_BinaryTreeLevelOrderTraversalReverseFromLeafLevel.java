package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a binary tree, return the level order traversal of its nodes' values. 
 *  (ie, from left to right, level by level).
 *  
 *  Return the list in reverse manner from lowest level
 */
public class L34_BinaryTreeLevelOrderTraversalReverseFromLeafLevel {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println("Is valid treenode = " + Arrays.deepToString(levelOrderBottom(root).toArray()));
    }
    
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new  ArrayList<>();
     
        if(root == null){
            return result;
        }
     
        LinkedList<TreeNode> current = new LinkedList<>();
        LinkedList<TreeNode> next = new LinkedList<>();
        current.offer(root);
     
        List<Integer> numberList = new ArrayList<>();
     
        // need to track when each level starts
        while(!current.isEmpty()){
            TreeNode head = current.poll();
     
            numberList.add(head.val);
     
            if(head.left != null){
                next.offer(head.left);
            }
            if(head.right!= null){
                next.offer(head.right);
            }
     
            if(current.isEmpty()){
                current = next;
                next = new LinkedList<>();
                result.add(numberList);
                numberList = new ArrayList<>();
            }
        }
     
        //return Collections.reverse(result);
        List<List<Integer>> reversedResult = new  ArrayList<>();
        for(int i=result.size()-1; i>=0; i--){
            reversedResult.add(result.get(i));
        }
     
        return reversedResult;
    }
    
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
