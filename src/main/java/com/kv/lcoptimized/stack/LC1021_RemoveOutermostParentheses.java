package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

/**
 * LC 1021 · Remove Outermost Parentheses (Easy)           [was: lc/L65_RemoveOutermostParenthesis]
 *
 * Pattern : depth counter replaces the stack - keep '(' when depth was > 0 before it, keep
 *           ')' when depth stays > 0 after it.
 * Optimal : O(n) time, O(1) extra (besides output).
 * Changed : the original used a Deque<Character> only to track its size; an int is enough.
 */
public class LC1021_RemoveOutermostParentheses {

    public static String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' && depth++ > 0) {
                sb.append(c);
            } else if (c == ')' && --depth > 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(removeOuterParentheses("(()())(())"), "()()()");
        Check.eq(removeOuterParentheses("(()()())"), "()()()");
        Check.eq(removeOuterParentheses("()()"), "");
    }
}
