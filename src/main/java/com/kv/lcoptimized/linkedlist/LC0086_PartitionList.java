package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 86 · Partition List (Medium)                         [was: lc/medium/L27_PartitionLinkedList]
 *
 * Pattern : two dummy-headed lists (less / greater-or-equal), then concatenate. Stable.
 * Optimal : O(n) time, O(1) space.
 * Changed : two appends replace the original's in-place unlink/relink with prev tracking,
 *           which was harder to follow. Remember to cut the tail (big.next = null) to avoid a cycle.
 */
public class LC0086_PartitionList {

    public static ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode bigHead = new ListNode(0);
        ListNode small = smallHead;
        ListNode big = bigHead;
        for (ListNode p = head; p != null; p = p.next) {
            if (p.val < x) {
                small.next = p;
                small = p;
            } else {
                big.next = p;
                big = p;
            }
        }
        big.next = null;
        small.next = bigHead.next;
        return smallHead.next;
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(partition(ListNode.of(1, 4, 3, 2, 5, 2), 3)), "1->2->2->4->3->5");
        Check.eq(String.valueOf(partition(ListNode.of(2, 1), 2)), "1->2");
    }
}
