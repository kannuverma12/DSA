package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

/**
 * LC 60 · Permutation Sequence (Hard)                     [was: lc/L17_PermutaionSequence]
 *
 * Pattern : factorial number system. With (n-1)! permutations per leading digit, the leading
 *           digit's index is k / (n-1)!; recurse on k % (n-1)!.
 * Optimal : O(n^2) with a list removal (n <= 9 so this is trivial); O(n log n) with a
 *           Fenwick tree "k-th unused" lookup if n were large.
 * Changed : boolean[] "used" + counting instead of ArrayList.remove (no boxing), and a
 *           StringBuilder instead of `result += ...`. The second, harder-to-verify method was
 *           removed.
 */
public class LC0060_PermutationSequence {

    public static String getPermutation(int n, int k) {
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        k--;                                            // 0-indexed
        for (int pos = n - 1; pos >= 0; pos--) {
            int idx = k / fact[pos];
            k %= fact[pos];
            for (int d = 1; d <= n; d++) {              // pick the idx-th unused digit
                if (!used[d] && idx-- == 0) {
                    used[d] = true;
                    sb.append(d);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(getPermutation(3, 3), "213");
        Check.eq(getPermutation(3, 4), "231");
        Check.eq(getPermutation(4, 9), "2314");
        Check.eq(getPermutation(1, 1), "1");
        Check.eq(getPermutation(9, 362880), "987654321");
    }
}
