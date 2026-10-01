package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 41 · First Missing Positive (Hard)                   [was: lc/L78_FIndFirstMissingPositive]
 *
 * Pattern : cyclic sort / index-as-hash: put value v at index v-1.
 * Optimal : O(n) time (each swap fixes one slot), O(1) space.
 * Changed : single, clearly bounded condition (1 <= v <= n and target slot not already v).
 *
 * L6/L7 follow-ups:
 *  - Not allowed to mutate input -> O(n) bitset, or O(n log n) sort a copy.
 *  - Streaming / too big for memory -> external bitmap by ranges, or binary search on answer
 *    counting values <= mid per pass (O(n log n), O(1) memory).
 */
public class LC0041_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int target = nums[i] - 1;
                nums[i] = nums[target];
                nums[target] = target + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        Check.eq(firstMissingPositive(new int[] {1, 2, 0}), 3);
        Check.eq(firstMissingPositive(new int[] {3, 4, -1, 1}), 2);
        Check.eq(firstMissingPositive(new int[] {7, 8, 9, 11, 12}), 1);
        Check.eq(firstMissingPositive(new int[] {1, 1}), 2);
        Check.eq(firstMissingPositive(new int[] {}), 1);
    }
}
