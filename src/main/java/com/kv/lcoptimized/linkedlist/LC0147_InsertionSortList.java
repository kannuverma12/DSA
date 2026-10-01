package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 147 · Insertion Sort List (Medium)                   [was: lc/L53_InsertionSortLinkedList]
 *
 * Pattern : dummy-headed sorted list; insert each node after the last node < it.
 * Complexity: O(n^2) worst, O(n) on already-sorted input thanks to the "tail" fast path.
 * Changed : the original allocated a new head node and its inner loop kept iterating after
 *           an insertion (extra comparisons, fragile). Here, a node that is >= the sorted tail
 *           is appended in O(1), so sorted input is linear. For real sorting use LC0148.
 */
public class LC0147_InsertionSortList {

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val >= tail.val) {
                tail.next = cur;
                tail = cur;
            } else {
                ListNode p = dummy;
                while (p.next.val <= cur.val) {
                    p = p.next;
                }
                cur.next = p.next;
                p.next = cur;
            }
            cur = next;
        }
        tail.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(insertionSortList(ListNode.of(4, 2, 1, 3))), "1->2->3->4");
        Check.eq(String.valueOf(insertionSortList(ListNode.of(-1, 5, 3, 4, 0))), "-1->0->3->4->5");
        Check.eq(String.valueOf(insertionSortList(ListNode.of(2, 3, 4, 3, 4, 5))), "2->3->3->4->4->5");
    }
}
