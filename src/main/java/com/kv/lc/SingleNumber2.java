package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given a non-empty array of integers, every element appears three times except for one, which 
 *  appears exactly once. Find that single one.
 *  Note:
 *  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *  Example 1:
 *      Input: [2,2,3,2]
 *      Output: 3
 */
public class SingleNumber2 {

    public static void main(String[] args) {
        int[] arr = {2,2,3,2};
        System.out.println("Single number : "+singleNumber(arr));
        //System.out.println("Single number  2: "+singleNumberOthersNumberTwice(arr));
    }
    
    // important trick
    public static int singleNumber(int[] A) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
    
    /*
     * SingleNumber Problem
     * 
     * Given an array of integers, every element appears twice except for one. Find that single one.
     * 
     * The key to solve this problem is bit manipulation. XOR will return 1 only on two different bits. 
     * So if two numbers are the same, XOR will return 0. Finally only one number left.
     * 
     */
    
    public static int singleNumberOthersNumberTwice(int[] A) {
        int x = 0;
        for (int a : A) {
            x = x ^ a;
        }
        return x;
    }

}
