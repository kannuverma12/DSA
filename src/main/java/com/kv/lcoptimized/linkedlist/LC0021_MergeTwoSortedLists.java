package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 21 · Merge Two Sorted Lists (Easy)                   [was: lc/L71_MergeTwoSortedLinkedListLC]
 *
 * Pattern : dummy head, splice the smaller node, attach the leftover tail at the end.
 * Optimal : O(m + n) time, O(1) space (re-links nodes, no allocation).
 * Changed : `<=` keeps the merge stable. The loop is simplified; the k-list version is now
 *           its own file (LC0023).
 */
public class LC0021_MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
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
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(mergeTwoLists(ListNode.of(1, 2, 4, 6), ListNode.of(3, 5, 7, 9))),
                "1->2->3->4->5->6->7->9");
        Check.eq(String.valueOf(mergeTwoLists(null, ListNode.of(0))), "0");
    }
}
