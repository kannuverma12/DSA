package com.kv.LinkedList;

/**
 * 
 * @author karanverma
 * 
 * Merge two sorted Linked List.
 * 
 * Let head be the first node of the linked list to be sorted and headRef be the pointer to head. 
 * Note that we need a reference to head in Sort_MergeSort() as the below implementation changes next links to
 * sort the linked lists (not data at the nodes), so head node has to be changed if the data at original 
 * head is not the smallest value in linked list.

 * Sort_MergeSort(headRef)
 * 1) If head is NULL or there is only one element in the Linked List then return.
 * 2) Else divide the linked list into two halves.  
 *      FrontBackSplit(head, &a, &b); // a and b are two halves 
 * 3) Sort the two halves a and b.
 *          Sort_MergeSort(a);
 *          Sort_MergeSort(b);
 * 4) Merge the sorted a and b (using SortedMerge() discussed here) and update the head pointer using headRef.
 *          *headRef = SortedMerge(a, b);
 *
 */
public class MergeTwoSortedLinkedList {

	Node head;
	
	public static void main(String[] args) {
		MergeTwoSortedLinkedList li = new MergeTwoSortedLinkedList();
        li.push(15);
        li.push(10);
        li.push(5);
        li.push(20);
        li.push(3);
        li.push(2);
        System.out.println("Linked List without sorting is :");
        li.printList(li.head);
 
        // Apply merge Sort
        li.head = li.mergeSort(li.head);
        System.out.print("\n Sorted Linked List is: \n");
        li.printList(li.head);

	}
	
    Node mergeSort(Node h) {
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        Node middle = getMiddle(h);
        Node nextofmiddle = middle.next;

        middle.next = null;

        Node left = mergeSort(h);

        // Apply mergeSort on right list
        Node right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        Node sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    Node sortedMerge(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        /* Pick either a or b, and recur */
        if (a.data <= b.data) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;

    }

    Node getMiddle(Node h) {
        if (h == null)
            return h;
        Node fastptr = h.next;
        Node slowptr = h;

        // Move fastptr by two and slow ptr by one Finally slowptr will point to middle
        // node
        while (fastptr != null) {
            fastptr = fastptr.next;
            if (fastptr != null) {
                slowptr = slowptr.next;
                fastptr = fastptr.next;
            }
        }
        return slowptr;
    }

    void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    void printList(Node headref) {
        while (headref != null) {
            System.out.print(headref.data + " ");
            headref = headref.next;
        }
    }
}
