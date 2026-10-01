package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;

/**
 * LC 16 · 3Sum Closest (Medium)                           [was: lc/medium/L3_ThreeSumClosest]
 *
 * Pattern : sort + two pointers, track best |sum - target|.
 * Optimal : O(n^2) time, O(1) extra.
 * Changed : skip duplicate anchors, seed with a real sum (no MAX_VALUE sentinel math).
 */
public class LC0016_ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int best = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Check.eq(threeSumClosest(new int[] {-1, 2, 1, -4}, 1), 2);
        Check.eq(threeSumClosest(new int[] {0, 0, 0}, 1), 0);
    }
}
