package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;

/**
 * LC 179 · Largest Number (Medium)                        [was: lc/medium/ArrangeNumbersInArrayToFromBiggestNumber]
 *
 * Pattern : custom comparator, a before b iff a+b > b+a (as strings).
 * Optimal : O(n log n * L) where L = max digits.
 * Changed : the original comparator never returned 0, which breaks the Comparator contract
 *           (TimSort may throw "Comparison method violates its general contract!"). The
 *           Integer variant also overflowed. It also printed instead of returning, and
 *           "0,0" -> "00".
 *
 * L6/L7 follow-up: prove transitivity of the ordering (it's what makes sorting valid).
 */
public class LC0179_LargestNumber {

    public static String largestNumber(int[] nums) {
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, (a, b) -> (b + a).compareTo(a + b));
        if (s[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String x : s) {
            sb.append(x);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(largestNumber(new int[] {54, 546, 548, 60}), "6054854654");
        Check.eq(largestNumber(new int[] {3, 30, 34, 5, 9}), "9534330");
        Check.eq(largestNumber(new int[] {0, 0}), "0");
    }
}
