package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;

/**
 * LC 525 · Contiguous Array + "largest subarray with equal letters and digits"
 *                                                         [was: lc/L67_LargestSubarrayWithEqualNumsAndChars]
 *
 * Pattern : map to +1/-1, then longest subarray with prefix-sum 0 = first index where each
 *           prefix sum was seen.
 * Optimal : O(n) time, O(n) space. Prefix sums are in [-n, n], so an int[] of size 2n+1
 *           replaces the HashMap.
 * Changed : original main() used the O(n^2) double loop and overwrote the caller's array.
 */
public class LC0525_ContiguousArray {

    /** LC 525: nums contains only 0 and 1. */
    public static int findMaxLength(int[] nums) {
        return longestZeroSum(nums.length, i -> nums[i] == 1 ? 1 : -1)[0];
    }

    /** GfG variant: returns {start, end} of the longest run with #letters == #non-letters, or {-1,-1}. */
    public static int[] equalLettersAndDigits(char[] arr) {
        int[] r = longestZeroSum(arr.length, i -> Character.isLetter(arr[i]) ? 1 : -1);
        return r[0] == 0 ? new int[] {-1, -1} : new int[] {r[1], r[1] + r[0] - 1};
    }

    interface Sign {
        int at(int i);
    }

    /** Returns {length, start}. */
    private static int[] longestZeroSum(int n, Sign sign) {
        int[] first = new int[2 * n + 1];
        Arrays.fill(first, -2);
        first[n] = -1;                       // prefix sum 0 "seen" before index 0
        int sum = n;                         // offset by n to keep indices non-negative
        int best = 0;
        int start = -1;
        for (int i = 0; i < n; i++) {
            sum += sign.at(i);
            if (first[sum] == -2) {
                first[sum] = i;
            } else if (i - first[sum] > best) {
                best = i - first[sum];
                start = first[sum] + 1;
            }
        }
        return new int[] {best, start};
    }

    public static void main(String[] args) {
        Check.eq(findMaxLength(new int[] {0, 1}), 2);
        Check.eq(findMaxLength(new int[] {0, 1, 0}), 2);
        Check.eq(findMaxLength(new int[] {0, 0, 1, 0, 0, 0, 1, 1}), 6);
        Check.eq(equalLettersAndDigits(new char[] {'A', 'B', 'X', '4', '6', 'X', 'a'}), new int[] {1, 4});
        Check.eq(equalLettersAndDigits(new char[] {'A', 'B'}), new int[] {-1, -1});
    }
}
