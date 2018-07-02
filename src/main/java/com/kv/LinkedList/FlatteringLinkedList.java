package com.kv.LinkedList;

/*
 * 	Given a linked list where every node represents a linked list and contains two pointers of its type:
	(i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
	(ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
	All linked lists are sorted. See the following example
	
	Write a function flatten() to flatten the lists into a single linked list. The flattened linked list should 
	also be sorted. For example, for the above input list, output list should be 
	5->7->8->10->19->20->22->28->30->35->40->45->50.
 */

/*
 * 	The idea is to use Merge() process of merge sort for linked lists. We use merge() to merge lists one by one. 
 * 	We recursively merge() the current list with already flattened list.
	The down pointer is used to link nodes of the flattened list.
 */
public class FlatteringLinkedList {
	FNode head;

	public static void main(String[] args) {
		FlatteringLinkedList L = new FlatteringLinkedList();

		 /* Let us create the following linked list
	        5 -> 10 -> 19 -> 28
	        |    |     |     |
	        V    V     V     V
	        7    20    22    35
	        |          |     |
	        V          V     V
	        8          50    40
	        |                |
	        V                V
	        30     
	        */

		L.head = L.push(L.head, 30);
		L.head = L.push(L.head, 8);
		L.head = L.push(L.head, 7);
		L.head = L.push(L.head, 5);

		L.head.right = L.push(L.head.right, 20);
		L.head.right = L.push(L.head.right, 10);

		L.head.right.right = L.push(L.head.right.right, 50);
		L.head.right.right = L.push(L.head.right.right, 22);
		L.head.right.right = L.push(L.head.right.right, 19);

		L.head.right.right.right = L.push(L.head.right.right.right, 45);
		L.head.right.right.right = L.push(L.head.right.right.right, 40);
		L.head.right.right.right = L.push(L.head.right.right.right, 35);
		L.head.right.right.right = L.push(L.head.right.right.right, 20);

		// flatten the list
		L.head = L.flatten(L.head);

		L.printList();

	}

	FNode flatten(FNode root) {
		if (root == null || root.right == null)
			return root;

		// recur for list on right
		root.right = flatten(root.right);

		// now merge
		root = merge(root, root.right);

		// return the root it will be in turn merged with its left
		return root;
	}

	// An utility function to merge two sorted linked lists
	FNode merge(FNode a, FNode b) {
		// if first linked list is empty then second is the answer
		if (a == null)
			return b;

		// if second linked list is empty then first is the result
		if (b == null)
			return a;

		// compare the data members of the two linked lists and put the larger one in
		// the result
		FNode result;

		if (a.data < b.data) {
			result = a;
			result.down = merge(a.down, b);
		} else {
			result = b;
			result.down = merge(a, b.down);
		}

		return result;
	}

	class FNode {
		int data;
		FNode right, down;

		FNode(int data) {
			this.data = data;
			right = null;
			down = null;
		}
	}

	FNode push(FNode head_ref, int data) {
		/* 1 & 2: Allocate the Node & Put in the data */
		FNode new_node = new FNode(data);

		/* 3. Make next of new Node as head */
		new_node.down = head_ref;

		/* 4. Move the head to point to new Node */
		head_ref = new_node;

		/* 5. return to link it back */
		return head_ref;
	}

	void printList() {
		FNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.down;
		}
		System.out.println();
	}

}
