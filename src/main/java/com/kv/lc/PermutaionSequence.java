package com.kv.lc;

import static org.mockito.Mockito.never;

import java.util.ArrayList;

/**
 * 
 * @author karanverma
 *
 *  The set [1,2,3,…,n] contains a total of n! unique permutations.
 *  By listing and labeling all of the permutations in order,
 *  We get the following sequence (ie, for n = 3):
 *  "123", "132", "213", "231", "312", "321"
 *  Given n and k, return the kth permutation sequence. (Note: Given n will be between 1 and 9 inclusive.)
 */
public class PermutaionSequence {

    public static void main(String[] args) {
        System.out.println("Get Permutations : "+getPermutation(3, 4));
        
        System.out.println("Get Permutations 2: "+getPermutation2(3, 4));

    }
    
    public static String getPermutation(int n, int k) {

        // initialize all numbers
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            numberList.add(i);
        }

        // change k to be index
        k--;

        // set factorial of n
        int mod = 1;
        for (int i = 1; i <= n; i++) {
            mod = mod * i;
        }

        String result = "";

        // find sequence
        for (int i = 0; i < n; i++) {
            mod = mod / (n - i);
            // find the right number(curIndex) of
            int curIndex = k / mod;
            // update k
            k = k % mod;

            // get number according to curIndex
            result += numberList.get(curIndex);
            // remove from list
            numberList.remove(curIndex);
        }

        return result.toString();
    }

    public static String getPermutation2(int n, int k) {
        boolean[] output = new boolean[n];
        StringBuilder buf = new StringBuilder("");

        int[] res = new int[n];
        res[0] = 1;

        for (int i = 1; i < n; i++)
            res[i] = res[i - 1] * i;

        for (int i = n - 1; i >= 0; i--) {
            int s = 1;

            while (k > res[i]) {
                s++;
                k = k - res[i];
            }

            for (int j = 0; j < n; j++) {
                if (j + 1 <= s && output[j]) {
                    s++;
                }
            }

            output[s - 1] = true;
            buf.append(Integer.toString(s));
        }

        return buf.toString();
    }

}
