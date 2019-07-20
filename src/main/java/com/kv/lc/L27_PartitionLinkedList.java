package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a linked list and a value x, partition it such that all nodes less than x come before nodes 
 *  greater than or equal to x.
 *  You should preserve the original relative order of the nodes in each of the two partitions.
 *  
 *  Input: head = 1->4->3->2->5->2, x = 3
 *  Output: 1->2->2->4->3->5
 */
public class L27_PartitionLinkedList {

    ListNode head;
    
    public static void main(String[] args) {
        L27_PartitionLinkedList l2 = new L27_PartitionLinkedList();
        l2.push(2);
        l2.push(5);
        l2.push(2);
        l2.push(3);
        l2.push(4);
        l2.push(1);
        System.out.println("Original list");
        l2.printList(l2.head);
        
        System.out.println("Partitioned List");
        ListNode lpartition = partition(l2.head, 3);
        l2.printList(lpartition);
    }
    
    public static ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;

        ListNode fakeHead1 = new ListNode(0);
        ListNode fakeHead2 = new ListNode(0);
        fakeHead1.next = head;

        ListNode p = head;
        ListNode prev = fakeHead1;
        ListNode p2 = fakeHead2;

        while (p != null) {
            if (p.val < x) {
                p = p.next;
                prev = prev.next;
            } else {
                p2.next = p;
                prev.next = p.next;

                p = prev.next;
                p2 = p2.next;
            }
        }
        // close the list
        p2.next = null;

        prev.next = fakeHead2.next;

        return fakeHead1.next;
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
