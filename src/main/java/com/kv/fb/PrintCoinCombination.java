package com.kv.fb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author karanverma
 * 
 * Given many coins of 3 different face values, print the combination sums of the coins up to 1000. 
 * Must be printed in order
 * 
 * eg: coins(10, 15, 55) 
 * print: 10 15 20 25 30 . . 1000
 * 
 */
public class PrintCoinCombination {

//    public static void main(String[] args) {
//        System.out.println("Print coin combinations till 1000 : " + Arrays.toString(printSums(10, 15, 25).toArray()));
//    }
    
    public static Set<Integer> printSums(int c1, int c2, int c3) {

        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        for (int sum = 1; sum <= 1000; sum++) {

            if (sums.contains(sum - c1) || sums.contains(sum - c2) || sums.contains(sum - c3)) {
                //System.out.println(sum);
                sums.add(sum);
            }
        }
        return sums;
    }

    // Function prints all combinations of numbers 1, 2, ...MAX_POINT
    // that sum up to n.
    // i is used in recursion keep track of index in arr[] where next
    // element is to be added. Initital value of i must be passed as 0
    static void printCompositions(int arr[], int n, int i)
    {
        int MAX_POINT = 3;
        if (n == 0) {
            printArray(arr, i);
        }
        else if(n > 0) {
            for (int k = 1; k <= MAX_POINT; k++) {
                arr[i]= k;
                printCompositions(arr, n-k, i+1);
            }
        }
    }

    // Utility function to print array arr[]
    static void printArray(int arr[], int m)
    {
        for (int i = 0; i < m; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }


    // Driver program
    public static void main (String[] args)
    {
        int n = 5;
        int size = 100;
        int[] arr = new int[size];
        System.out.println("Different compositions formed by 1, 2 and 3 of "+ n + " are");
        printCompositions(arr, n, 0);
    }

}
