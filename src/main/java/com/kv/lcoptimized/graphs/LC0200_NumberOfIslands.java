package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 200 · Number of Islands (Medium) + LC 305 · Number of Islands II (Hard)   [NEW]
 *
 * LC 200 : flood fill from each unvisited '1'. O(mn). Iterative stack avoids overflow on
 *          big grids (a recursive DFS on a 1000x1000 all-land grid will StackOverflow in Java).
 * LC 305 : land is added cell by cell; report island count after each addition. Re-running
 *          BFS each time is O(k * mn). Union-Find makes each addition O(alpha) -> O(k + mn).
 *          This "static -> online" escalation is typical of Google interviews.
 */
public class LC0200_NumberOfIslands {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] stack = new int[m * n];
        int islands = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != '1') {
                    continue;
                }
                islands++;
                int top = 0;
                grid[r][c] = '0';
                stack[top++] = r * n + c;
                while (top > 0) {
                    int cell = stack[--top];
                    for (int[] d : DIRS) {
                        int nr = cell / n + d[0];
                        int nc = cell % n + d[1];
                        if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == '1') {
                            grid[nr][nc] = '0';
                            stack[top++] = nr * n + nc;
                        }
                    }
                }
            }
        }
        return islands;
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n);
        boolean[] land = new boolean[m * n];
        int islands = 0;
        List<Integer> out = new ArrayList<>();
        for (int[] p : positions) {
            int id = p[0] * n + p[1];
            if (!land[id]) {
                land[id] = true;
                islands++;
                for (int[] d : DIRS) {
                    int nr = p[0] + d[0];
                    int nc = p[1] + d[1];
                    int nid = nr * n + nc;
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && land[nid] && uf.union(id, nid)) {
                        islands--;
                    }
                }
            }
            out.add(islands);
        }
        return out;
    }

    public static void main(String[] args) {
        char[][] g = {"11110".toCharArray(), "11010".toCharArray(), "11000".toCharArray(), "00000".toCharArray()};
        Check.eq(numIslands(g), 1);
        char[][] g2 = {"11000".toCharArray(), "11000".toCharArray(), "00100".toCharArray(), "00011".toCharArray()};
        Check.eq(numIslands(g2), 3);
        Check.eq(numIslands2(3, 3, new int[][] {{0, 0}, {0, 1}, {1, 2}, {2, 1}, {1, 1}, {1, 1}}),
                Arrays.asList(1, 1, 2, 3, 1, 1));
    }
}
