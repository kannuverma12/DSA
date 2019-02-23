package com.kv.lc;


/**
 * 
 * 
 * @author karanverma
 *
 *  Given an array of numbers. Array has only one element which is alone. All other elements appear in pair. Find that one element with missing pair in O(1) extra space.
 */

/*
 * A ={1, 2, 3, 1, 3}, you can see that, the answer should be 2 as it is the only element with missing pair.
 * 1= 1, 2=10, 3=11
 * 1 ^ 1= 0
 * 2 ^ 2 = 10 ^10 = 00
 * 3 ^ 3 = 11 ^ 11= 00
 * 
 * When we XOR an element with itself, we get 0 as a result. If we XOR all the array elements, then XOR 
 * of every duplicate pair will be zero and we will be left with the only element that appears once.
 * 
 * 1 ^ 2 ^ 3 ^ 1 ^ 3 = (1 ^ 1) ^( 3 ^ 3) ^ 2 = 0 ^ 0 ^ 2 = 2
 */
public class FindNonDuplicateElementInArray {
    
    /*
     *  XOR of element with itself results value zero.
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[] arr = {1,2,3,1,3};
        
//        for(int i =0 ;i<arr.length;i++) {
//            for(int j=0; j< arr.length;j++)
//                System.out.println(arr[i] ^ arr[j]);
//        }

    }

}
