package com.kv.LinkedList;

/**
 * 
 * @author karanverma
 *
 *  Find if a linked list is even or odd
 */
public class EvenOrOddLinkedList {
	
	Node head;

	public static void main(String[] args) {
		EvenOrOddLinkedList llist = new EvenOrOddLinkedList();
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);
        llist.push(6);
 
        llist.checkEvenOrOdd(llist.head);
	}

	
    private void checkEvenOrOdd(Node head) {
        while (head != null && head.next != null) {
            head = head.next.next;
        }
        if (head == null) {
            System.out.println("Even Linked List");
        } else {
            System.out.println("Odd Linked List");
        }
    }

    /* Inserts a new Node at front of the list. */
    public void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }
}
