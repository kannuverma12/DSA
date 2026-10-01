package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

/**
 * LC 142 · Linked List Cycle II (Medium)                  [was: lc/L52_LinkedListCycle2]
 *
 * Pattern : Floyd. After slow/fast meet, a pointer from head and one from the meeting point
 *           meet at the cycle entry. Proof: 2(a+b) = a+b+k*c  =>  a = k*c - b.
 * Optimal : O(n) time, O(1) space.
 * Changed : the original was already optimal. Be ready to derive the proof on the whiteboard.
 *
 * Same trick: Find the Duplicate Number (LC 287) - treat i -> nums[i] as a linked list.
 */
public class LC0142_LinkedListCycleII {

    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                for (ListNode p = head; p != slow; p = p.next) {
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    /** LC 287: n+1 numbers in [1, n], one duplicate, O(1) space, read-only. */
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(3, 2, 0, -4);
        head.next.next.next.next = head.next;
        Check.eq(detectCycle(head).val, 2);
        Check.isTrue(detectCycle(ListNode.of(1, 2)) == null);
        Check.eq(findDuplicate(new int[] {1, 3, 4, 2, 2}), 2);
        Check.eq(findDuplicate(new int[] {3, 1, 3, 4, 2}), 3);
    }
}
