package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 560 · Subarray Sum Equals K (Medium)                 [was: lc/medium/NumberOfSubrraysWithGivenSum]
 *
 * Pattern : prefix sum + count of earlier prefix sums equal to (prefix - k).
 * Optimal : O(n) time, O(n) space. Works with negatives (sliding window does not).
 * Changed : merge() for counting; the logic was already optimal.
 *
 * L6/L7 follow-ups:
 *  - All non-negative -> sliding window gives O(1) space.
 *  - Divisible by k (LC 974) -> key by floorMod(prefix, k).
 *  - 2D version (LC 1074) -> fix row pair, run this on column sums: O(r^2 * c).
 */
public class LC0560_SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);
        int prefix = 0;
        int res = 0;
        for (int x : nums) {
            prefix += x;
            res += count.getOrDefault(prefix - k, 0);
            count.merge(prefix, 1, Integer::sum);
        }
        return res;
    }

    public static void main(String[] args) {
        Check.eq(subarraySum(new int[] {1, 1, 1}, 2), 2);
        Check.eq(subarraySum(new int[] {10, 2, -2, -20, 10}, -10), 3);
        Check.eq(subarraySum(new int[] {1, -1, 0}, 0), 3);
    }
}
