package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 36 · Valid Sudoku (Medium)                           [was: lc/ValidSuduko]
 *
 * Pattern : single pass with 27 bitmasks (9 rows, 9 cols, 9 boxes).
 * Optimal : O(81) time, O(27) ints.
 * Changed : one pass instead of three; bitmasks instead of allocating 27 boolean arrays.
 *           Box index = (r / 3) * 3 + c / 3.
 *
 * L6/L7 follow-up: Sudoku Solver (LC 37) - backtracking with these same masks, and pick the
 * most-constrained empty cell first (fewest candidates, via Integer.bitCount).
 */
public class LC0036_ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    continue;
                }
                int bit = 1 << (board[r][c] - '1');
                int b = (r / 3) * 3 + c / 3;
                if ((rows[r] & bit) != 0 || (cols[c] & bit) != 0 || (boxes[b] & bit) != 0) {
                    return false;
                }
                rows[r] |= bit;
                cols[c] |= bit;
                boxes[b] |= bit;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] rows = {
            "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1",
            "7...2...6", ".6....28.", "...419..5", "....8..79"};
        char[][] board = new char[9][];
        for (int i = 0; i < 9; i++) {
            board[i] = rows[i].toCharArray();
        }
        Check.isTrue(isValidSudoku(board));
        board[0][0] = '8';
        Check.isTrue(!isValidSudoku(board));
    }
}
