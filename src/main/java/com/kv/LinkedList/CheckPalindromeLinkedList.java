package com.kv.LinkedList;



/*
 * METHOD 1 (Use a Stack)
	A simple solution is to use a stack of list nodes. This mainly involves three steps.
	1) Traverse the given list from head to tail and push every visited node to stack.
	2) Traverse the list again. For every visited node, pop a node from stack and compare data of 
		popped node with currently visited node.
	3) If all nodes matched, then return true, else false.

	Time complexity of above method is O(n), but it requires O(n) extra space. Following methods solve this with constant extra space.
 * 
 * 
 */



/*
 * 
 * 	METHOD 2 (By reversing the list)
	This method takes O(n) time and O(1) extra space.
	1) Get the middle of the linked list.
	2) Reverse the second half of the linked list.
	3) Check if the first half and second half are identical.
	4) Construct the original linked list by reversing the second half again and attaching it back to the first half

	To divide the list in two halves, method 2 of this post is used.
	When number of nodes are even, the first and second half contain exactly half nodes. 
	The challenging thing in this method is to handle the case when number of nodes are odd. 
	We don’t want the middle node as part of any of the lists as we are going to compare
	them for equality. For odd case, we use a separate variable ‘midnode’.
 * 
 * 
 * 
 */
public class CheckPalindromeLinkedList {
	
	Node head, fast_ptr, slow_ptr, second_half;
	
	public static void main(String[] args) {
		CheckPalindromeLinkedList llist = new CheckPalindromeLinkedList();
 
        char str[] = {'a', 'b', 'a', 'c', 'a', 'b', 'a'};
        for (int i = 0; i< 7 ; i++) {
            llist.push(str[i]);
            //llist.printList(llist.head);
            if (llist.isPalindrome(llist.head) != false){
                System.out.println("Is Palindrome");
            }else{
                System.out.println("Not Palindrome");
            }
        }
	}
	
	private boolean isPalindrome(Node head2) {
		
		slow_ptr = head;
		fast_ptr = head;
		Node prev_of_slow_ptr = head;
		Node midNode = null;
		boolean res = true;
		
		if(head != null && head.next != null) {
			/* Get the middle of the list. */
			while(fast_ptr != null  && fast_ptr.next != null) {
				fast_ptr = fast_ptr.next.next;
				/*We need previous of the slow_ptr for linked lists  with odd elements */
				prev_of_slow_ptr = slow_ptr;
				slow_ptr = slow_ptr.next;
			}
			
			/* fast_ptr would become NULL when there are even elements in list and not NULL for odd elements. We need 
			 * to skip middle node for odd case and store it somewhere so that we can restore the original list */
			if(fast_ptr != null) {
				midNode = slow_ptr;
				slow_ptr = slow_ptr.next;
			}
			
			// Now reverse the second half and compare it with first half
			second_half = slow_ptr;
			prev_of_slow_ptr = null;
			reverse();
			
			res = compareList(head, second_half);
			/* Construct the original list back */
			reverse();
			
			if (midNode != null) {
                // If there was a mid node (odd size case) which                                                         
                // was not part of either first half or second half.
                prev_of_slow_ptr.next = midNode;
                midNode.next = second_half;
            } else
                prev_of_slow_ptr.next = second_half;
		}
		
		return res;
	}
	
	private boolean compareList(Node head1, Node head2) {
		Node temp1 = head1;
		Node temp2 = head2;
		if(temp1 == null && temp2 == null) {
			return false;
		}
		while(temp1 != null && temp2 != null) {
			if(temp1.data == temp2.data) {
				temp1 = temp1.next;
				temp2 = temp2.next;
				
			}else
				return false;
		}
		return false;
	}

	/* Function to reverse the linked list  Note that this function may change the head */
	private void reverse() {
		Node prev = null;
		Node current = second_half;
		Node next;
		
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			
		}
		second_half = prev;
	}

	public void push(char new_data) {
		Node new_node = new Node(new_data);
		new_node.next = head;
		head = new_node;
	}

	void printList(Node ptr) {
		while (ptr != null) {
			System.out.print(ptr.data + "->");
			ptr = ptr.next;
		}
		System.out.println("NULL");
	}

}
