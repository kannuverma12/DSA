package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 82 · Remove Duplicates from Sorted List II (Medium) + LC 83 (keep one copy)
 *                                                         [was: lc/medium/L26_RemoveDuplicatesFromSortedLinkedList]
 *
 * Pattern : dummy head; when a run of equal values starts after prev, skip the whole run.
 * Optimal : O(n) time, O(1) space.
 * Changed : the original was already correct; LC 83 (keep one copy) was added since
 *           interviewers often ask both.
 */
public class LC0082_RemoveDuplicatesSortedListII {

    /** LC 82: drop every value that appears more than once. */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        while (prev.next != null) {
            ListNode cur = prev.next;
            if (cur.next != null && cur.next.val == cur.val) {
                int dup = cur.val;
                while (cur != null && cur.val == dup) {
                    cur = cur.next;
                }
                prev.next = cur;
            } else {
                prev = cur;
            }
        }
        return dummy.next;
    }

    /** LC 83: keep one copy of each value. */
    public static ListNode deleteDuplicatesKeepOne(ListNode head) {
        for (ListNode p = head; p != null && p.next != null; ) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(deleteDuplicates(ListNode.of(1, 2, 3, 3, 4, 4, 5))), "1->2->5");
        Check.eq(String.valueOf(deleteDuplicates(ListNode.of(1, 1, 1, 2, 3))), "2->3");
        Check.eq(String.valueOf(deleteDuplicates(ListNode.of(1, 1))), "null");
        Check.eq(String.valueOf(deleteDuplicatesKeepOne(ListNode.of(1, 1, 2, 3, 3))), "1->2->3");
    }
}
