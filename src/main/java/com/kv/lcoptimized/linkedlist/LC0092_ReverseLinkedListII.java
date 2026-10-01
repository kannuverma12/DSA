package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 92 · Reverse Linked List II (Medium) + LC 206 · Reverse Linked List
 *                                                         [was: lc/L29_ReverseLinkedListFromNtoM]
 *
 * Pattern : dummy head; walk to node before `left`, then repeatedly move the node after
 *           `start` to the front of the sublist ("head insertion").
 * Optimal : one pass, O(1) space.
 * Changed : the original scanned the whole list to find the boundaries, then reversed with
 *           extra sentinel nodes. This version stops at `right` and needs no special case
 *           when left == 1.
 */
public class LC0092_ReverseLinkedListII {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode before = dummy;
        for (int i = 1; i < left; i++) {
            before = before.next;
        }
        ListNode start = before.next;               // becomes the tail of the reversed part
        for (int i = left; i < right; i++) {
            ListNode moved = start.next;
            start.next = moved.next;
            moved.next = before.next;
            before.next = moved;
        }
        return dummy.next;
    }

    /** LC 206 - know both the iterative and the recursive form cold. */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(reverseBetween(ListNode.of(1, 2, 3, 4, 5), 2, 4)), "1->4->3->2->5");
        Check.eq(String.valueOf(reverseBetween(ListNode.of(3, 5), 1, 2)), "5->3");
        Check.eq(String.valueOf(reverseBetween(ListNode.of(5), 1, 1)), "5");
        Check.eq(String.valueOf(reverseList(ListNode.of(1, 2, 3))), "3->2->1");
    }
}
