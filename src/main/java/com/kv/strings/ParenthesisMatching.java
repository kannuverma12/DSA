package com.kv.strings;

/*
 *  Java Program to Check for balanced paranthesis by using Stacks
 */
 
import java.util.*;
 
public class ParenthesisMatching
{
	static boolean  ret = false;
	
	public static void main2(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        /* Creating Stack */
        Stack<Character> stk = new Stack<Character>();
        System.out.println("Parenthesis Matching Test\n");
        /* Accepting expression */
        System.out.println("Enter expression");
        String exp = scan.next();        
        int len = exp.length();
        System.out.println("\nMatches and Mismatches:\n");
        boolean ret = false;
		for (int i = 0; i < len; i++) {
			char ch = exp.charAt(i);
			if (ch == '(' || ch == '[' || ch == '{')
				stk.push(ch);
			else if (ch == ')') {
				char p = stk.peek();
				if (p == '(') {
					ret = true;
					stk.pop();
				}

			} else if (ch == ']') {
				char p = stk.peek();
				if (p == '[') {
					ret = true;
					stk.pop();
				}

			} else if (ch == '}') {
				char p = stk.peek();
				if (p == '{') {
					ret = true;
					stk.pop();
				}

			}
			// else if (ch == ')' || ch == ']' || ch == '}')
			// {
			// try
			// {
			// int p = stk.pop() + 1;
			// ret = true;
			// System.out.println(ch+" at index "+(i+1)+" matched at index "+p);
			// }
			// catch(Exception e)
			// {
			// ret = false;
			// System.out.println(ch+" at index "+(i+1)+" is unmatched");
			// }
			// }
		}
        while (!stk.isEmpty() ){
        	ret = false;
        	stk.pop();
            //System.out.println("'(' at index > "+(stk.pop() +1)+" is unmatched");
        }
        
        System.out.println("The String has balanced parenthesis ? "+ret);
        
    }
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int a0 = 0; a0 < t; a0++){
            String exp = in.next();
            Stack<Character> stk = new Stack<Character>();
            boolean ret = false;
            for (int i = 0; i < exp.length(); i++)
            {    
                char ch = exp.charAt(i);
                char p = '\u0000';
                if (ch == '(' || ch == '[' || ch == '{')
                    stk.push(ch);
                else if(ch == ')'){
                    if(!stk.empty())
                        p = stk.peek();
                    if(p != '\u0000' && p == '('){
                        ret = true;
                        stk.pop();
                    }
                }
                else if(ch == ']'){
                    if(!stk.empty())
                        p = stk.peek();
                    if(p != '\u0000' && p == '['){
                        ret = true;
                        stk.pop();
                    }

                }
                else if(ch == '}'){
                    if(!stk.empty())
                        p = stk.peek();
                    if(p != '\u0000' && p == '{'){
                        ret = true;
                        stk.pop();
                    }

                }else{
                    ret = false;
                }         
            }
            while (!stk.isEmpty() ){
                ret = false;
                stk.pop();
            }
            if(ret){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
            
        }
    }
}
