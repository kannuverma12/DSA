package com.kv.lcoptimized.slidingwindow;

import com.kv.lcoptimized.common.Check;

/**
 * LC 239 · Sliding Window Maximum (Hard)                  [NEW - monotonic deque]
 *
 * Pattern : deque of indices with decreasing values; front is the window max. An index is
 *           popped from the back when a bigger value arrives (it can never be max again),
 *           and from the front when it leaves the window.
 * Optimal : O(n) time (each index pushed/popped once), O(k) space.
 *           Heap gives O(n log n); mention it as the first idea, then improve.
 *
 * Implemented with a plain int[] ring (head/tail) - faster than ArrayDeque<Integer>.
 *
 * L6/L7 follow-ups: shortest subarray with sum >= K with negatives (LC 862) and
 * constrained subsequence sum (LC 1425) are the same deque over prefix sums / dp values.
 */
public class LC0239_SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] out = new int[n - k + 1];
        int[] dq = new int[n];
        int head = 0;
        int tail = 0;                          // deque = dq[head, tail)
        for (int i = 0; i < n; i++) {
            if (head < tail && dq[head] <= i - k) {
                head++;
            }
            while (head < tail && nums[dq[tail - 1]] <= nums[i]) {
                tail--;
            }
            dq[tail++] = i;
            if (i >= k - 1) {
                out[i - k + 1] = nums[dq[head]];
            }
        }
        return out;
    }

    public static void main(String[] args) {
        Check.eq(maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3), new int[] {3, 3, 5, 5, 6, 7});
        Check.eq(maxSlidingWindow(new int[] {1}, 1), new int[] {1});
        Check.eq(maxSlidingWindow(new int[] {9, 8, 7, 6}, 2), new int[] {9, 8, 7});
    }
}
