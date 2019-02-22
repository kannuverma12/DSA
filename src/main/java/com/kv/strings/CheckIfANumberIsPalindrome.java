package com.kv.strings;

/**
 * 
 * @author karanverma
 *
 */
public class CheckIfANumberIsPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    int num = -121, reversedInteger = 0, remainder, originalInteger;
	    boolean isNeg = false;
        originalInteger = num;
        if(num<0) {
            System.out.println(originalInteger + " is not a palindrome.");
            return;
        }
            

        // reversed integer is stored in variable 
        while( num != 0 )
        {
//            System.out.println("*****");
//            System.out.println("num : "+num);
            remainder = num % 10;
//            System.out.println("remainder : "+remainder);
            reversedInteger = reversedInteger * 10 + remainder;
//            System.out.println("reversedInteger : "+reversedInteger);
            num  /= 10;
        }

        // palindrome if orignalInteger and reversedInteger are equal
        if (originalInteger == reversedInteger)
            System.out.println(originalInteger + " is a palindrome.");
        else
            System.out.println(originalInteger + " is not a palindrome.");
    
	}

}
