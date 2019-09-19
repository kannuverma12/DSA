package com.kv.hr;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 * 
 * MInimumPower - grandalf
 *
 */
public class MinimumPower {

    public static void main(String[] args) {
        Integer[] arr = {-2, 3,1,-5};
        List<Integer> l = Arrays.asList(arr);
        minP(l);
    }
    
    public static int minP(List<Integer> arr) {
        int minP = 0;
        
        for(int i=arr.size()-1; i>=0;i--) {
            if((i == arr.size()-1) &&  arr.get(i) > 0)
                continue;
            minP += arr.get(i);
            System.out.println("min p = "+minP );
        }
        System.out.println("min p = "+minP );
        if(minP < 0)
            minP = Math.abs(minP) + 1;
        
        System.out.println("min p = "+minP );
        
        return minP;
        
    }

}
