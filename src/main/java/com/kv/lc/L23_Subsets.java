package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a set of distinct integers, nums, return all possible subsets (the power set).
 *  
 *  Input: nums = [1,2,3]
 *  Output: [ [3], [1], [2], [1,2,3], [1,3], [2,3],[1,2],[]]
 *  
 *  Note: 
 *  1) Elements in a subset must be in non-descending order. 
 *  2) The solution set must not contain duplicate subsets.
 */
public class L23_Subsets {
    
    /*
     * Given a set S of n distinct integers, there is a relation between Sn and Sn-1.
     *  The subset of Sn-1 is the union of {subset of Sn-1} and {each element in Sn-1 + one more element}. 
     *  Therefore, a Java solution can be quickly formalized.
     */

    public static void main(String[] args) {
       int[] is= {1, 2, 3};

       System.out.println(Arrays.deepToString(subsets(is).toArray()));
    }
    
    public static List<List<Integer>> subsets(int[] S) {
        if (S == null)
            return null;
     
        Arrays.sort(S);
     
        List<List<Integer>> result = new ArrayList<>();
     
        for (int i = 0; i < S.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
     
            //get sets that are already in result
            for (List<Integer> a : result) {
                temp.add(new ArrayList<>(a));
            }
     
            //add S[i] to existing sets
            for (List<Integer> a : temp) {
                a.add(S[i]);
            }
     
            //add S[i] only as a set
            List<Integer> single = new ArrayList<>();
            single.add(S[i]);
            temp.add(single);
     
            result.addAll(temp);
        }
     
        //add empty set
        result.add(new ArrayList<>());
     
        return result;
    }

    //more simpler..can subset any character
    private static void printSubsets(char[] set) {
        int n = set.length;
        //System.out.println("n = "+n+" << = "+(1<<n) +" and result = " +(1 & 1));
        // Run a loop for printing all 2^n subsets one by one
        for (int i = 0; i < (1<<n); i++) {
            System.out.print("{");
            for (int j = 0; j < n; j++) {

                // (1<<j) is a number with jth bit 1 so when we 'and' them with the
                // subset number we get which numbers are present in the subset and which are not
                if ((i & (1 << j)) > 0)
                    System.out.print(set[j] + "");

            }
            System.out.println("}");


        }
    }

}
