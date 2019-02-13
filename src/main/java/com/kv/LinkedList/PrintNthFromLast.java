package com.kv.LinkedList;


public class PrintNthFromLast {
	Node head;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PrintNthFromLast llist = new PrintNthFromLast();
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(35);
 
        llist.printNthFromLast(2);
        llist.printNthFromLast(llist.head,2);

        
        llist.printNthFromLastUsing2Pointers(2);
        
        llist.removeNthFromLast(llist.head,2);
        llist.printList();
	}

	
	/*
	 *  1) count the number of nodes in Linked List
	 *  2. check if value of n is not more than length of
        // the linked list
         * 
         2) get the (n-len+1)th node from the begining
	 */
	private void printNthFromLast(int n) {

		int len = 0;
		Node temp = head;
		
		while(temp != null) {
			temp = temp.next;
			len++;
		}
		System.out.println("Length : "+len);
		if(n >len) {
			System.out.println("Lenght of list is less than : "+n);
			
		}
		
		temp = head;
		for(int i=1; i< len-n+1 ;i++) {
			temp = temp.next;
		}
		System.out.println(temp.data);
	}
	
	public void push(int new_data)
    {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

	//using recursion
	void printNthFromLast(Node head, int n) 
	{
	    int i = 0;
	    if (head == null)
	       return;
	    printNthFromLast(head.next, n);
	    if (++i == n)
	       System.out.println("Node : "+ head.data);
	}
	
	
	/*
	 * Using single scan******
	 * 
	 * 
	 * 
	 * Maintain two pointers – reference pointer and main pointer. 
	 * Initialize both reference and main pointers to head. First move reference pointer to 
	 * n nodes from head. Now move both pointers one by one until reference pointer reaches end. 
	 * Now main pointer will point to nth node from the end. Return main pointer
	 * 
	 * 
	 * Solution: Yes. Efficient Approach: Use two pointers pNthNode and pTemp. Initially, both point
		to head node of the list. pNthNode starts moving only after pTemp has made n moves.
		From there both move forward until pTemp reaches the end of the list. As a result pNthNode
		points to nth node from the end of the linked list.
	 */
			
	void printNthFromLastUsing2Pointers(int n) {
		Node main_ptr = head;
		Node ref_ptr = head;
		
		int count = 0;
		
		if(head != null) {
			while(count < n) {
				if(ref_ptr == null) {
					System.out.println(n+" is greater than the number of elements on the list");
					return;
				}
				ref_ptr = ref_ptr.next;
				count++;
			}
			while(ref_ptr != null) {
				main_ptr = main_ptr.next;
				ref_ptr = ref_ptr.next;
			}
			System.out.println("Usig 2 prts : Node no. "+n+" from last is "+ main_ptr.data);
		}
		
	}
	
	void removeNthFromLast(Node head, int n) {
	    System.out.println("Deleting "+n+"th node from last");
	    int len = 0;
        Node temp = head;
        Node prev = null;
        
        while(temp != null) {
            temp = temp.next;
            len++;
        }
        //System.out.println("Length : "+len);
        if(n >len) {
            System.out.println("Lenght of list is less than : "+n);
            
        }
        
        for(int i=1; i< len-n+1 ;i++) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
        //System.out.println(temp.data);
	        
	}
	
	
	
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
