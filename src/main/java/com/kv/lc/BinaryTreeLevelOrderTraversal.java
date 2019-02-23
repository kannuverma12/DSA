package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author karanverma
 *
 *  Given a binary tree, return the level order traversal of its nodes' values. 
 *  (ie, from left to right, level by level).
 */
public class BinaryTreeLevelOrderTraversal {
    /*
     * It is obvious that this problem can be solve by using a queue. However, if we use one queue 
     * we can not track when each level starts. So we use two queues to track the current level 
     * and the next level
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println("Is valid treenode = " + Arrays.deepToString(levelOrder1(root).toArray()));
    }
    
    //method 1 - use this
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
     
        if(root==null){
            return result;
        }
     
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> levelQueue = new LinkedList<>();
     
        nodeQueue.offer(root);
        levelQueue.offer(1);//start from 1
        
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();
     
            List<Integer> l=null;
            if(result.size()<level){
                l = new ArrayList<>();
                result.add(l);
            }else{
                l = result.get(level-1);
            }
     
            l.add(node.val);
     
            if(node.left!=null){
                nodeQueue.offer(node.left);
                levelQueue.offer(level+1);
            }
     
            if(node.right!=null){
                nodeQueue.offer(node.right);
                levelQueue.offer(level+1);
            }
        }
     
        return result;
    }
    
    // basic algorithm is this
    private static void levelOrderTravesal(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        if(root == null)
            return;
        
        q.add(root);
        
        while(!q.isEmpty()) {
            TreeNode temp = q.poll();
            System.out.println(temp.val);
            
            if(temp.left != null)
                q.add(temp.left);
            if(temp.right != null)
                q.add(temp.right);
        }
        
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> al = new ArrayList<List<Integer>>();
        List<Integer> nodeValues = new ArrayList<Integer>();
        if(root == null)
            return al;
     
        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.add(root);
     
        while(!current.isEmpty()){
            TreeNode node = current.remove();
     
            if(node.left != null)
                next.add(node.left);
            if(node.right != null)
                next.add(node.right);
     
            nodeValues.add(node.val);
            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                al.add(nodeValues);
                nodeValues = new ArrayList();
            }
     
        }
        return al;
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
