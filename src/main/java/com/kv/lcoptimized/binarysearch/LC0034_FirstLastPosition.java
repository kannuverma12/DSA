package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 34 · First and Last Position (Medium) + LC 35 · Search Insert Position (Easy)
 *                        [was: lc/medium/L40_FindFirstAndLastPositionOfElementInSortedArray, lc/L76_SearchInsertPosition]
 *
 * Pattern : one lowerBound() primitive: first index i with nums[i] >= target, in [0, n].
 *           first = lowerBound(t), last = lowerBound(t + 1) - 1, insert position = lowerBound(t).
 * Optimal : O(log n) time, O(1) space.
 * Changed : the original had two differently-shaped loops (mid rounding up vs down), which is
 *           where off-by-one bugs come from. Learn one half-open template and reuse it.
 *           (lo + hi) >>> 1 avoids overflow.
 */
public class LC0034_FirstLastPosition {

    /** First index in [0, n] whose value is >= target. */
    public static int lowerBound(int[] nums, long target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[] {-1, -1};
        }
        return new int[] {first, lowerBound(nums, (long) target + 1) - 1};
    }

    /** LC 35. */
    public static int searchInsert(int[] nums, int target) {
        return lowerBound(nums, target);
    }

    public static void main(String[] args) {
        Check.eq(searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8), new int[] {3, 4});
        Check.eq(searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6), new int[] {-1, -1});
        Check.eq(searchRange(new int[] {}, 0), new int[] {-1, -1});
        Check.eq(searchRange(new int[] {Integer.MAX_VALUE}, Integer.MAX_VALUE), new int[] {0, 0});
        Check.eq(searchInsert(new int[] {1, 3, 5, 6}, 5), 2);
        Check.eq(searchInsert(new int[] {1, 3, 5, 6}, 2), 1);
        Check.eq(searchInsert(new int[] {1, 3, 5, 6}, 7), 4);
    }
}
