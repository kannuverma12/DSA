package com.kv.LinkedList;

public class DeleteNodeFromLinkedList 
{ 
    Node head; // head of list 
  
    /* Linked list Node*/
    class Node 
    { 
        int data; 
        Node next; 
        Node(int d) 
        { 
            data = d; 
            next = null; 
        } 
    } 
  
    /* Given a key, deletes the first occurrence of key in linked list */
    void deleteNode(int key) 
    { 
        // Store head node 
        Node temp = head, prev = null; 
  
        // If head node itself holds the key to be deleted 
        if (temp != null && temp.data == key) 
        { 
            head = temp.next; // Changed head 
            return; 
        } 
  
        // Search for the key to be deleted, keep track of the 
        // previous node as we need to change temp.next 
        while (temp != null && temp.data != key) 
        { 
            prev = temp; 
            temp = temp.next; 
        }     
  
        // If key was not present in linked list 
        if (temp == null) return; 
  
        // Unlink the node from linked list 
        prev.next = temp.next; 
    } 
  
    /* Inserts a new Node at front of the list. */
    public void push(int new_data) 
    { 
        Node new_node = new Node(new_data); 
        new_node.next = head; 
        head = new_node; 
    } 
  
    /* This function prints contents of linked list starting from 
        the given node */
    public void printList() 
    { 
        Node tnode = head; 
        while (tnode != null) 
        { 
            System.out.print(tnode.data+" "); 
            tnode = tnode.next; 
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
    
    
    
   
  
    /* Drier program to test above functions. Ideally this function 
    should be in a separate user class. It is kept here to keep 
    code compact */
    public static void main(String[] args) 
    { 
        DeleteNodeFromLinkedList llist = new DeleteNodeFromLinkedList(); 
  
        llist.push(7); 
        llist.push(1); 
        llist.push(3); 
        llist.push(2); 
  
        System.out.println("\nCreated Linked list is:"); 
        llist.printList(); 
  
        llist.deleteNode(1); // Delete node at position 4 
  
        System.out.println("\nLinked List after Deletion at position 4:"); 
        llist.printList(); 
    } 
} 
