package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

/**
 * LC 329 · Longest Increasing Path in a Matrix (Hard)     [NEW - DFS + memo on an implicit DAG]
 *
 * Strictly increasing moves can't cycle, so the grid is a DAG. memo[r][c] = longest path
 * starting at (r, c) = 1 + max over bigger neighbours. Each cell is solved once.
 * Optimal : O(mn) time and space.
 * Alternative without recursion (safe for huge grids): Kahn's topological sort, peeling
 * "sinks" layer by layer; the number of layers is the answer.
 *
 * Recognising "this is DP on a DAG" is the key step here, and it is what gets evaluated.
 */
public class LC0329_LongestIncreasingPathInMatrix {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int best = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                best = Math.max(best, dfs(matrix, r, c, memo));
            }
        }
        return best;
    }

    private static int dfs(int[][] a, int r, int c, int[][] memo) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        int best = 1;
        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < a.length && nc < a[0].length && a[nr][nc] > a[r][c]) {
                best = Math.max(best, 1 + dfs(a, nr, nc, memo));
            }
        }
        memo[r][c] = best;
        return best;
    }

    public static void main(String[] args) {
        Check.eq(longestIncreasingPath(new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}), 4);
        Check.eq(longestIncreasingPath(new int[][] {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}), 4);
        Check.eq(longestIncreasingPath(new int[][] {{1}}), 1);
    }
}
