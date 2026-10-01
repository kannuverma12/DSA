package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

/**
 * LC 150 · Evaluate Reverse Polish Notation (Medium)      [was: lc/EvaluateReversePolishNotation]
 *
 * Pattern : operand stack; an operator pops b then a and pushes a op b.
 * Optimal : O(n) time, O(n) space.
 * Changed : int[] stack instead of Stack<String>. The original converted every intermediate
 *           result back to a String and re-parsed it, and matched operators with
 *           "+-*\/".contains(token).
 *
 * L6/L7 follow-ups: infix evaluation with precedence and parentheses (Basic Calculator
 * LC 224/227/772) - shunting-yard, or recursive descent with one function per precedence level.
 */
public class LC0150_EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = 0;
        for (String t : tokens) {
            if (t.length() == 1 && "+-*/".indexOf(t.charAt(0)) >= 0) {
                int b = stack[--top];
                int a = stack[--top];
                switch (t.charAt(0)) {
                    case '+': stack[top++] = a + b; break;
                    case '-': stack[top++] = a - b; break;
                    case '*': stack[top++] = a * b; break;
                    default:  stack[top++] = a / b; break;   // Java truncates toward zero, as required
                }
            } else {
                stack[top++] = Integer.parseInt(t);
            }
        }
        return stack[0];
    }

    public static void main(String[] args) {
        Check.eq(evalRPN(new String[] {"2", "1", "+", "3", "*"}), 9);
        Check.eq(evalRPN(new String[] {"4", "13", "5", "/", "+"}), 6);
        Check.eq(evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}), 22);
    }
}
