package com.kv.LinkedList;

public class LengthOfCircularLinkedList {
	
	static Node head;
	
	static public void push(int new_data)
    {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

	public static void main(String[] args) {
		LengthOfCircularLinkedList llist = new LengthOfCircularLinkedList();
        
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(10);
        llist.push(20);
         
        /*Create loop for testing */
        llist.head.next.next.next.next = llist.head;
 
        System.out.println("Length = "+findLength(head));
        
	}

	private static int findLength(Node node) {
		Node current = head;
		int count = 0;
		if(node == null)
			return count;
		
		while(current != node) {
			current = current.next;
			count++;
		}
		return count;
	}

}
