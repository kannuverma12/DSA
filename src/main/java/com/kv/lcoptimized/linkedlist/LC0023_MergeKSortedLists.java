package com.kv.lcoptimized.linkedlist;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;

import java.util.PriorityQueue;

/**
 * LC 23 · Merge k Sorted Lists (Hard)                     [was: method 2 inside lc/L71_MergeTwoSortedLinkedListLC]
 *
 * A) Min-heap of list heads: O(N log k) time, O(k) space.
 * B) Pairwise divide & conquer merge: O(N log k) time, O(1) extra. No heap, and it reuses
 *    mergeTwoLists.
 * Changed : moved out of the 2-list file; the broken commented-out "method 3" was removed.
 *
 * L6/L7 follow-ups:
 *  - Lists live on k different machines / files -> external k-way merge with buffered readers,
 *    heap of (value, source). Discuss I/O batching.
 *  - Very large k -> tournament (loser) tree; same big-O, fewer comparisons.
 */
public class LC0023_MergeKSortedLists {

    public static ListNode mergeKListsHeap(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((x, y) -> Integer.compare(x.val, y.val));
        for (ListNode l : lists) {
            if (l != null) {
                heap.add(l);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode n = heap.poll();
            tail.next = n;
            tail = n;
            if (n.next != null) {
                heap.add(n.next);
            }
        }
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        for (int step = 1; step < lists.length; step *= 2) {
            for (int i = 0; i + step < lists.length; i += 2 * step) {
                lists[i] = LC0021_MergeTwoSortedLists.mergeTwoLists(lists[i], lists[i + step]);
            }
        }
        return lists[0];
    }

    public static void main(String[] args) {
        Check.eq(String.valueOf(mergeKListsHeap(new ListNode[] {
            ListNode.of(1, 4, 5), ListNode.of(1, 3, 4), ListNode.of(2, 6)})), "1->1->2->3->4->4->5->6");
        Check.eq(String.valueOf(mergeKLists(new ListNode[] {
            ListNode.of(1, 4, 5), ListNode.of(1, 3, 4), ListNode.of(2, 6)})), "1->1->2->3->4->4->5->6");
        Check.eq(String.valueOf(mergeKLists(new ListNode[] {null})), "null");
        Check.eq(String.valueOf(mergeKLists(new ListNode[0])), "null");
    }
}
