package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 77 · Combinations (Medium)                           [was: lc/DP1_Combinations]
 *
 * Pattern : choose-from-index backtracking, pruned so enough numbers remain:
 *           i <= n - (k - path.size()) + 1.
 * Optimal : O(C(n,k) * k) time, O(k) extra.
 * Changed : BUG FIX - the original did res.add(item) without copying, so every result was
 *           the same (eventually empty) list: [[], [], [], [], [], []]. Also added the
 *           "enough numbers left" pruning bound.
 */
public class LC0077_Combinations {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> out = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), out);
        return out;
    }

    private static void dfs(int start, int n, int k, List<Integer> path, List<List<Integer>> out) {
        if (path.size() == k) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            dfs(i + 1, n, k, path, out);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Check.eq(combine(4, 2), Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
                Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4)));
        Check.eq(combine(1, 1), Arrays.asList(Arrays.asList(1)));
        Check.eq(combine(10, 5).size(), 252);
    }
}
