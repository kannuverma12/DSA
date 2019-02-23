package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *  To represent a cycle in the given linked list, we use an integer pos which represents 
 *  the position (0-indexed) in the linked list where tail connects to. If pos is -1, then 
 *  there is no cycle in the linked list.
 *  Note: Do not modify the linked list.
 *  
 *  Input: head = [3,2,0,-4], pos = 1
 *  Output: tail connects to node index 1
 *  Explanation: There is a cycle in the linked list, where tail connects to the second node.

 */
public class LinkedListCycle2 {

    ListNode head;
    public static void main(String[] args) {
        LinkedListCycle2 l2 = new LinkedListCycle2();
        l2.push(9);
        l2.push(7);
        l2.push(5);
        l2.push(3);
        l2.push(7);
        System.out.println("l2");
        l2.head.next = l2.head.next.next.next;
        l2.printList(l2.head);
        
        System.out.println("Merged l1 and l2");
        ListNode lcycle = detectCycle(l2.head);
        System.out.println(lcycle.val);
    }
    
    public static ListNode detectCycle(ListNode head) {
        // Find the collision point if the list has a cycle
        if (head == null) return null;
         
        ListNode slow = head;
        ListNode fast = head;
         
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
             
            if (slow == fast) break;
        }
         
        // check if the list has no cycle
        if (fast == null || fast.next == null) 
            return null;
         
        // find the entrance of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }
   
   public void push(int new_data)
   {
       ListNode new_node = new ListNode(new_data);
       new_node.next = head;
       head = new_node;
   }
   
   void printList(ListNode l)
   {
       ListNode temp = l;
       while (temp != null)
       {
          System.out.print(temp.val+" ");
          temp = temp.next;
       }
       System.out.println();
   }

}
