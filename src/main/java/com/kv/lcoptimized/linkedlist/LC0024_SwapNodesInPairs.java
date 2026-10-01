package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 24 · Swap Nodes in Pairs (Medium)                    [was: lc/medium/L54_SwapNodesInPair]
 *
 * Pattern : dummy head; for each pair (a, b) after prev: prev->b->a->rest.
 * Optimal : O(n) time, O(1) space.
 * Changed : named pointers (prev, a, b) instead of t1/t2 bookkeeping. This is k-group
 *           reversal with k = 2 (see LC0025).
 */
public class LC0024_SwapNodesInPairs {

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;
            a.next = b.next;
            b.next = a;
            prev.next = b;
            prev = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(swapPairs(ListNode.of(1, 2, 4, 6))), "2->1->6->4");
        Check.eq(String.valueOf(swapPairs(ListNode.of(1, 2, 3))), "2->1->3");
        Check.eq(String.valueOf(swapPairs(null)), "null");
    }
}
