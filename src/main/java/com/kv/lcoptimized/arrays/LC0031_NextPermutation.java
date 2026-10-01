package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 31 · Next Permutation (Medium)                       [was: lc/medium/L5_NextPermutation]
 *
 * Pattern : find rightmost ascent i, swap with rightmost element > nums[i], reverse suffix.
 * Optimal : O(n) time, O(1) space.
 * Changed : uses i = -1 as the "no ascent" signal instead of the p==0 && q==0 special case,
 *           which was hard to reason about.
 *
 * L6/L7 follow-ups:
 *  - Why is the suffix non-increasing? That's the invariant that makes the reverse correct.
 *  - Previous permutation: mirror every comparison.
 *  - k-th next permutation -> see LC0060 (factorial number system).
 */
public class LC0031_NextPermutation {

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void reverse(int[] a, int lo, int hi) {
        while (lo < hi) {
            swap(a, lo++, hi--);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        nextPermutation(a);
        Check.eq(a, new int[] {1, 3, 2});
        int[] b = {3, 2, 1};
        nextPermutation(b);
        Check.eq(b, new int[] {1, 2, 3});
        int[] c = {1, 1, 5};
        nextPermutation(c);
        Check.eq(c, new int[] {1, 5, 1});
        int[] d = {1, 5, 8, 4, 7, 6, 5, 3, 1};
        nextPermutation(d);
        Check.eq(d, new int[] {1, 5, 8, 5, 1, 3, 4, 6, 7});
    }
}
