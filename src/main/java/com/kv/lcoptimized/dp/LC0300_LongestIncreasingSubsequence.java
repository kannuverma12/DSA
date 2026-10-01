package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 300 · Longest Increasing Subsequence (Medium)        [NEW - top-5 DP pattern]
 *
 * O(n^2) DP: lis[i] = 1 + max lis[j] for j < i with a[j] < a[i]. Say it first.
 * O(n log n) patience sorting: tails[k] = smallest possible tail of an increasing subsequence
 *   of length k+1. For each x, replace the first tail >= x (lower bound) or append.
 *   tails is NOT itself an LIS; to reconstruct one, keep the index and a parent pointer (below).
 *
 * Reductions worth recognising: Russian Doll Envelopes (LC 354: sort by w asc, h DESC, then LIS
 * on h), longest chain, minimum deletions to make sorted = n - LIS, box stacking.
 */
public class LC0300_LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int len = 0;
        for (int x : nums) {
            int i = Arrays.binarySearch(tails, 0, len, x);
            if (i < 0) {
                i = -(i + 1);                           // insertion point = lower bound
            }
            tails[i] = x;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    /** Returns one actual LIS in O(n log n). */
    public static List<Integer> findLIS(int[] nums) {
        int n = nums.length;
        int[] tailIdx = new int[n];
        int[] parent = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int lo = 0;
            int hi = len;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (nums[tailIdx[mid]] < nums[i]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            parent[i] = lo > 0 ? tailIdx[lo - 1] : -1;
            tailIdx[lo] = i;
            if (lo == len) {
                len++;
            }
        }
        List<Integer> out = new ArrayList<>();
        for (int i = len == 0 ? -1 : tailIdx[len - 1]; i >= 0; i = parent[i]) {
            out.add(0, nums[i]);
        }
        return out;
    }

    public static void main(String[] args) {
        Check.eq(lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}), 4);
        Check.eq(lengthOfLIS(new int[] {0, 1, 0, 3, 2, 3}), 4);
        Check.eq(lengthOfLIS(new int[] {7, 7, 7, 7}), 1);
        Check.eq(findLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}), Arrays.asList(2, 3, 7, 18));
        Check.eq(findLIS(new int[] {}).size(), 0);
    }
}
