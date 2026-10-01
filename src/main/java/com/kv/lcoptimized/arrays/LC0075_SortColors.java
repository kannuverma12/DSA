package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 75 · Sort Colors (Medium)                            [was: lc/medium/L22_SortColors0s1s2s]
 *
 * Pattern : Dutch National Flag, 3-way partition.
 * Optimal : one pass, O(1) space.
 * Changed : the original used two-pass counting sort. The problem's own follow-up asks for
 *           one pass, which is what an interviewer will push for.
 *
 * Invariant: [0,lo) = 0, [lo,mid) = 1, (hi,end] = 2, [mid,hi] unknown.
 * Follow-up: this is the partition step of 3-way quicksort (fast on many duplicates).
 */
public class LC0075_SortColors {

    public static void sortColors(int[] nums) {
        int lo = 0;
        int mid = 0;
        int hi = nums.length - 1;
        while (mid <= hi) {
            if (nums[mid] == 0) {
                swap(nums, lo++, mid++);
            } else if (nums[mid] == 2) {
                swap(nums, mid, hi--);   // don't advance mid: swapped-in value is unexamined
            } else {
                mid++;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {2, 0, 2, 1, 1, 0};
        sortColors(a);
        Check.eq(a, new int[] {0, 0, 1, 1, 2, 2});
        int[] b = {1, 0, 0, 2, 1, 1, 0, 0, 2, 2};
        sortColors(b);
        Check.eq(b, new int[] {0, 0, 0, 0, 1, 1, 1, 2, 2, 2});
    }
}
