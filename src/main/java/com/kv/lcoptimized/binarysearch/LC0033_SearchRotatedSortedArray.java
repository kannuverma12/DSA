package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 33 · Search in Rotated Sorted Array (Medium)         [was: lc/medium/L7_SearchInRotatedSortedArray]
 *
 * Pattern : binary search; one half around mid is always sorted - check if target lies in it.
 * Optimal : O(log n) time, O(1) space.
 * Changed : dropped the recursive twin (O(log n) stack for nothing).
 *
 * L6/L7 follow-ups:
 *  - With duplicates (LC 81): when nums[lo]==nums[mid]==nums[hi] you can't tell the sorted
 *    half -> shrink both ends; worst case O(n). Explain why that's unavoidable.
 *  - Find the rotation point / minimum (LC 153) - compare with nums[hi], not nums[lo].
 */
public class LC0033_SearchRotatedSortedArray {

    public static int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[lo] <= nums[mid]) {                 // left half sorted
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {                                     // right half sorted
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    /** LC 153: minimum of a rotated array without duplicates. */
    public static int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        Check.eq(search(a, 0), 4);
        Check.eq(search(a, 7), 3);
        Check.eq(search(a, 3), -1);
        Check.eq(search(new int[] {1}, 0), -1);
        Check.eq(findMin(a), 0);
        Check.eq(findMin(new int[] {11, 13, 15, 17}), 11);
    }
}
