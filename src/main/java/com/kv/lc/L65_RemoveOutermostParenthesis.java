package com.kv.lc;

import java.util.ArrayDeque;
import java.util.Deque;

public class L65_RemoveOutermostParenthesis {

    public static void main(String[] args) {
        System.out.println("After removal : "+ removeOuterParentheses("(()()())"));
    }

    private static String removeOuterParentheses(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder b = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (stack.isEmpty()) {
                if (c == '(')
                    stack.addLast('(');
            } else {
                if (c == ')' && stack.size() == 1)
                    stack.removeLast();
                else {
                    b.append(c);
                    if (c == '(')
                        stack.addLast(c);
                    else
                        stack.removeLast();
                }
            }
        }
        return b.toString();
    }
}
