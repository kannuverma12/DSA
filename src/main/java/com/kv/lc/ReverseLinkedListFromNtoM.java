package com.kv.lc;

import com.kv.lc.ParitionLinkedList.ListNode;

/**
 * 
 * @author karanverma
 *
 *  Reverse a linked list from position m to n. Do it in one-pass.
 *  Note: 1 ≤ m ≤ n ≤ length of list.
 *  Example:
 *  Input: 1->2->3->4->5->NULL, m = 2, n = 4
 *  Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListFromNtoM {
    ListNode head;

    public static void main(String[] args) {

        ReverseLinkedListFromNtoM l2 = new ReverseLinkedListFromNtoM();
        l2.push(2);
        l2.push(5);
        l2.push(2);
        l2.push(3);
        l2.push(4);
        l2.push(1);
        System.out.println("Original list");
        l2.printList(l2.head);
        
        System.out.println("Partitioned List");
        ListNode lpartition = reverseBetween(l2.head, 2, 4);
        l2.printList(lpartition);
        
    }
    
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)
            return head;

        ListNode prev = null;// track (m-1)th node
        ListNode first = new ListNode(0);// first's next points to mth
        ListNode second = new ListNode(0);// second's next points to (n+1)th

        int i = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            if (i == m - 1) {
                prev = p;
            }

            if (i == m) {
                first.next = p;
            }

            if (i == n) {
                second.next = p.next;
                p.next = null;
            }

            p = p.next;
        }
        if (first.next == null)
            return head;

        // reverse list [m, n]
        ListNode p1 = first.next;
        ListNode p2 = p1.next;
        p1.next = second.next;

        while (p1 != null && p2 != null) {
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

        // connect to previous part
        if (prev != null)
            prev.next = p1;
        else
            return p1;

        return head;
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
