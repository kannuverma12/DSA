package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 15 · 3Sum (Medium)                                   [was: lc/medium/L2_ThreeSum]
 *
 * Pattern : sort + fix one element + two pointers.
 * Optimal : O(n^2) time, O(1) extra (ignoring sort / output).
 * Changed : early exit once nums[i] > 0 (no triplet can sum to 0), Arrays.asList for triplets.
 *
 * L6/L7 follow-ups:
 *  - Why not hash-based? Same O(n^2) but dedup is messier and constant is worse.
 *  - Generalise to k-sum -> see LC0018_FourSum.
 *  - Lower bound: 3SUM is conjectured to have no O(n^(2-e)) algorithm.
 */
public class LC0015_ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum < 0) {
                    lo++;
                } else if (sum > 0) {
                    hi--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == nums[hi - 1]) {
                        hi--;
                    }
                    lo++;
                    hi--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Check.eq(threeSum(new int[] {-1, 0, 1, 2, -1, -4}),
                Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
        Check.eq(threeSum(new int[] {0, 0, 0, 0}), Arrays.asList(Arrays.asList(0, 0, 0)));
        Check.eq(threeSum(new int[] {1, 2}).size(), 0);
    }
}
