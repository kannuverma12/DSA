package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a m x n grid filled with non-negative numbers, find a path from top left to bottom 
 *  right which minimizes the sum of all numbers along its path.
 *  Note: You can only move either down or right at any point in time.
 *  
 *  Input:
 *  [
 *      [1,3,1],
 *      [1,5,1],
 *      [4,2,1]
 *  ]
 *  Output: 7
 *  Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 *  https://www.geeksforgeeks.org/min-cost-path-dp-6/
 */
public class DP5_MinimumPathSum {

    public static void main(String[] args) {
        int[][] matrix = {{1,3,1},{1,5,1},{4,2,1}};
        
        System.out.println("MinimumPathSum DFS : "+minPathSum(matrix));
        
        
        System.out.println("MinimumPathSum DP : "+minPathSumDP(matrix));

        //use geeksforgeeks solution
        System.out.println("MinimumPathSum DP GFGs : "+minCost(matrix, matrix.length, matrix[0].length));
    }
    
    // Dynamic Programming - accepted in leetcode
    public static int minPathSumDP(int[][] grid) {
        if(grid == null || grid.length==0)
            return 0;
     
        int m = grid.length;
        int n = grid[0].length;
     
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];    
     
        // initialize top row
        for(int i=1; i<n; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
     
        // initialize left column
        for(int j=1; j<m; j++){
            dp[j][0] = dp[j-1][0] + grid[j][0];
        }
     
        // fill up the dp table
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(dp[i-1][j] > dp[i][j-1]){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else{
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
            }
        }
     
        return dp[m-1][n-1];
    }
    
    /*
     * A native solution would be depth-first search. It's time is too expensive and fails the online judgement.
     */
    // not accepted
    public static int minPathSum(int[][] grid) {
        return dfs(0, 0, grid);
    }

    public static int dfs(int i, int j, int[][] grid) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        if (i < grid.length - 1 && j < grid[0].length - 1) {
            int r1 = grid[i][j] + dfs(i + 1, j, grid);
            int r2 = grid[i][j] + dfs(i, j + 1, grid);
            return Math.min(r1, r2);
        }

        if (i < grid.length - 1) {
            return grid[i][j] + dfs(i + 1, j, grid);
        }

        if (j < grid[0].length - 1) {
            return grid[i][j] + dfs(i, j + 1, grid);
        }

        return 0;
    }

    /*
    1) Optimal Substructure
        The path to reach (m, n) must be through one of the 3 cells: (m-1, n-1) or (m-1, n) or (m, n-1).
        So minimum cost to reach (m, n) can be written as “minimum of the 3 cells plus cost[m][n]”.
        minCost(m, n) = min (minCost(m-1, n-1), minCost(m-1, n), minCost(m, n-1)) + cost[m][n]

    2) Overlapping Subproblems
        Following is simple recursive implementation of the MCP (Minimum Cost Path) problem. The
        implementation simply follows the recursive structure mentioned above.
     */

    //geeksforgeeks solution
    static int minCost(int cost[][], int m, int n) {
        if (n < 0 || m < 0)
            return Integer.MAX_VALUE;
        else if (m == 0 && n == 0)
            return cost[m][n];
        else
            return cost[m][n] +
                    min( minCost(cost, m-1, n-1),
                            minCost(cost, m-1, n),
                            minCost(cost, m, n-1) );
    }

    static int min(int x, int y, int z)
    {
        if (x < y)
            return (x < z) ? x : z;
        else
            return (y < z) ? y : z;
    }

    //DP geeksforgeeks
    private static int minCostDP(int cost[][], int m, int n)
    {
        int i, j;
        int tc[][] = new int[m + 1][n + 1];

        tc[0][0] = cost[0][0];

        /* Initialize first column of total cost(tc) array */
        for (i = 1; i <= m; i++)
            tc[i][0] = tc[i - 1][0] + cost[i][0];

        /* Initialize first row of tc array */
        for (j = 1; j <= n; j++)
            tc[0][j] = tc[0][j - 1] + cost[0][j];

        /* Construct rest of the tc array */
        for (i = 1; i <= m; i++)
            for (j = 1; j <= n; j++)
                tc[i][j] = min(tc[i - 1][j - 1],
                        tc[i - 1][j],
                        tc[i][j - 1]) + cost[i][j];

        return tc[m][n];
    }



}
