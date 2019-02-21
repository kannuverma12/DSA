package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a list, rotate the list to the right by k places, where k is non-negative.
 *  For example:
 *  Given 1->2->3->4->5->NULL and k = 2,
 *  return 4->5->1->2->3->NULL.
 */
public class RotateLinkedListRight {
    ListNode head;

    public static void main(String[] args) {
        RotateLinkedListRight llist = new RotateLinkedListRight();
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(35);
        System.out.println("Before rotating");
        llist.printList(llist.head);
        
        ListNode hListNode = rotateRight(llist.head, 2);
        System.out.println("After rotating");
        llist.printList(hListNode);
        
    }
    
    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null || n == 0)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            n--;
            fast = fast.next;
            if (fast == null)
                fast = head;
        }
        if (fast == null || slow == fast)
            return head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
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
