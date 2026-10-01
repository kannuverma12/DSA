package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

/**
 * LC 20 · Valid Parentheses (Easy)                        [was: lc/L70_ValidParenthesis]
 *
 * Pattern : push the EXPECTED closer when an opener is seen; a closer must match the top.
 * Optimal : O(n) time, O(n) space.
 * Changed : char[] used as a stack instead of Stack<Character> (Stack is synchronized and
 *           boxes every char). The original also called map.values().contains(c), a linear
 *           scan per character, and silently skipped non-bracket chars. Odd length exits early.
 */
public class LC0020_ValidParentheses {

    public static boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }
        char[] stack = new char[s.length()];
        int top = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack[top++] = ')';
            } else if (c == '[') {
                stack[top++] = ']';
            } else if (c == '{') {
                stack[top++] = '}';
            } else if (top == 0 || stack[--top] != c) {
                return false;
            }
        }
        return top == 0;
    }

    public static void main(String[] args) {
        Check.isTrue(isValid("()[]{}"));
        Check.isTrue(isValid("{[()]}"));
        Check.isTrue(!isValid("(]"));
        Check.isTrue(!isValid("((())("));
        Check.isTrue(!isValid("]]"));
    }
}
