package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 1 · Two Sum (Easy)                                   [was: lc/easy/L1_TwoSum]
 *
 * Pattern : hash map complement lookup.
 * Optimal : O(n) time, O(n) space, single pass.
 * Changed : kept only the one-pass map; brute force and two-pass variants removed.
 *           Returns an empty array instead of null.
 *
 * L6/L7 follow-ups:
 *  - Input already sorted -> two pointers, O(1) extra space (LC 167).
 *  - Stream of numbers with add()/find() (LC 170): trade off add vs find cost.
 *  - Count all pairs / pairs with duplicates -> store counts instead of indices.
 */
public class LC0001_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(nums.length * 2);
        for (int i = 0; i < nums.length; i++) {
            Integer j = seen.get(target - nums[i]);
            if (j != null) {
                return new int[] {j, i};
            }
            seen.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        Check.eq(twoSum(new int[] {2, 7, 11, 15}, 9), new int[] {0, 1});
        Check.eq(twoSum(new int[] {3, 3}, 6), new int[] {0, 1});
        Check.eq(twoSum(new int[] {1, 2}, 7), new int[0]);
    }


    public static int[] twoSum_kv(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            Integer found = seen.get(target - nums[i]);
            if (found != null) {
                return new int[]{found, i};

            }
            seen.put(nums[i], i);
        }
        return new int[0];
    }
}
