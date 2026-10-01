package com.kv.lcoptimized.heap;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * LC 215 · Kth Largest Element + "k smallest elements"    [was: lc/L6_KthLargestElementInArray,
 *                                                                lc/L68_FindKSmallestElementsInArray]
 *
 * A) Size-k min-heap: O(n log k) time, O(k) space. Best for STREAMS / when n is huge.
 * B) Quickselect with a RANDOM pivot and 3-way partition: O(n) expected, O(1) space.
 *    3-way partitioning keeps it linear on inputs with many duplicates.
 * Changed : the original quickselect always pivoted on the LAST element, so it was O(n^2) on
 *           sorted input (a common test). L68 used an insertion-sort style O(n*k) scan, and its
 *           guard `k <= 0 && k > length` could never be true. The max-heap comparator `y - x`
 *           can overflow; Collections.reverseOrder() is used instead.
 *
 * L6/L7 follow-ups: guaranteed O(n) -> median-of-medians; distributed top-k -> per-shard
 * heaps + merge; k-th smallest in a sorted matrix (LC 378) -> heap or binary search on value.
 */
public class LC0215_KthLargest {

    public static int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);
        for (int x : nums) {
            heap.add(x);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    public static int findKthLargest(int[] nums, int k) {
        int[] a = nums.clone();
        int target = a.length - k;                          // index in ascending order
        int lo = 0;
        int hi = a.length - 1;
        while (true) {
            int pivot = a[ThreadLocalRandom.current().nextInt(lo, hi + 1)];
            // 3-way partition: [lo, lt) < pivot, [lt, i) == pivot, (gt, hi] > pivot
            int lt = lo;
            int gt = hi;
            int i = lo;
            while (i <= gt) {
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }
            if (target < lt) {
                hi = lt - 1;
            } else if (target > gt) {
                lo = gt + 1;
            } else {
                return pivot;
            }
        }
    }

    /** k smallest elements (any order), O(n log k) with a max-heap of size k. */
    public static int[] smallestK(int[] nums, int k) {
        if (k <= 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k + 1, java.util.Collections.reverseOrder());
        for (int x : nums) {
            maxHeap.add(x);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Check.eq(findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2), 5);
        Check.eq(findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
        Check.eq(findKthLargestHeap(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
        int[] sorted = new int[200_000];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = i;
        }
        Check.eq(findKthLargest(sorted, 1), 199_999);
        int[] same = new int[200_000];
        Check.eq(findKthLargest(same, 100_000), 0);
        int[] small = smallestK(new int[] {1, 5, 8, 9, 6, 7, 3, 4, 2, 0}, 5);
        Arrays.sort(small);
        Check.eq(small, new int[] {0, 1, 2, 3, 4});
    }
}
