package com.kv.lc;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author karanverma
 *
 */
public class L71_MergeTwoSortedLinkedListLC {
    
    ListNode head;

    public static void main(String[] args) {
        L71_MergeTwoSortedLinkedListLC l1 = new L71_MergeTwoSortedLinkedListLC();
        l1.push(6);
        l1.push(4);
        l1.push(2);
        l1.push(1);
        System.out.println("L1");
        l1.printList(l1.head);

        L71_MergeTwoSortedLinkedListLC l2 = new L71_MergeTwoSortedLinkedListLC();
        l2.push(9);
        l2.push(7);
        l2.push(5);
        l2.push(3);
        System.out.println("l2");
        l1.printList(l2.head);

        System.out.println("Merged l1 and l2");
        ListNode lMerged = mergeTwoLists(l1.head, l2.head);
        l1.printList(lMerged);

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            } else if (l1 == null) {
                p.next = l2;
                break;
            } else if (l2 == null) {
                p.next = l1;
                break;
            }
        }

        return head.next;
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void push(int new_data) {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head;
        head = new_node;
    }

    void printList(ListNode l) {
        ListNode temp = l;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Method 2 : Using Priority Queue
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0)
            return null;

        Comparator<ListNode> comp = Comparator.comparing(ListNode->ListNode.val);
        PriorityQueue<ListNode> queue = new PriorityQueue<>(comp);

        ListNode head = new ListNode(0);
        ListNode p = head;

        for(ListNode list: lists){
            if(list!=null)
                queue.offer(list);
        }

        while(!queue.isEmpty()){
            ListNode n = queue.poll();
            p.next = n;
            p=p.next;

            if(n.next!=null)
                queue.offer(n.next);
        }

        return head.next;

    }

    // method 3 - min heap

    /*public static Node mergeKSortedLists(Node arr[], int k)
    {
        Node head = null, last = null;

        // priority_queue ‘pq’ implemeted as min heap with the
        // help of ‘compare’ function
        PriorityQueue pq = new PriorityQueue<>(new Comparator() {
            public int compare(Node a, Node b) {
                return a.data –b.data;
            }
        });

        // push the head nodes of all the k lists in ‘pq’
        for (int i = 0; i < k; i++)
            if (arr[i] != null)
                pq.add(arr[i]); // loop till 'pq' is not empty
         while (!pq.isEmpty()) { // get the top element of 'pq'
              Node top = pq.peek(); pq.remove();
              // check if there is a node next to the 'top' node // in the list of which 'top' node is a member

             if (top.next != null) // push the next node in 'pq' pq.add(top.next); // if final merged list is empty if (head == null) { head = top; // points to the last node so far of // the final merged list last = top; } else { // insert 'top' at the end of the merged list so far last.next = top; // update the 'last' pointer last = top; } } // head node of the required merged list return head; } // function to print the singly linked list public static void printList(Node head) { while (head != null) { System.out.print(head.data + " "); head = head.next; } } // Utility function to create a new node public Node push(int data) { Node newNode = new Node(data); newNode.next = null; return newNode; } public static void main(String args[]) { int k = 3; // Number of linked lists int n = 4; // Number of elements in each list // an array of pointers storing the head nodes // of the linked lists Node arr[] = new Node[k]; arr[0] = new Node(1); arr[0].next = new Node(3); arr[0].next.next = new Node(5); arr[0].next.next.next = new Node(7); arr[1] = new Node(2); arr[1].next = new Node(4); arr[1].next.next = new Node(6); arr[1].next.next.next = new Node(8); arr[2] = new Node(0); arr[2].next = new Node(9); arr[2].next.next = new Node(10); arr[2].next.next.next = new Node(11); // Merge all lists Node head = mergeKSortedLists(arr, k); printList(head); }
*/
}
