package com.kv.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author karanverma
 *
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> returnList = new ArrayList<>();
 
        if(root == null)
            return returnList;
 
        Stack<TreeNode> stack = new Stack<>();
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
