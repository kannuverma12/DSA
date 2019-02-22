package com.kv.fb;

/**
 * 
 * @author karanverma
 *
 *  Given 2 strings representing very large numbers (these are not representable as a BigInteger or other 
 *  various type) write a method for adding the two numbers and returning their sum.
 */
public class AddTwoVeryLargeNumbers {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Sum Strings "+ sumStrings("124","123"));

    }
    
    public static String sumStrings(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;

        int carry = 0;
        String res = "";
        while (i >= 0 || j >= 0 || carry == 1) {
            carry += i >= 0 ? (a.charAt(i--) - '0') : 0;
            carry += j >= 0 ? (b.charAt(j--) - '0') : 0;

            res = (char) (carry % 10 + '0') + res;
            carry = carry / 10;
        }
        return res;
    }
    

}
