package com.kv.algos;

import java.math.BigInteger;
import java.util.*;

public class BigIntegerSum {



    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        BigInteger sum = BigInteger.ZERO;
        BigInteger arr[] = new BigInteger[n];
        for(int i=0 ; i<n; i++){
            arr[i] = in.nextBigInteger();
        }
        for(BigInteger bi : arr){
        	sum = sum.add(bi);
        }
        System.out.println(sum);
    }
}