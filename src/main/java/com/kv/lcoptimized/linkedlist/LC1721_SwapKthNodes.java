package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 1721 · Swapping Nodes in a Linked List (Medium)      [was: lc/Q24_3_ReplaceNthAndNthFromLastInLL - empty stub]
 *
 * Swap the k-th node from the start with the k-th node from the end.
 * Pattern : one pass - `first` stops at k-th node; a second pointer started from head when
 *           we reached `first` lags by k-1, so it ends on the k-th from the end.
 * Optimal : O(n) time, O(1) space.
 *
 * Interview note: swapping VALUES is accepted on LeetCode; if the interviewer insists on
 * swapping NODES, handle adjacent nodes and first==second (use a dummy head + prev pointers).
 * Both are implemented below.
 */
public class LC1721_SwapKthNodes {

    public static ListNode swapValues(ListNode head, int k) {
        ListNode first = head;
        for (int i = 1; i < k; i++) {
            first = first.next;
        }
        ListNode second = head;
        for (ListNode p = first; p.next != null; p = p.next) {
            second = second.next;
        }
        int t = first.val;
        first.val = second.val;
        second.val = t;
        return head;
    }

    public static ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) {
            n++;
        }
        int i = Math.min(k, n - k + 1);
        int j = Math.max(k, n - k + 1);
        if (i == j) {
            return head;
        }
        ListNode prevA = dummy;
        for (int c = 1; c < i; c++) {
            prevA = prevA.next;
        }
        ListNode prevB = dummy;
        for (int c = 1; c < j; c++) {
            prevB = prevB.next;
        }
        ListNode a = prevA.next;
        ListNode b = prevB.next;
        if (a.next == b) {                        // adjacent: prevA -> b -> a -> rest
            a.next = b.next;
            b.next = a;
            prevA.next = b;
        } else {
            ListNode afterA = a.next;
            a.next = b.next;
            b.next = afterA;
            prevA.next = b;
            prevB.next = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(swapValues(ListNode.of(1, 2, 3, 4, 5), 2)), "1->4->3->2->5");
        Check.eq(String.valueOf(swapNodes(ListNode.of(1, 2, 3, 4, 5), 2)), "1->4->3->2->5");
        Check.eq(String.valueOf(swapNodes(ListNode.of(1, 2, 3, 4), 2)), "1->3->2->4");
        Check.eq(String.valueOf(swapNodes(ListNode.of(1, 2), 1)), "2->1");
        Check.eq(String.valueOf(swapNodes(ListNode.of(1, 2, 3), 2)), "1->2->3");
        Check.eq(String.valueOf(swapNodes(ListNode.of(7, 9, 6, 6, 7, 8, 3, 0, 9, 5), 5)), "7->9->6->6->8->7->3->0->9->5");
    }
}
