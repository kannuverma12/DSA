package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 61 · Rotate List (Medium)                            [was: lc/L18_RotateLinkedListRight]
 *
 * Pattern : measure length and tail, close into a ring, cut at (len - k % len).
 * Optimal : O(n) time, O(1) space - independent of k.
 * Changed : the original advanced `fast` k times, wrapping around, so it was O(k). LeetCode
 *           allows k up to 2 * 10^9, which times out. k % len fixes it.
 */
public class LC0061_RotateList {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        k %= len;
        if (k == 0) {
            return head;
        }
        ListNode newTail = head;
        for (int i = 1; i < len - k; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(rotateRight(ListNode.of(1, 2, 3, 4, 5), 2)), "4->5->1->2->3");
        Check.eq(String.valueOf(rotateRight(ListNode.of(0, 1, 2), 4)), "2->0->1");
        Check.eq(String.valueOf(rotateRight(ListNode.of(1, 2), 2_000_000_000)), "1->2");
    }
}
