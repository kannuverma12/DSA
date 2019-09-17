package com.kv.lc;

public class L72_ReverseLinkedListInKGroup {
    static ListNode head;

    public static void main(String[] args) {

        L72_ReverseLinkedListInKGroup llist = new L72_ReverseLinkedListInKGroup();

        /* Constructed Linked List is 1->2->3->4->5->6->
           7->8->8->9->null */
        llist.push(9);
        llist.push(8);
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.println("Given Linked List");
        llist.printList();

        //llist.head = llist.reverse(llist.head, 4);

        llist.head = llist.reverseKGroup(llist.head, 4);

        System.out.println("Reversed list");
        llist.printList();

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode prev = fake;
        int i = 0;

        ListNode p = head;
        while (p != null) {
            i++;
            if (i % k == 0) {
                prev = reverse(prev, p.next);
                p = prev.next;
            } else {
                p = p.next;
            }
        }

        return fake.next;
    }

    public ListNode reverse(ListNode prev, ListNode next){
        ListNode last = prev.next;
        ListNode curr = last.next;

        while(curr != next){
            last.next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = last.next;
        }

        return last;
    }


    // Method 2
    ListNode reverse(ListNode head, int k)
    {
        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;

        int count = 0;

        /* Reverse first k nodes of linked list */
        while (count < k && current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

       /* next is now a pointer to (k+1)th node
          Recursively call for the list starting from current.
          And make rest of the list as next of first node */
        if (next != null)
            head.next = reverse(next, k);

        // prev is now head of input list
        return prev;
    }


    static public class ListNode {
        int                                     val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void push(int new_data)
    {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head;
        head = new_node;
    }

    void printList()
    {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
        System.out.println();
    }


}
