package com.kv.lc;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author karanverma
 *
 */
public class BinaryTreePreoderTraversal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
 
        if(root == null)
            return returnList;
 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
 
        while(!stack.empty()){
            TreeNode n = stack.pop();
            returnList.add(n.val);
 
            if(n.right != null){
                stack.push(n.right);
            }
            if(n.left != null){
                stack.push(n.left);
            }
 
        }
        return returnList;
    }
    
    static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }

}
