package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *  Note: The solution set must not contain duplicate subsets.
 */
public class L24_Subsets2 {

    public static void main(String[] args) {

    }
    
    public List<List<Integer>> subsetsWithDup(int[] num) {
         if (num == null)
            return null;
     
        Arrays.sort(num);
     
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> prev = new ArrayList<>();
     
        for (int i = num.length-1; i >= 0; i--) {
     
            //get existing sets
            if (i == num.length - 1 || num[i] != num[i + 1] || prev.size() == 0) {
                prev = new ArrayList<>();
                for (int j = 0; j < result.size(); j++) {
                    prev.add(new ArrayList<>(result.get(j)));
                }
            }
     
            //add current number to each element of the set
            for (List<Integer> temp : prev) {
                temp.add(0, num[i]);
            }
     
            //add each single number as a set, only if current element is different with previous
            if (i == num.length - 1 || num[i] != num[i + 1]) {
                List<Integer> temp = new ArrayList<>();
                temp.add(num[i]);
                prev.add(temp);
            }
     
            //add all set created in this iteration
            for (List<Integer> temp : prev) {
                result.add(new ArrayList<>(temp));
            }
        }
     
        //add empty set
        result.add(new ArrayList<>());
     
        return result;
    }
    

}
