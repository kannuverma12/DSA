package com.kv.lc;

import java.util.Stack;

/**
 * 
 * @author karanverma
 *
 *  Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *  Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *  Note:
 *  Division between two integers should truncate toward zero.
 *  The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 *  
 *  Input: ["2", "1", "+", "3", "*"]
 *  Output: 9
 *  Explanation: ((2 + 1) * 3) = 9
 */
public class EvaluateReverseolishNotation {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] tokens = {"2", "1", "+","3","*"};
        
        System.out.println(evalRPN(tokens));

    }
    
    public static int evalRPN(String[] tokens) {
        
        int returnValue = 0;
 
        String operators = "+-*/";
 
        Stack<String> stack = new Stack<String>();
 
        for(String t : tokens){
            if(!operators.contains(t)){
                stack.push(t);
            }else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int index = operators.indexOf(t);
                switch(index){
                    case 0:
                        stack.push(String.valueOf(a+b));
                        break;
                    case 1:
                        stack.push(String.valueOf(b-a));
                        break;
                    case 2:
                        stack.push(String.valueOf(a*b));
                        break;
                    case 3:
                        stack.push(String.valueOf(b/a));
                        break;
                }
            }
        }
 
        returnValue = Integer.valueOf(stack.pop());
 
        return returnValue;
 
    }

}
