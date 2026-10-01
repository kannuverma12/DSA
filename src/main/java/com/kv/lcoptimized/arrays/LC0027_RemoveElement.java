package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 27 · Remove Element (Easy)                           [was: lc/L73_RemoveElement]
 *
 * Pattern : read/write pointers.
 * Optimal : O(n) time, O(1) space.
 * Changed : added the swap-with-tail variant, which does fewer writes when the
 *           element to remove is rare (order is not required to be kept).
 */
public class LC0027_RemoveElement {

    /** Stable: keeps the relative order of kept elements. */
    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x;
            }
        }
        return write;
    }

    /** Unstable: #writes == #occurrences of val. */
    public static int removeElementFewWrites(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[--n];
            } else {
                i++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        Check.eq(removeElement(new int[] {3, 2, 2, 3}, 3), 2);
        Check.eq(removeElementFewWrites(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2), 5);
    }
}
