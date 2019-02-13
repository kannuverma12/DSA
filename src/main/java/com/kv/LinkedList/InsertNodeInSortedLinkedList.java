package com.kv.LinkedList;

public class InsertNodeInSortedLinkedList {
	
	Node head;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InsertNodeInSortedLinkedList llist = new InsertNodeInSortedLinkedList();
        Node new_node;
        new_node = llist.newNode(5);
        llist.insertToSortedLinkedList(new_node);
        new_node = llist.newNode(10);
        llist.insertToSortedLinkedList(new_node);
        new_node = llist.newNode(7);
        llist.insertToSortedLinkedList(new_node);
        new_node = llist.newNode(3);
        llist.insertToSortedLinkedList(new_node);
        new_node = llist.newNode(1);
        llist.insertToSortedLinkedList(new_node);
        new_node = llist.newNode(9);
        llist.insertToSortedLinkedList(new_node);
        System.out.println("Created Linked List");
        llist.printList();

		
		
	}
	
	
	/*
	 *  1) If Linked list is empty then make the node as
           head and return it.
        2) If value of the node to be inserted is smaller 
           than value of head node, then insert the node 
           at start and make it head.
        3) In a loop, find the appropriate node after 
           which the input node (let 9) is to be inserted. 
           To find the appropriate node start from head, 
           keep moving until you reach a node GN (10 in
           the below diagram) who's value is greater than 
           the input node. The node just before GN is the
           appropriate  node (7).
        4) Insert the node (9) after the appropriate node
           (7) found in step 3
	 */

	private  void insertToSortedLinkedList(Node newnode) {
		Node current;
		
		if(head == null || head.data >= newnode.data) {
			newnode.next = head;
			head = newnode;
		}else {
			current = head;
			
			while(current.next != null &&
					current.next.data < newnode.data) {
				current = current.next;
			}
			newnode.next = current.next;
			current.next = newnode;
		}
		
	}
	
	 /* Function to create a node */
    Node newNode(int data)
    {
       Node x = new Node(data);
       return x;
    }
    
    /* Function to print linked list */
    void printList()
    {
        Node temp = head;
        while (temp != null)
        {
           System.out.print(temp.data+" ");
           temp = temp.next;
        }
    }

}
