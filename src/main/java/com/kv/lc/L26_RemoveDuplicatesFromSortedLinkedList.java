package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct 
 *  numbers from the original list.
 *  
 *  Input: 1->2->3->3->4->4->5
 *  Output: 1->2->5
 */
public class L26_RemoveDuplicatesFromSortedLinkedList {

    ListNode head;
    
    public static void main(String[] args) {
        L26_RemoveDuplicatesFromSortedLinkedList l2 = new L26_RemoveDuplicatesFromSortedLinkedList();
        l2.push(5);
        l2.push(4);
        l2.push(4);
        l2.push(3);
        l2.push(3);
        l2.push(2);
        l2.push(1);
        
        l2.printList(l2.head);
        
        System.out.println("After removing duplicates");
        ListNode lunique = deleteDuplicates( l2.head);
        l2.printList(lunique);
    }
    
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode t = new ListNode(0);
        t.next = head;

        ListNode p = t;
        while (p.next != null && p.next.next != null) {
            if (p.next.val == p.next.next.val) {
                int dup = p.next.val;
                while (p.next != null && p.next.val == dup) {
                    p.next = p.next.next;
                }
            } else {
                p = p.next;
            }

        }

        return t.next;
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void push(int new_data) {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head;
        head = new_node;
    }

    void printList(ListNode l) {
        ListNode temp = l;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

}
