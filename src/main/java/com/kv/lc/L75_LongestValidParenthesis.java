package com.kv.lc;

import java.util.Stack;

public class L75_LongestValidParenthesis {
    public static void main(String[] args) {
        System.out.println("longest valid parenthesis = "+longestValidParentheses("(()(())"));
    }

    public static int longestValidParentheses(String s) {
        Stack<int[]> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (!stack.isEmpty() && stack.peek()[0] == 0) {
                    stack.pop();
                    result = Math.max(result, i - (stack.isEmpty() ? -1 : stack.peek()[1]));
                } else {
                    stack.push(new int[] {1, i});
                }
            } else {
                stack.push(new int[] {0, i});
            }
        }

        return result;
    }

}
