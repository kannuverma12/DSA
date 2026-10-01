package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 39 · Combination Sum (Medium) + LC 40 · Combination Sum II (Medium)
 *   [was: lc/DP2_CombinationSum, lc/DP3_CombinationSum2]
 *
 * Pattern : choose-from-index backtracking. LC 39 recurses with i (reuse allowed); LC 40
 *           recurses with i + 1 and skips equal siblings (i > start && a[i] == a[i-1]).
 * Pruning : sort first, then `break` as soon as a[i] > remaining (all later values are larger).
 * Changed : BUG FIX - both originals did result.add(list) without copying, so every stored
 *           combination was the same list object, emptied by backtracking. Output was
 *           [[], []] instead of [[2,2,3],[7]]. Also added sorting + break pruning (LC 39
 *           originally explored every branch until sum > target).
 *
 * Follow-up: only the COUNT is needed -> it's unbounded knapsack DP (LC 377 counts ordered
 * sequences; LC 518 counts combinations - loop order differs, and so does the answer).
 */
public class LC0039_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] a = candidates.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        dfs(a, 0, target, true, new ArrayList<>(), out);
        return out;
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] a = candidates.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        dfs(a, 0, target, false, new ArrayList<>(), out);
        return out;
    }

    private static void dfs(int[] a, int start, int remaining, boolean reuse,
                            List<Integer> path, List<List<Integer>> out) {
        if (remaining == 0) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length && a[i] <= remaining; i++) {
            if (!reuse && i > start && a[i] == a[i - 1]) {
                continue;
            }
            path.add(a[i]);
            dfs(a, reuse ? i : i + 1, remaining - a[i], reuse, path, out);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Check.eq(combinationSum(new int[] {2, 3, 6, 7}, 7), Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(7)));
        Check.eq(combinationSum(new int[] {2, 3, 5}, 8),
                Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
        Check.eq(combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8), Arrays.asList(
                Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5), Arrays.asList(1, 7), Arrays.asList(2, 6)));
    }
}
