package com.kv.fb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateExpression {
    private static abstract class Node {
        Node left;
        Node right;
    }
    private static class Operator extends Node {
        char op;
        Operator (char op) {
            super();
            this.op = op;
        }
    }
    private static class Operand extends Node {
        int value;
        Operand(int value) {
            super();
            this.value = value;
        }
    }

    private final Map<Character, Integer> idToValue;

    //precedence: usual (* and / higher than + and -)
    //associativity: usual (left to right)
    // assume: operands are single letters
    // assume: integer arithmatic (no floating points)
    // assume: expression contains operands, operators and spaces only.
    public EvaluateExpression(List<String> assignments) {
        this.idToValue = new HashMap<>();
        for (String assignment: assignments) {
            assignment = assignment.replaceAll(" ", "");
            String[] parts = assignment.split("=");
            char id = parts[0].charAt(0);
            int value = Integer.parseInt(parts[1]);
            idToValue.put(id, value);
        }
    }

    private static int skipSpaces(String expression, int i) {
        for (; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c != ' ') {
                break;
            }
        }

        return i;
    }

    public int parseExpression(String expression) {
        //represent the expression as a list of operands and operators
        Node head = null;
        Node tail = null;
        boolean expectOperand = true;
        for (int i = skipSpaces(expression, 0); i < expression.length(); i++, i = skipSpaces(expression, i)) {
            char c = expression.charAt(i);
            if (expectOperand) {
                if (!idToValue.containsKey(c)) {
                    throw new IllegalArgumentException();
                }
                int value = idToValue.get(c);
                Node operand = new Operand(value);
                if (head == null) {
                    head = operand;
                    tail = operand;
                } else {
                    operand.left = tail;
                    tail.right = operand;
                    tail = operand;
                }
                expectOperand = false;
            } else {
                if ((c != '+') && (c != '-') && (c != '*') && (c != '/')) {
                    throw new IllegalArgumentException();
                }
                Node operator = new Operator(c);
                operator.left = tail;
                tail.right = operator;
                tail = operator;
                expectOperand = true;
            }
        }

        if (expectOperand) {
            throw new IllegalArgumentException("compilation error");
        }

        //evaluate all * and /
        Set<Character> operatorsToEvaluate = new HashSet<>();
        operatorsToEvaluate.add('/');
        operatorsToEvaluate.add('*');
        head = evaluateLeftToRight(head, operatorsToEvaluate);

        //evaluate all + and -
        operatorsToEvaluate.remove('/');
        operatorsToEvaluate.remove('*');
        operatorsToEvaluate.add('+');
        operatorsToEvaluate.add('-');
        head = evaluateLeftToRight(head, operatorsToEvaluate);

        return ((Operand)head).value;
    }

    private int evaluate(Operator operator) {
        int operand1 = ((Operand)operator.left).value;
        int operand2 = ((Operand)operator.right).value;
        char op = operator.op;
        int value;
        switch(op) {
            case '+': value = operand1 + operand2; break;
            case '-': value = operand1 - operand2; break;
            case '*': value = operand1 * operand2; break;
            case '/': value = operand1 / operand2; break;
            default:
                throw new IllegalArgumentException();
        }

        return value;
    }

    private Node evaluateLeftToRight(Node head, Set<Character> operatorsToEvaluate) {
        for (Node node = head; node != null; node = node.right) {
            if (!(node instanceof Operator)) {
                continue;
            }
            Operator operator = (Operator)node;
            if (!operatorsToEvaluate.contains(operator.op)) {
                continue;
            }
            int value = evaluate(operator);
            Operand operand = new Operand(value);
            operand.left = operator.left.left;
            operand.right = operator.right.right;
            if (operator.left.left != null) {
                operator.left.left.right = operand;
            } else {
                head = operand;
            }
            if (operator.right.right != null) {
                operator.right.right.left = operand;
            }
            node = operand;
        }

        return head;
    }

    private static void test(EvaluateExpression evaluator, String expression, int expected) {
        int actual = evaluator.parseExpression(expression);
        assert (actual == expected);
    }

    private static void negativeTest(EvaluateExpression evaluator, String expression) {
        try {
            evaluator.parseExpression(expression);
        } catch (IllegalArgumentException e) {
            return; //success
        }

        assert (false); //fail
    }

    private static void test() {
        List<String> assignments = Arrays.asList("a = 3", "b=8", " c =5", "d = 6 ");
        EvaluateExpression evaluator = new EvaluateExpression(assignments);

        test(evaluator, "a + b + c", 16);
        test(evaluator, "a + b - c", 6);
        test(evaluator, "b - a * c", -7);
        test(evaluator, "d / a + c - b", -1);
        test(evaluator, "a + b * c - d", 37);
        test(evaluator, "a*b*c - d", 114);
        test(evaluator, "b", 8);
        test(evaluator, "b + b", 16);
        test(evaluator, "b / b", 1);
        test(evaluator, "b / b / b", 0);
        test(evaluator, "b / a / b", 0);
        test(evaluator, "b / a + d", 8);
        test(evaluator, "c-a", 2);
        test(evaluator, "c-a ", 2);
        test(evaluator, "c- a ", 2);
        test(evaluator, "c -a ", 2);
        test(evaluator, " c-a", 2);
        test(evaluator, " c -   a", 2);

        negativeTest(evaluator, "");
        negativeTest(evaluator, " ");
        negativeTest(evaluator, " x ");
        negativeTest(evaluator, "a + * b");
        negativeTest(evaluator, "a + x");
        negativeTest(evaluator, "a + b * c /");
        negativeTest(evaluator, "-a + b * c");

        EvaluateExpression evaluator2 = new EvaluateExpression(Arrays.asList("a=1", "b=2", "c=2"));
        test(evaluator2, "a*b + a*c + b*c", 8);
        test(evaluator2, "a*c - b/c + c*c", 5);

        EvaluateExpression evaluator3 = new EvaluateExpression(Arrays.asList("g=2", "p=3", "t=1", "w=2"));
        test(evaluator3, "g + p*t - w*p", -1);
        test(evaluator3, "t - g + t - w", -2);
        negativeTest(evaluator3, "e + t*t -m");
    }

    public static void main(String[] args) {
        test();
        
        List<String> assignments = Arrays.asList("a = 3", "b=8", " c =5", "d = 6 ");
        EvaluateExpression evaluator = new EvaluateExpression(assignments);
        
        System.out.println("val = "+evaluator.parseExpression("a+b-c"));
    }
}
