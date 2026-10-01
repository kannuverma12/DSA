package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 4 · Median of Two Sorted Arrays (Hard)               [was: lc/hard/L57_MedianOfTwoSortedArray]
 *
 * Pattern : binary search the partition of the SHORTER array so that
 *           left halves hold (m+n+1)/2 elements and maxLeft <= minRight.
 * Optimal : O(log(min(m, n))) time, O(1) space.
 * Changed : the original kept four methods. The merge-count one read arr1[j++] instead of
 *           arr2[j++] (wrong answer) and did integer division for even totals; the "method 4"
 *           recursion only works for equal-length arrays. Kept the partition method and
 *           replaced edge-case branches with +/- infinity sentinels.
 *
 * L6/L7 follow-ups:
 *  - k-th smallest of two sorted arrays (same idea, or discard k/2 per step: O(log k)).
 *  - Median of k sorted arrays / of a stream -> see design/LC0295_MedianFinder.
 */
public class LC0004_MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        int m = a.length;
        int n = b.length;
        int half = (m + n + 1) / 2;
        int lo = 0;
        int hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;         // elements taken from a
            int j = half - i;                // elements taken from b
            int aLeft = i == 0 ? Integer.MIN_VALUE : a[i - 1];
            int aRight = i == m ? Integer.MAX_VALUE : a[i];
            int bLeft = j == 0 ? Integer.MIN_VALUE : b[j - 1];
            int bRight = j == n ? Integer.MAX_VALUE : b[j];
            if (aLeft > bRight) {
                hi = i - 1;
            } else if (bLeft > aRight) {
                lo = i + 1;
            } else {
                int maxLeft = Math.max(aLeft, bLeft);
                if (((m + n) & 1) == 1) {
                    return maxLeft;
                }
                return ((long) maxLeft + Math.min(aRight, bRight)) / 2.0;
            }
        }
        throw new IllegalArgumentException("inputs must be sorted");
    }

    public static void main(String[] args) {
        Check.near(findMedianSortedArrays(new int[] {1, 3}, new int[] {2}), 2.0);
        Check.near(findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}), 2.5);
        Check.near(findMedianSortedArrays(new int[] {9, 15}, new int[] {5, 8, 10, 20}), 9.5);
        Check.near(findMedianSortedArrays(new int[] {}, new int[] {1}), 1.0);
    }
}
