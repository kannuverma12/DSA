package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

/**
 * LC 79 · Word Search (Medium)                            [was: lc/medium/L51_WordSearch]
 *
 * Pattern : DFS from each cell, marking the path in place ('#') and restoring on the way back.
 * Complexity: O(m * n * 3^L) worst case, O(L) recursion.
 * Changed : BUG FIX - the original returned true BEFORE restoring the cell, leaving '#' marks
 *           in the caller's board. It also kept scanning every start cell after a match.
 *           Added pruning: reject early if the board lacks enough of some letter, and start
 *           from whichever end of the word is rarer on the board (big win on "aaaa...ab").
 *
 * L6/L7 follow-up: many words (LC 212 Word Search II) -> build a trie of the words, DFS once
 * over the board, and prune trie leaves as words are found.
 */
public class LC0079_WordSearch {

    public static boolean exist(char[][] board, String word) {
        int[] boardCount = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                boardCount[c]++;
            }
        }
        int[] wordCount = new int[128];
        for (char c : word.toCharArray()) {
            if (++wordCount[c] > boardCount[c]) {
                return false;
            }
        }
        char[] w = word.toCharArray();
        if (boardCount[w[0]] > boardCount[w[w.length - 1]]) {
            w = new StringBuilder(word).reverse().toString().toCharArray();
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, w, 0, r, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] b, char[] w, int k, int r, int c) {
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] != w[k]) {
            return false;
        }
        if (k == w.length - 1) {
            return true;
        }
        char saved = b[r][c];
        b[r][c] = '#';
        boolean found = dfs(b, w, k + 1, r + 1, c) || dfs(b, w, k + 1, r - 1, c)
                || dfs(b, w, k + 1, r, c + 1) || dfs(b, w, k + 1, r, c - 1);
        b[r][c] = saved;
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {"ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray()};
        Check.isTrue(exist(board, "ABCCED"));
        Check.isTrue(exist(board, "SEE"));
        Check.isTrue(!exist(board, "ABCB"));
        Check.eq(new String(board[0]) + new String(board[1]) + new String(board[2]), "ABCESFCSADEE");  // untouched
    }
}
