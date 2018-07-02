package com.kv.LinkedList;

public class ImplementStackUsingLinkedList {
	static Node top = null;
	
	public static void main(String... strings) {
		push(10);
		push(11);
		push(12);
		push(13);
		
		pop();
		display();
	}

	
	/*
	 * 
	 * Step 1: Check whether stack is Empty (top == NULL).
		Step 2: If it is Empty, then display 'Stack is Empty!!!' and terminate the function.
		Step 3: If it is Not Empty, then define a Node pointer 'temp' and initialize with top.
		Step 4: Display 'temp → data --->' and move it to the next node. Repeat the same until temp reaches to the first node in the stack (temp → next != NULL).
		Step 4: Finally! Display 'temp → data ---> NULL'.
	 */
	private static void display() {
		System.out.println("Displaying:");
		if(top == null) {
			System.out.println("Stack Empty!!!");
			
		}
		Node temp = top;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
		
	}

	
	
	/*
	 * Step 1: Create a newNode with given value.
		Step 2: Check whether stack is Empty (top == NULL)
		Step 3: If it is Empty, then set newNode → next = NULL.
		Step 4: If it is Not Empty, then set newNode → next = top.
		Step 5: Finally, set top = newNode.
	 */
	private static void push(int i) {
		if(top == null) {
			top = new Node(i);	
		}else {
			Node newNode = new Node(i);
			newNode.next = top;
			top = newNode;
		}
		System.out.println("Item Pushed!");
	}

	
	/*
	 * Step 1: Check whether stack is Empty (top == NULL).
		Step 2: If it is Empty, then display "Stack is Empty!!! Deletion is not possible!!!" and terminate the function
		Step 3: If it is Not Empty, then define a Node pointer 'temp' and set it to 'top'.
		Step 4: Then set 'top = top → next'.
		Step 7: Finally, delete 'temp' (free(temp)).
	 */
	private static void pop() {
		if(top == null) {
			System.out.println("Stack is Empty");
		}else {
			Node temp = top.next;
			top = temp;
			temp = null;
		}
		System.out.println("Item Poped!");
		
	}

}
