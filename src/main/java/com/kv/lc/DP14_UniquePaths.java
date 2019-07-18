package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *  The robot can only move either down or right at any point in time. The robot is trying to 
 *  reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *  How many possible unique paths are there?
 *  
 *  Input: m = 3, n = 2 
 *  Output: 3
 *  Explanation:
 *  From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 *  1. Right -> Right -> Down
 *  2. Right -> Down -> Right
 *  3. Down -> Right -> Right
 */
public class DP14_UniquePaths {

    public static void main(String[] args) {
        int m = 3, n = 3;
        System.out.println("Unique path DFS : "+uniquePaths(m, n));
        System.out.println("Unique path DP : "+uniquePathsDp(m, n));

        System.out.println("Unique path DP Memo : "+uniquePathsDPMemo(m, n));

    }
    
    //A depth-first search solution is pretty straight-forward. However, the time of this solution 
    //is too expensive, and it didn't pass the online judge.
    public static int uniquePaths(int m, int n) {
        return dfs(0, 0, m, n);
    }

    public static int dfs(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (i < m - 1 && j < n - 1) {
            return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
        }

        if (i < m - 1) {
            return dfs(i + 1, j, m, n);
        }

        if (j < n - 1) {
            return dfs(i, j + 1, m, n);
        }

        return 0;
    }
    
    // Dynamic Programming
    public static int uniquePathsDp(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;

        int[][] dp = new int[m][n];

        // left column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // top row
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // fill up the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    // Dynamic Programming with Memorization
    public static int uniquePathsDPMemo(int m, int n) {
        int[][] mem = new int[m][n];

        // init with -1 value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mem[i][j] = -1;
            }
        }

        return helper(mem, m - 1, n - 1);
    }

    private static int helper(int[][] mem, int m, int n) {
        // edge has only one path
        if (m == 0 || n == 0) {
            mem[m][n] = 1;
            return 1;
        }

        if (mem[m][n] != -1) {
            return mem[m][n];
        }

        mem[m][n] = helper(mem, m, n - 1) + helper(mem, m - 1, n);

        return mem[m][n];
    }

}
