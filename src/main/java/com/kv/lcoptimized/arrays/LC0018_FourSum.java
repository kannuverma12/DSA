package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * LC 18 · 4Sum (Medium), generalised to k-Sum              [was: lc/medium/L4_FourSum]
 *
 * Pattern : sort, recurse down to 2-sum, solve 2-sum with two pointers.
 * Optimal : O(n^(k-1)) time, O(k) recursion.
 * Changed : generic kSum instead of hand-written nested loops; sums in long (the original
 *           overflows on inputs like [1e9,1e9,1e9,1e9]); min/max pruning per level.
 *
 * L6/L7 follow-ups: "what if k is a parameter?" is the usual escalation - this is that answer.
 */
public class LC0018_FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    static List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n - start < k) {
            return res;
        }
        // Prune: smallest k or largest k numbers can't reach target.
        if ((long) nums[start] * k > target || (long) nums[n - 1] * k < target) {
            return res;
        }
        if (k == 2) {
            int lo = start;
            int hi = n - 1;
            while (lo < hi) {
                long sum = (long) nums[lo] + nums[hi];
                if (sum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                    lo++;
                } else if (sum > target || (hi < n - 1 && nums[hi] == nums[hi + 1])) {
                    hi--;
                } else {
                    res.add(new LinkedList<>(Arrays.asList(nums[lo++], nums[hi--])));
                }
            }
            return res;
        }
        for (int i = start; i < n - k + 1; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            for (List<Integer> sub : kSum(nums, target - nums[i], i + 1, k - 1)) {
                ((LinkedList<Integer>) sub).addFirst(nums[i]);
                res.add(sub);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Check.eqUnordered(fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0), Arrays.asList(
                Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1)));
        Check.eq(fourSum(new int[] {1000000000, 1000000000, 1000000000, 1000000000}, -294967296).size(), 0);
        Check.eq(fourSum(new int[] {2, 2, 2, 2, 2}, 8).size(), 1);
    }
}
