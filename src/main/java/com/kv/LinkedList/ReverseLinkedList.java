package com.kv.LinkedList;

//Java program for reversing the linked list

public class ReverseLinkedList {

	static Node head;

	static class Node {

		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	/* Function to reverse the linked list */
	
	/*
	 *     1 -> 2   |   Next            -> Current.Next   |      N   -> CN
	 *     2 -> 3   |   Current.Next    -> Prev           |      CN -> P
	 *     3 -> 4   |   Prev            -> Current        |      P -> C
	 *     4 -> 1   |   Current         -> Next           |     C -> N
	 */
	Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node next = null;
		while (current != null) {
			// TO-REVIEW
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}
	Node reverseUtil(Node curr, Node prev) {

		/* If last node mark it head */
		if (curr.next == null) {
			head = curr;

			/* Update next to prev node */
			curr.next = prev;
			return null;
		}

		/* Save curr->next node for recursive call */
		Node next1 = curr.next;

		/* and update next .. */
		curr.next = prev;

		reverseUtil(next1, curr);
		return head;
	}

	// prints content of double linked list
	void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
	}

	public static void main(String[] args) {
		ReverseLinkedList list = new ReverseLinkedList();
		list.head = new Node(85);
		list.head.next = new Node(15);
		list.head.next.next = new Node(4);
		list.head.next.next.next = new Node(20);

		System.out.println("Given Linked list");
		list.printList(head);
		head = list.reverse(head);
		System.out.println("");
		System.out.println("Reversed linked list ");
		list.printList(head);

		reverseMethodTwo();
	}

	public static void reverseMethodTwo() {
		ReverseLinkedList list = new ReverseLinkedList();
		list.head = new Node(1);
		list.head.next = new Node(2);
		list.head.next.next = new Node(3);
		list.head.next.next.next = new Node(4);
		list.head.next.next.next.next = new Node(5);
		list.head.next.next.next.next.next = new Node(6);
		list.head.next.next.next.next.next.next = new Node(7);
		list.head.next.next.next.next.next.next.next = new Node(8);
		System.out.println();
		System.out.println("In Second(Reverse) Method : Original Linked list ");
		list.printList(head);
		Node res = list.reverseUtil(head, null);
		System.out.println("");
		System.out.println("");
		System.out.println("Reversed linked list ");
		list.printList(res);
	}
}
