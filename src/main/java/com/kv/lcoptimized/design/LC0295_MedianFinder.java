package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LC 295 · Find Median from Data Stream (Hard)            [NEW - two heaps]
 *
 * low = max-heap (smaller half), high = min-heap (larger half), |low| - |high| in {0, 1}.
 * addNum O(log n), findMedian O(1).
 *
 * L6/L7 follow-ups:
 *  - All values in [0, 100] -> counting array, O(1) add, O(100) median.
 *  - 99% of values in [0, 100] -> counting array + two overflow heaps / counters.
 *  - Sliding window median (LC 480) -> two heaps with lazy deletion, or two TreeMaps (multisets).
 *  - Distributed / approximate -> t-digest or quantile sketches (KLL, GK).
 */
public class LC0295_MedianFinder {

    private final PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> high = new PriorityQueue<>();

    public void addNum(int num) {
        low.add(num);
        high.add(low.poll());                 // largest of the low half moves up
        if (high.size() > low.size()) {
            low.add(high.poll());             // rebalance so low holds the extra element
        }
    }

    public double findMedian() {
        if (low.size() > high.size()) {
            return low.peek();
        }
        return ((long) low.peek() + high.peek()) / 2.0;
    }

    public static void main(String[] args) {
        LC0295_MedianFinder m = new LC0295_MedianFinder();
        m.addNum(1);
        m.addNum(2);
        Check.near(m.findMedian(), 1.5);
        m.addNum(3);
        Check.near(m.findMedian(), 2.0);
        LC0295_MedianFinder big = new LC0295_MedianFinder();
        big.addNum(Integer.MAX_VALUE);
        big.addNum(Integer.MAX_VALUE);
        Check.near(big.findMedian(), Integer.MAX_VALUE);
    }
}
