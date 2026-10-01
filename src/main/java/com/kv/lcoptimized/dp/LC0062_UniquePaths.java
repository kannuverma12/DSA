package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 62 · Unique Paths (Medium) + LC 63 · Unique Paths II (Medium)
 *   [was: lc/DP14_UniquePaths, lc/DP15_UniquePaths2]
 *
 * DP     : paths[c] += paths[c-1] row by row - O(mn) time, O(n) space (1 row).
 * Math   : C(m+n-2, m-1), computed incrementally with exact integer division - O(min(m,n)).
 * LC 63  : same 1-row DP; an obstacle zeroes its cell. No separate first row/col init loops.
 * Changed : originals used a full m x n table, plus an exponential DFS and a -1-filled memo.
 *           Progression to narrate: brute force -> memo -> table -> one row -> closed form.
 */
public class LC0062_UniquePaths {

    public static int uniquePaths(int m, int n) {
        int[] row = new int[n];
        java.util.Arrays.fill(row, 1);
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                row[c] += row[c - 1];
            }
        }
        return row[n - 1];
    }

    public static long uniquePathsMath(int m, int n) {
        int k = Math.min(m, n) - 1;
        int total = m + n - 2;
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (total - k + i) / i;           // stays an integer at every step
        }
        return res;
    }

    public static int uniquePathsWithObstacles(int[][] grid) {
        int n = grid[0].length;
        int[] row = new int[n];
        row[0] = 1;
        for (int[] cells : grid) {
            for (int c = 0; c < n; c++) {
                if (cells[c] == 1) {
                    row[c] = 0;
                } else if (c > 0) {
                    row[c] += row[c - 1];
                }
            }
        }
        return row[n - 1];
    }

    public static void main(String[] args) {
        Check.eq(uniquePaths(3, 7), 28);
        Check.eq(uniquePaths(3, 2), 3);
        Check.eq(uniquePathsMath(3, 7), 28L);
        Check.eq(uniquePathsMath(23, 12), 193536720L);
        Check.eq(uniquePathsWithObstacles(new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}), 2);
        Check.eq(uniquePathsWithObstacles(new int[][] {{0, 1}, {0, 0}}), 1);
        Check.eq(uniquePathsWithObstacles(new int[][] {{1}}), 0);
    }
}
