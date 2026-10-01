package com.kv.lcoptimized.heap;

import com.kv.lcoptimized.common.Check;

import java.util.PriorityQueue;

/**
 * LC 1167 · Minimum Cost to Connect Sticks / GfG "Connect n ropes"   [was: lc/medium/ConnectNRopesMinimumCost]
 *
 * Pattern : Huffman - always merge the two cheapest. Exchange argument: the smallest items
 *           should be deepest in the merge tree.
 * Optimal : O(n log n) time, O(n) space.
 * Changed : costs are summed in long (n ropes of length ~1e4 overflow int quickly). The heap is
 *           seeded in one call, and the result is returned instead of computed inside main().
 *           Pre-sorted input -> two-queue trick gives O(n) after sorting.
 */
public class LC1167_ConnectRopes {

    public static long connectSticks(int[] sticks) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int s : sticks) {
            heap.add((long) s);
        }
        long cost = 0;
        while (heap.size() > 1) {
            long merged = heap.poll() + heap.poll();
            cost += merged;
            heap.add(merged);
        }
        return cost;
    }

    public static void main(String[] args) {
        Check.eq(connectSticks(new int[] {4, 3, 2, 6}), 29L);
        Check.eq(connectSticks(new int[] {1, 8, 3, 5}), 30L);
        Check.eq(connectSticks(new int[] {5}), 0L);
    }
}
