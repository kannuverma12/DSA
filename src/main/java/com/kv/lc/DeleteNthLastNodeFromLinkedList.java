package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 */
public class DeleteNthLastNodeFromLinkedList {
    
    static ListNode head;

    public static void main(String[] args) {
        DeleteNthLastNodeFromLinkedList llist = new DeleteNthLastNodeFromLinkedList();
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(35);
        System.out.println("Before deleting");
        llist.printList();
        System.out.println();
        
        System.out.println("********* Method 1 *****");
        llist.removeNthFromEnd(head, 2);
        System.out.println("After deleting");
        llist.printList();
        System.out.println();
        
        System.out.println("********* Method 2 *****");
        llist.removeNthFromEndOneGo(head, 2);
        System.out.println("After deleting");
        llist.printList();

    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;

        // get length of list
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        // if remove first node
        int fromStart = len - n + 1;
        if (fromStart == 1)
            return head.next;

        // remove non-first node
        p = head;
        int i = 0;
        while (p != null) {
            i++;
            if (i == fromStart - 1) {
                p.next = p.next.next;
            }
            p = p.next;
        }

        return head;
    }

    public ListNode removeNthFromEndOneGo(ListNode head, int n) {
        if (head == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // if remove the first node
        if (fast == null) {
            head = head.next;
            return head;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
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

    void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

}
