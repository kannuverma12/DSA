package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 2 · Add Two Numbers (Medium)                         [was: lc/medium/L56_AddTwoNumbersLinkedList]
 *
 * Pattern : dummy head + carry; loop while either list or the carry remains.
 * Optimal : O(max(m, n)) time, O(1) extra.
 * Changed : folding `carry` into the loop condition removes the trailing special case.
 *
 * L6/L7 follow-up: digits stored most-significant first (LC 445) without reversing input ->
 * two stacks, or recurse after padding the shorter list.
 */
public class LC0002_AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(addTwoNumbers(ListNode.of(2, 4, 3), ListNode.of(5, 6, 4))), "7->0->8");
        Check.eq(String.valueOf(addTwoNumbers(ListNode.of(9, 9, 9), ListNode.of(1))), "0->0->0->1");
    }
}
