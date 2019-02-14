package com.kv.fb;

import java.util.Arrays;

/**
 * 
 * @author karanverma
 * 
 * "given an array representing a non-negative integer (ex: 123 represented as [1,2,3]), return the next integer 
 * (output: [1,2,4]). Run through all edge cases (ex: [9,9,9,9,9,9,9,9] etc)" 
 * 
 */
public class GetNextIntegerRepresentedInArray {

    public static void main(String[] args) {
        System.out.print("Next Integer: " + Arrays.toString(nextInteger(new int[]{1, 2, 9})));
    }
    
    private static int[] nextInteger(int[] input) {
        if (input.length == 0) {
            return input;
        }
        int sum;
        int carry = 1;
        for (int i = input.length - 1; i >= 0 && carry != 0; i--) {
            sum = input[i] + carry;
            input[i] = sum > 9 ? sum - 10 : sum;
            carry = sum > 9 ? 1 : 0;
            
            //System.out.println("i = "+i+", sum = "+sum+", input[i] = "+input[i]+", carry = "+carry);
        }
        if (carry == 0) {
            return input;
        }
        int[] result = new int[input.length + 1];
        result[0] = carry;
        System.arraycopy(input, 0, result, 1, input.length);
        return result;
    }

}
