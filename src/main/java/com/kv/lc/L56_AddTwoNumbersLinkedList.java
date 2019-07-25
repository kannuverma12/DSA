package com.kv.lc;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class L56_AddTwoNumbersLinkedList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = addTwoNumbers(l1, l2);
        printlist(l3);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode fake = new ListNode(0);
        ListNode p = fake;

        ListNode p1 = l1;
        ListNode p2 = l2;

        int carry = 0;
        while (p1 != null || p2 != null) {
            int sum = carry;
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }

            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }

            if (sum > 9) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }

            ListNode l = new ListNode(sum);
            p.next = l;
            p = p.next;
        }

        //don't forget check the carry value at the end
        if (carry > 0) {
            ListNode l = new ListNode(carry);
            p.next = l;
        }
        return fake.next;
    }


    static void printlist(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
