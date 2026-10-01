package com.kv.lcoptimized.graphs;

import com.kv.lcoptimized.common.Check;

/**
 * LC 130 · Surrounded Regions (Medium)                    [was: lc/L47_SurroundedRegions]
 *
 * Pattern : reverse the question. Flood-fill from every border 'O' and mark it safe; then flip
 *           the remaining 'O' to 'X' and restore the safe ones.
 * Optimal : O(mn) time. The flood fill uses an explicit int[] stack of encoded cells (r * n + c),
 *           so a 1000x1000 board of 'O' can't overflow the call stack.
 * Changed : the original was correct; it used LinkedList<Integer> (boxing) and four copies of
 *           the neighbour check. A direction array replaces the copies.
 */
public class LC0130_SurroundedRegions {

    private static final int[] DR = {1, -1, 0, 0};
    private static final int[] DC = {0, 0, 1, -1};

    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] stack = new int[m * n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                boolean border = r == 0 || c == 0 || r == m - 1 || c == n - 1;
                if (border && board[r][c] == 'O') {
                    int top = 0;
                    board[r][c] = 'S';
                    stack[top++] = r * n + c;
                    while (top > 0) {
                        int cell = stack[--top];
                        for (int d = 0; d < 4; d++) {
                            int nr = cell / n + DR[d];
                            int nc = cell % n + DC[d];
                            if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'O') {
                                board[nr][nc] = 'S';
                                stack[top++] = nr * n + nc;
                            }
                        }
                    }
                }
            }
        }
        for (char[] row : board) {
            for (int c = 0; c < n; c++) {
                row[c] = row[c] == 'S' ? 'O' : 'X';
            }
        }
    }

    public static void main(String[] args) {
        char[][] b = {"XXXX".toCharArray(), "XOOX".toCharArray(), "XXOX".toCharArray(), "XOXX".toCharArray()};
        solve(b);
        Check.eq(b, new char[][] {"XXXX".toCharArray(), "XXXX".toCharArray(), "XXXX".toCharArray(), "XOXX".toCharArray()});
        char[][] b2 = {"OO".toCharArray(), "OO".toCharArray()};
        solve(b2);
        Check.eq(b2, new char[][] {"OO".toCharArray(), "OO".toCharArray()});
    }
}
