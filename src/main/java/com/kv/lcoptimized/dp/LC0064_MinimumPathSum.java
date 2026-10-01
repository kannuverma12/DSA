package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 64 · Minimum Path Sum (Medium)                       [was: lc/DP5_MinimumPathSum]
 *
 * Pattern : dp[c] = grid[r][c] + min(dp[c] (from above), dp[c-1] (from left)), one row.
 * Optimal : O(mn) time, O(n) space (or O(1) by writing into grid, if mutation is allowed).
 * Changed : the original main() called a GeeksforGeeks recursion that ALSO allows diagonal
 *           moves (a different problem) and is exponential. There was also an exponential DFS
 *           and an m x n table. Kept one 1-row DP for right/down; the diagonal variant is below,
 *           clearly labelled.
 */
public class LC0064_MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        int n = grid[0].length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] row : grid) {
            dp[0] += row[0];
            for (int c = 1; c < n; c++) {
                dp[c] = row[c] + Math.min(dp[c], dp[c - 1]);
            }
        }
        return dp[n - 1];
    }

    /** GfG "Min Cost Path": moves right, down AND diagonally down-right. */
    public static int minCostPathWithDiagonal(int[][] cost) {
        int n = cost[0].length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        for (int r = 0; r < cost.length; r++) {
            for (int c = 0; c < n; c++) {
                int best;
                if (r == 0 && c == 0) {
                    best = 0;
                } else if (r == 0) {
                    best = dp[c - 1];
                } else if (c == 0) {
                    best = prev[c];
                } else {
                    best = Math.min(prev[c - 1], Math.min(prev[c], dp[c - 1]));
                }
                dp[c] = cost[r][c] + best;
            }
            int[] t = prev;
            prev = dp;
            dp = t;
        }
        return prev[n - 1];
    }

    public static void main(String[] args) {
        Check.eq(minPathSum(new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}), 7);
        Check.eq(minPathSum(new int[][] {{1, 2, 3}, {4, 5, 6}}), 12);
        Check.eq(minCostPathWithDiagonal(new int[][] {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}}), 8);
    }
}
