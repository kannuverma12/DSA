package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 78 · Subsets (Medium) + LC 90 · Subsets II (Medium)
 *   [was: lc/medium/L23_Subsets, lc/medium/L24_Subsets2, lc/FindAllSubsetOfGivenSet]
 *
 * LC 78 : cascading - for each x, copy every existing subset and append x. O(n * 2^n).
 *         Bitmask variant: subset i contains element j iff bit j of i is set (good for n <= 20
 *         and for "iterate all subsets of a mask" DP tricks).
 * LC 90 : sort; for a duplicate x, extend ONLY the subsets created in the previous round
 *         (otherwise you'd recreate them).
 * Changed : L24 built subsets back-to-front with add(0, x) (O(n) per insert) and extra copies;
 *           FindAllSubsetOfGivenSet had its whole body commented out.
 */
public class LC0078_Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        out.add(new ArrayList<>());
        for (int x : nums) {
            for (int i = 0, size = out.size(); i < size; i++) {
                List<Integer> s = new ArrayList<>(out.get(i));
                s.add(x);
                out.add(s);
            }
        }
        return out;
    }

    public static List<List<Integer>> subsetsBitmask(int[] nums) {
        int n = nums.length;
        List<List<Integer>> out = new ArrayList<>(1 << n);
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> s = new ArrayList<>(Integer.bitCount(mask));
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    s.add(nums[j]);
                }
            }
            out.add(s);
        }
        return out;
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        out.add(new ArrayList<>());
        int prevRoundStart = 0;
        for (int i = 0; i < a.length; i++) {
            int size = out.size();
            int from = (i > 0 && a[i] == a[i - 1]) ? prevRoundStart : 0;
            for (int j = from; j < size; j++) {
                List<Integer> s = new ArrayList<>(out.get(j));
                s.add(a[i]);
                out.add(s);
            }
            prevRoundStart = size;
        }
        return out;
    }

    public static void main(String[] args) {
        List<List<Integer>> expected = Arrays.asList(Arrays.<Integer>asList(), Arrays.asList(1), Arrays.asList(2),
                Arrays.asList(1, 2), Arrays.asList(3), Arrays.asList(1, 3), Arrays.asList(2, 3), Arrays.asList(1, 2, 3));
        Check.eq(subsets(new int[] {1, 2, 3}), expected);
        Check.eq(subsetsBitmask(new int[] {1, 2, 3}), expected);
        Check.eqUnordered(subsetsWithDup(new int[] {1, 2, 2}), Arrays.asList(Arrays.<Integer>asList(),
                Arrays.asList(1), Arrays.asList(1, 2), Arrays.asList(1, 2, 2), Arrays.asList(2), Arrays.asList(2, 2)));
        Check.eq(subsetsWithDup(new int[] {4, 4, 4, 1, 4}).size(), 10);
    }
}
