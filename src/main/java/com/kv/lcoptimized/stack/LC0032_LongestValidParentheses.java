package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

/**
 * LC 32 · Longest Valid Parentheses (Hard)                [was: lc/L75_LongestValidParenthesis]
 *
 * A) Index stack with a sentinel "last unmatched" index: O(n) time, O(n) space.
 * B) Two counter passes (left-to-right, then right-to-left): O(n) time, O(1) space.
 *    L->R catches everything except runs where '(' stays in excess; R->L catches those.
 * Changed : the original pushed int[] pairs onto Stack (boxing + allocation per char).
 *           B is the answer to the usual "can you do O(1) space?" follow-up.
 */
public class LC0032_LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        int[] stack = new int[s.length() + 1];
        int top = 0;
        stack[top++] = -1;                           // base: index before current valid run
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack[top++] = i;
            } else {
                top--;
                if (top == 0) {
                    stack[top++] = i;                // unmatched ')' becomes new base
                } else {
                    best = Math.max(best, i - stack[top - 1]);
                }
            }
        }
        return best;
    }

    public static int longestValidParenthesesO1(String s) {
        int best = 0;
        int open = 0;
        int close = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                best = Math.max(best, 2 * close);
            } else if (close > open) {
                open = close = 0;
            }
        }
        open = close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                best = Math.max(best, 2 * open);
            } else if (open > close) {
                open = close = 0;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        String[] in = {"(()", ")()())", "", "(()(())", "()(()"};
        int[] out = {2, 4, 0, 6, 2};
        for (int i = 0; i < in.length; i++) {
            Check.eq(longestValidParentheses(in[i]), out[i]);
            Check.eq(longestValidParenthesesO1(in[i]), out[i]);
        }
    }
}
