package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 22 · Generate Parentheses (Medium)                   [was: lc/DP4_GenerateParenthesis]
 *
 * Pattern : backtracking with two counters. Add '(' while open < n; add ')' while close < open.
 *           Invalid prefixes are never built.
 * Optimal : O(Catalan(n) * n) = O(4^n / sqrt(n)) time, O(n) extra.
 * Changed : one shared char[] buffer instead of `s + "("`, which allocated a new String at every
 *           node. The unused BFS "method 2" was removed.
 */
public class LC0022_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> out = new ArrayList<>();
        dfs(new char[2 * n], 0, 0, 0, n, out);
        return out;
    }

    private static void dfs(char[] buf, int pos, int open, int close, int n, List<String> out) {
        if (pos == buf.length) {
            out.add(new String(buf));
            return;
        }
        if (open < n) {
            buf[pos] = '(';
            dfs(buf, pos + 1, open + 1, close, n, out);
        }
        if (close < open) {
            buf[pos] = ')';
            dfs(buf, pos + 1, open, close + 1, n, out);
        }
    }

    public static void main(String[] args) {
        Check.eq(generateParenthesis(3), Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        Check.eq(generateParenthesis(1), Arrays.asList("()"));
        Check.eq(generateParenthesis(8).size(), 1430);
    }
}
