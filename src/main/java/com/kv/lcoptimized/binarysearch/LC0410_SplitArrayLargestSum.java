package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 410 · Split Array Largest Sum (Hard)                 [NEW - "binary search on the answer"]
 *
 * Split nums into k non-empty contiguous parts minimising the largest part sum.
 *
 * Pattern : the answer is monotone: if max-sum S is feasible, any S' > S is too. Binary search
 *           S in [max(nums), sum(nums)], checking feasibility greedily in O(n).
 * Optimal : O(n log(sum)) time, O(1) space. (DP is O(k n^2) - mention it, then improve.)
 *
 * The same template covers: Koko Eating Bananas (LC 875), Capacity to Ship Packages (LC 1011),
 * Minimize Max Distance to Gas Station (LC 774, on doubles), Magnetic Force (LC 1552, maximise).
 * Recognising this shape quickly is a strong signal in Google rounds.
 */
public class LC0410_SplitArrayLargestSum {

    public static int splitArray(int[] nums, int k) {
        long lo = 0;
        long hi = 0;
        for (int x : nums) {
            lo = Math.max(lo, x);
            hi += x;
        }
        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            if (partsNeeded(nums, mid) <= k) {
                hi = mid;           // feasible: try smaller
            } else {
                lo = mid + 1;
            }
        }
        return (int) lo;
    }

    /** Greedy: extend the current part until adding the next element would exceed cap. */
    private static int partsNeeded(int[] nums, long cap) {
        int parts = 1;
        long cur = 0;
        for (int x : nums) {
            if (cur + x > cap) {
                parts++;
                cur = 0;
            }
            cur += x;
        }
        return parts;
    }

    public static void main(String[] args) {
        Check.eq(splitArray(new int[] {7, 2, 5, 10, 8}, 2), 18);
        Check.eq(splitArray(new int[] {1, 2, 3, 4, 5}, 2), 9);
        Check.eq(splitArray(new int[] {1, 4, 4}, 3), 4);
    }
}
