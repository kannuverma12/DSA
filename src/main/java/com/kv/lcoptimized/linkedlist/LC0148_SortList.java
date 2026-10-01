package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 148 · Sort List (Medium)                             [was: lc/L54_SortLinkedList]
 *
 * Pattern : bottom-up merge sort. Merge runs of size 1, 2, 4, ... by splitting and re-linking.
 * Optimal : O(n log n) time, O(1) extra space (no recursion stack).
 * Changed : the problem asks for constant space, but the original allocated a NEW node for
 *           every element in every merge (O(n log n) garbage, O(n) live) and recursed
 *           (O(log n) stack). Top-down with slow/fast split is fine to present first; this is
 *           the follow-up answer.
 */
public class LC0148_SortList {

    public static ListNode sortList(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) {
            n++;
        }
        ListNode dummy = new ListNode(0, head);
        for (int size = 1; size < n; size *= 2) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, size);
                cur = split(right, size);
                prev = mergeInto(prev, left, right);
            }
        }
        return dummy.next;
    }

    /** Cuts after `size` nodes and returns the head of the remainder. */
    private static ListNode split(ListNode head, int size) {
        for (int i = 1; head != null && i < size; i++) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode rest = head.next;
        head.next = null;
        return rest;
    }

    /** Appends merge(a, b) after tail; returns the new tail. */
    private static ListNode mergeInto(ListNode tail, ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(sortList(ListNode.of(4, 2, 1, 3))), "1->2->3->4");
        Check.eq(String.valueOf(sortList(ListNode.of(-1, 5, 3, 4, 0))), "-1->0->3->4->5");
        Check.eq(String.valueOf(sortList(ListNode.of(2, 3, 4, 3, 4, 5))), "2->3->3->4->4->5");
        Check.eq(String.valueOf(sortList(null)), "null");
    }
}
