package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 152 · Maximum Product Subarray (Medium)              [was: lc/medium/MaximumProductSubarray]
 *
 * Pattern : Kadane variant tracking both max and min product ending here (a negative
 *           number swaps them).
 * Optimal : O(n) time, O(1) space.
 * Changed : the original's default entry point was the O(n^2) double loop; now only the
 *           linear version, with the swap trick instead of a temp variable.
 */
public class LC0152_MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) {
                int t = max;
                max = min;
                min = t;
            }
            max = Math.max(x, max * x);
            min = Math.min(x, min * x);
            best = Math.max(best, max);
        }
        return best;
    }

    public static void main(String[] args) {
        Check.eq(maxProduct(new int[] {2, 3, -2, 4}), 6);
        Check.eq(maxProduct(new int[] {-2, 0, -1}), 0);
        Check.eq(maxProduct(new int[] {1, -2, -3, 0, 7, -8, -2}), 112);
        Check.eq(maxProduct(new int[] {-2}), -2);
    }
}
