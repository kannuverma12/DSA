package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 131 · Palindrome Partitioning (Medium) + LC 132 · Min Cuts (Hard)
 *                                                         [was: lc/hard/DP6_PalindromePartioning]
 *
 * Pattern : precompute isPal[i][j] in O(n^2) (isPal[i][j] = s[i]==s[j] && isPal[i+1][j-1]),
 *           then backtrack over cut positions with O(1) palindrome checks.
 * Complexity: O(n * 2^n) output-bound; the table removes the O(n) check per candidate.
 * Changed : the original "DP method" returned a flat list of palindromic SUBSTRINGS (a different
 *           problem, mislabelled as partitioning), and the DFS re-checked each substring in O(n).
 *           Added LC 132 (min cuts), the natural DP follow-up: O(n^2).
 */
public class LC0131_PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        boolean[][] pal = palindromeTable(s);
        List<List<String>> out = new ArrayList<>();
        dfs(s, 0, pal, new ArrayList<>(), out);
        return out;
    }

    private static void dfs(String s, int start, boolean[][] pal, List<String> path, List<List<String>> out) {
        if (start == s.length()) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (pal[start][end]) {
                path.add(s.substring(start, end + 1));
                dfs(s, end + 1, pal, path, out);
                path.remove(path.size() - 1);
            }
        }
    }

    /** LC 132: fewest cuts so every piece is a palindrome. */
    public static int minCut(String s) {
        int n = s.length();
        boolean[][] pal = palindromeTable(s);
        int[] cuts = new int[n];                       // cuts[i] = min cuts for s[0..i]
        for (int i = 0; i < n; i++) {
            cuts[i] = i;
            for (int j = 0; j <= i; j++) {
                if (pal[j][i]) {
                    cuts[i] = j == 0 ? 0 : Math.min(cuts[i], cuts[j - 1] + 1);
                }
            }
        }
        return n == 0 ? 0 : cuts[n - 1];
    }

    private static boolean[][] palindromeTable(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                pal[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || pal[i + 1][j - 1]);
            }
        }
        return pal;
    }

    public static void main(String[] args) {
        Check.eq(partition("aab"), Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b")));
        Check.eq(partition("a"), Arrays.asList(Arrays.asList("a")));
        Check.eq(minCut("aab"), 1);
        Check.eq(minCut("ab"), 1);
        Check.eq(minCut("racecar"), 0);
    }
}
