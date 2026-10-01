package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 25 · Reverse Nodes in k-Group (Hard)                 [was: lc/L72_ReverseLinkedListInKGroup]
 *
 * Pattern : for each group: check k nodes exist, reverse them in place, reconnect.
 * Optimal : O(n) time, O(1) space.
 * Changed : the original's "method 2" was recursive (O(n/k) stack) and reversed the final
 *           short group too, which the problem forbids. The iterative version was fine; this
 *           one makes the group boundaries explicit.
 */
public class LC0025_ReverseNodesInKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;
        while (true) {
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) {
                return dummy.next;              // fewer than k nodes left: leave as is
            }
            ListNode groupNext = kth.next;
            ListNode prev = groupNext;          // reversed group's tail links to the next group
            ListNode cur = groupPrev.next;
            while (cur != groupNext) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            ListNode oldFirst = groupPrev.next; // now the group's last node
            groupPrev.next = kth;
            groupPrev = oldFirst;
        }
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(reverseKGroup(ListNode.of(1, 2, 3, 4, 5), 2)), "2->1->4->3->5");
        Check.eq(String.valueOf(reverseKGroup(ListNode.of(1, 2, 3, 4, 5), 3)), "3->2->1->4->5");
        Check.eq(String.valueOf(reverseKGroup(ListNode.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 4)),
                "4->3->2->1->8->7->6->5->9");
        Check.eq(String.valueOf(reverseKGroup(ListNode.of(1, 2), 1)), "1->2");
    }
}
