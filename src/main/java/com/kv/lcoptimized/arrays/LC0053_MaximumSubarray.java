package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 53 · Maximum Subarray (Medium)                       [was: lc/medium/LargestSumContiguousSubarray]
 *
 * Pattern : Kadane - best subarray ending here = max(x, bestEndingHere + x).
 * Optimal : O(n) time, O(1) space.
 * Changed : one version that handles all-negative input; also returns the indices, which
 *           interviewers commonly ask for next.
 *
 * L6/L7 follow-ups:
 *  - Divide & conquer O(n log n) - leads into segment trees for range max-subarray queries.
 *  - Circular array (LC 918): max(kadane, total - minKadane), careful when all negative.
 *  - Max sum with at most k length -> prefix sums + monotonic deque.
 */
public class LC0053_MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        int best = nums[0];
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

    /** Returns {sum, start, end}. */
    public static int[] maxSubArrayWithRange(int[] nums) {
        int best = nums[0];
        int bestStart = 0;
        int bestEnd = 0;
        int cur = nums[0];
        int curStart = 0;
        for (int i = 1; i < nums.length; i++) {
            if (cur < 0) {
                cur = nums[i];
                curStart = i;
            } else {
                cur += nums[i];
            }
            if (cur > best) {
                best = cur;
                bestStart = curStart;
                bestEnd = i;
            }
        }
        return new int[] {best, bestStart, bestEnd};
    }

    public static void main(String[] args) {
        Check.eq(maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        Check.eq(maxSubArray(new int[] {-3, -1, -2}), -1);
        Check.eq(maxSubArrayWithRange(new int[] {-2, -3, 4, -1, -2, 1, 5, -3}), new int[] {7, 2, 6});
    }
}
