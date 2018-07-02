package com.kv.LinkedList;

public class PrintReverseOfLinkedList {
	
	Node head;
	
	
	/*
	 * 
	 * Traverse recursively till the end of the linked list. While coming back, start printing the
elements.
	 */
	private void printReverse(Node head) {
		if(head == null)
			return;
		printReverse(head.next);
		System.out.println(head.data+" ");
	}
	
	/* Inserts a new Node at front of the list. */
    public void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);
 
        /* 3. Make next of new Node as head */
        new_node.next = head;
 
        /* 4. Move the head to point to new Node */
        head = new_node;
    }

	public static void main(String[] args) {
		
		// Let us create linked list 1->2->3->4
		PrintReverseOfLinkedList llist = new PrintReverseOfLinkedList();
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);
 
        llist.printReverse(llist.head);
	}

	

}
