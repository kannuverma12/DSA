package com.kv.fb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrintCoinCombination {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Print coin combinations till 1000 : "+Arrays.toString(printSums(10, 15, 25).toArray()));

    }
    
    public static Set<Integer> printSums(int c1, int c2, int c3) {

        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        for(int sum = 1; sum <= 1000; sum++) {

            if(sums.contains(sum - c1) || sums.contains(sum - c2) || sums.contains(sum - c3)) {
                System.out.println(sum);
                sums.add(sum);
            }
        }
        return sums;
    }

}
