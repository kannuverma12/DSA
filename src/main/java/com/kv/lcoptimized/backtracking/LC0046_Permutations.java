package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 46 · Permutations (Medium) + LC 47 · Permutations II (Medium) + string permutations
 *   [was: lc/DP9_L9_Permutations, lc/DP9_Permutation2, lc/medium/StringPermutations (empty stub)]
 *
 * LC 46 : in-place swap backtracking. O(n * n!) time, O(n) extra.
 * LC 47 : sort, then "used[]" backtracking; skip nums[i] if it equals nums[i-1] and nums[i-1]
 *         is NOT used in this branch. This yields each distinct permutation exactly once, in
 *         lexicographic order, with no HashSet.
 * Changed : the originals' default main() generated 17! (about 3.5e14) permutations and would
 *           never finish. The insertion-based iterative version copied lists O(n) times per
 *           level. LC 47 method 2 deduplicated with a HashSet<List> after generating
 *           everything. The swap-based LC 47 dedupe with a per-level HashSet is correct but
 *           allocates; the sorted + used[] rule is the standard answer.
 */
public class LC0046_Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        swapPermute(nums.clone(), 0, out);
        return out;
    }

    private static void swapPermute(int[] a, int k, List<List<Integer>> out) {
        if (k == a.length) {
            List<Integer> p = new ArrayList<>(a.length);
            for (int x : a) {
                p.add(x);
            }
            out.add(p);
            return;
        }
        for (int i = k; i < a.length; i++) {
            swap(a, k, i);
            swapPermute(a, k + 1, out);
            swap(a, k, i);
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        uniqueDfs(a, new boolean[a.length], new ArrayList<>(), out);
        return out;
    }

    private static void uniqueDfs(int[] a, boolean[] used, List<Integer> path, List<List<Integer>> out) {
        if (path.size() == a.length) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (used[i] || (i > 0 && a[i] == a[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            path.add(a[i]);
            uniqueDfs(a, used, path, out);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /** All distinct permutations of a string, in sorted order (same rule as LC 47). */
    public static List<String> stringPermutations(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        List<String> out = new ArrayList<>();
        charDfs(a, new boolean[a.length], new char[a.length], 0, out);
        return out;
    }

    private static void charDfs(char[] a, boolean[] used, char[] buf, int pos, List<String> out) {
        if (pos == a.length) {
            out.add(new String(buf));
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (used[i] || (i > 0 && a[i] == a[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            buf[pos] = a[i];
            charDfs(a, used, buf, pos + 1, out);
            used[i] = false;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Check.eqUnordered(permute(new int[] {1, 2, 3}), Arrays.asList(
                Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1)));
        Check.eq(permuteUnique(new int[] {1, 1, 2}),
                Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1), Arrays.asList(2, 1, 1)));
        Check.eq(permuteUnique(new int[] {1, 1, 3, 3}).size(), 6);
        Check.eq(stringPermutations("aba"), Arrays.asList("aab", "aba", "baa"));
        Check.eq(stringPermutations("abcd").size(), 24);
    }
}
