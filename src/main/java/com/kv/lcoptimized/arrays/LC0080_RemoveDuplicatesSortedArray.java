package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;

/**
 * LC 26 / LC 80 · Remove Duplicates from Sorted Array (keep at most k)
 *                                                         [was: lc/medium/L25_RemoveDuplicatesFromSortedArray]
 *
 * Pattern : write pointer compares with the element k slots behind it.
 * Optimal : O(n) time, O(1) space.
 * Changed : the original header asked for "at most twice" but the method marked "use this"
 *           kept at most once. One method now covers both: k=1 is LC 26, k=2 is LC 80.
 */
public class LC0080_RemoveDuplicatesSortedArray {

    public static int removeDuplicates(int[] nums, int k) {
        int write = 0;
        for (int x : nums) {
            if (write < k || nums[write - k] != x) {
                nums[write++] = x;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 2, 3};
        int len = removeDuplicates(a, 2);
        Check.eq(Arrays.copyOf(a, len), new int[] {1, 1, 2, 2, 3});

        int[] b = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        len = removeDuplicates(b, 1);
        Check.eq(Arrays.copyOf(b, len), new int[] {0, 1, 2, 3, 4});
    }
}
