package com.kv.LinkedList;

import java.util.HashSet;
import java.util.Set;

public class CheckLoopInLinkedList {

    static Node head;

    public static void main(String[] args) {
        CheckLoopInLinkedList llist = new CheckLoopInLinkedList();

        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(10);

        /* Create loop for testing */
        llist.head.next.next.next.next = llist.head;

        if (detectLoop(head))
            System.out.println("Loop found");
        else
            System.out.println("No Loop");

        detectLoopUsingFloydMethod(head);

    }

    /*
     * Floyd’s Cycle-Finding Algorithm: This is the fastest method. Traverse linked
     * list using two pointers. Move one pointer by one and other pointer by two. If
     * these pointers meet at some node then there is a loop. If pointers do not
     * meet then linked list doesn’t have loop.
     * 
     * This problem was solved by Floyd. The solution is named the Floyd cycle
     * finding algorithm. It uses two pointers moving at different speeds to walk
     * the linked list. Once they enter the loop they are expected to meet, which
     * denotes that there is a loop. This works because the only way a faster moving
     * pointer would point to the same location as a slower moving pointer is if
     * somehow the entire list or a part of it is circular. Think of a tortoise and
     * a hare running on a track. The faster running hare will catch up with the
     * tortoise if they are running in a loop. As an example, consider the following
     * example and trace out the Floyd algorithm. From the diagrams below we can see
     * that after the final step they are meeting at some point in the loop which
     * may not be the starting point of the loop.
     */

    private static boolean detectLoopUsingFloydMethod(Node head2) {
        // TODO Auto-generated method stub

        Node slow_ptr = head;
        Node fast_ptr = head;

        while (slow_ptr != null && fast_ptr != null && fast_ptr.next != null) {
            slow_ptr = slow_ptr.next;
            fast_ptr = fast_ptr.next.next;

            if (slow_ptr == fast_ptr) {
                System.out.println("Loop found");
                return true;
            }
        }
        return false;

    }

    /*
     * Problem-14 Check whether the given linked list is NULL-terminated. If there
     * is a cycle, find the length of the loop. Solution: This solution is also an
     * extension of the basic cycle detection problem. After finding the loop in the
     * linked list, keep the slowPtr as it is. The fastPtr keeps on moving until it
     * again comes back to slowPtr. While moving fastPtr, use a counter variable
     * which increments at the rate of 1.
     */

    // Returns true if there is a loop in linked list else returns false.
    private static boolean detectLoop(Node head2) {

        Set<Node> set = new HashSet<Node>();
        while (head2 != null) {
            if (set.contains(head2))
                return true;
            set.add(head2);
            head2 = head2.next;
        }
        return false;
    }

    static public void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

}
