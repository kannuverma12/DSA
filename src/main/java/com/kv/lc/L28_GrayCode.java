package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  The gray code is a binary numeral system where two successive values differ in only one bit.
 *  
 *  Given a non-negative integer n representing the total number of bits in the code, 
 *  print the sequence of gray code. A gray code sequence must begin with 0.
 *  
 *  A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 *  Therefore, for n = 0 the gray code sequence is [0].
 *  
 *  Input: 2 Output: [0,1,3,2]
 *  Explanation: 
 *  00 - 0
 *  01 - 1
 *  11 - 3
 *  10 - 2
 */
public class L28_GrayCode {

    public static void main(String[] args) {
        int n = 2;
        System.out.println("Gray code for n = "+n+" is :");
        System.out.println(Arrays.toString(grayCode(n).toArray()));
    }
    
    public static List<Integer> grayCode(int n) {
        if(n==0){
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }
     
        List<Integer> result = grayCode(n-1);
        int numToAdd = 1<<(n-1);
     
        for(int i=result.size()-1; i>=0; i--){
            result.add(numToAdd+result.get(i));
        }
     
        return result;
    }
    

}
