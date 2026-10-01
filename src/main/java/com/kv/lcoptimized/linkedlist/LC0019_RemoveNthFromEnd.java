package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 19 · Remove Nth Node From End of List (Medium)       [was: lc/medium/L39_DeleteNthLastNodeFromLinkedList]
 *
 * Pattern : dummy head + fast pointer n+1 ahead, one pass.
 * Optimal : O(L) time, O(1) space.
 * Changed : the dummy node removes the "remove the head" special case. The two-pass variant
 *           was dropped. The original main() also relied on a static head field.
 */
public class LC0019_RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(removeNthFromEnd(ListNode.of(1, 2, 3, 4, 5), 2)), "1->2->3->5");
        Check.eq(String.valueOf(removeNthFromEnd(ListNode.of(1), 1)), "null");
        Check.eq(String.valueOf(removeNthFromEnd(ListNode.of(1, 2), 2)), "2");
    }
}
