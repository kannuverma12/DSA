package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 96 · Unique Binary Search Trees (Medium)             [was: lc/DP12_UniqueBinarySearchTrees]
 *
 * Pattern : Catalan recurrence G(n) = sum_{i=1..n} G(i-1) * G(n-i).  O(n^2).
 * Closed form: C(n+1) = C(n) * 2(2n+1) / (n+2).  O(n), exact in long for n <= 33.
 * Changed : the original set count[1] = 1 unconditionally, so n = 0 threw
 *           ArrayIndexOutOfBounds. Added the O(n) closed form.
 */
public class LC0096_UniqueBST {

    public static int numTrees(int n) {
        long[] g = new long[n + 1];
        g[0] = 1;
        for (int nodes = 1; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                g[nodes] += g[root - 1] * g[nodes - root];
            }
        }
        return (int) g[n];
    }

    public static long catalan(int n) {
        long c = 1;
        for (int i = 0; i < n; i++) {
            c = c * 2 * (2 * i + 1) / (i + 2);
        }
        return c;
    }

    public static void main(String[] args) {
        Check.eq(numTrees(3), 5);
        Check.eq(numTrees(0), 1);
        Check.eq(numTrees(19), 1767263190);
        Check.eq(catalan(19), 1767263190L);
    }
}
