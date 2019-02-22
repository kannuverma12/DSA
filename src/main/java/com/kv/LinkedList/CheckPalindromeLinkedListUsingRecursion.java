package com.kv.LinkedList;

/**
 * 
 * @author karanverma
 *
 */
public class CheckPalindromeLinkedListUsingRecursion {

    Node head; // head of list
    Node left;
    
    public static void main(String[] args) {
        CheckPalindromeLinkedListUsingRecursion llist = new CheckPalindromeLinkedListUsingRecursion();

        char str[] = { 'a', 'b', 'a', 'c', 'a', 'b', 'a' };
        String string = new String(str);
        for (int i = 0; i < 7; i++) {
            llist.push(str[i]);
            //llist.printList(llist.head);
            if (llist.isPalindrome(llist.head) != false) {
                System.out.println("Is Palindrome j");
            } else {
                System.out.println("Not Palindrome");
            }
        }
    }
    
    boolean isPalindrome(Node head) {
        boolean result = isPalindromeUtil(head);
        return result;
    }

    // Initial parameters to this function are &head and head
    boolean isPalindromeUtil(Node right) {
        left = head;

        /* stop recursion when right becomes NULL */
        if (right == null)
            return true;

        /*
         * If sub-list is not palindrome then no need to check for current left and
         * right, return false
         */
        boolean isp = isPalindromeUtil(right.next);
        if (isp == false)
            return false;

        /* Check values at current left and right */
        boolean isp1 = (right.data == (left).data);

        /* Move left to next node */
        left = left.next;

        return isp1;
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
