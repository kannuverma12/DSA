package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Follow up for problem "Populating Next Right Pointers in Each Node".
 *  What if the given tree could be any binary tree? Would your previous solution still work?
 *  
 *  Similar to Populating Next Right Pointers in Each Node, we have 4 pointers at 2 levels of the tree.
 */
public class PopulateNextRightPointerOfEachNode2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public void connect(Node root) {
        if(root == null) 
            return;
     
        Node lastHead = root;//prevous level's head 
        Node lastCurrent = null;//previous level's pointer
        Node currentHead = null;//currnet level's head 
        Node current = null;//current level's pointer
     
        while(lastHead!=null){
            lastCurrent = lastHead;
     
            while(lastCurrent!=null){
                //left child is not null
                if(lastCurrent.left!=null)    {
                    if(currentHead == null){
                        currentHead = lastCurrent.left;
                        current = lastCurrent.left;
                    }else{
                        current.next = lastCurrent.left;
                        current = current.next;
                    }
                }
     
                //right child is not null
                if(lastCurrent.right!=null){
                    if(currentHead == null){
                        currentHead = lastCurrent.right;
                        current = lastCurrent.right;
                    }else{
                        current.next = lastCurrent.right;
                        current = current.next;
                    }
                }
     
                lastCurrent = lastCurrent.next;
            }
     
            //update last head
            lastHead = currentHead;
            currentHead = null;
        }
    }
    
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

}
