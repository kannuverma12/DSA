package com.kv.LinkedList;

public class PrintReverseOfLinkedList {

    Node head;

    public static void main(String[] args) {

        // Let us create linked list 1->2->3->4
        PrintReverseOfLinkedList llist = new PrintReverseOfLinkedList();
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        llist.printReverse(llist.head);
    }
    
    /*
     * Traverse recursively till the end of the linked list. While coming back,
     * start printing the elements.
     */
    private void printReverse(Node head) {
        if (head == null)
            return;
        printReverse(head.next);
        System.out.println(head.data + " ");
    }

    /* Inserts a new Node at front of the list. */
    public void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

}
