package com.kv.fb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author karanverma
 *  Given a list of arrays of time intervals, write a function that calculates the total amount of time 
 *  covered by the intervals.    
 *
 */
public class TotalTimeCoveredByIntervals {

    /*
     * Given a list of arrays of time intervals, write a function that calculates the total amount of time covered by the intervals. 
        For example: 
        input = [(1,4), (2,3)] 
        return 3 
        input = [(4,6), (1,2)] 
        return 3 
        input = {{1,4}, {6,8}, {2,4}, {7,9}, {10, 15}} 
        return 11
     */
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] segments = {{1,2},{3,4},{4,5}};
        System.out.println("Total time : "+mergeSegments(segments));
        
        
        System.out.println("Total time 2: ");
        totalInterval(segments);

    }
    
    public static int mergeSegments(int[][] segments) {
        Arrays.sort(segments, (x, y) -> x[0] - y[0]);
        
        

        int result = 0;
        int last = 0;
        for(int[] seg : segments) {
            result += Math.max(seg[1] - Math.max(last, seg[0]), 0);
            last = Math.max(last, seg[1]);
        }
        return result;
    }
    
    static Set<Integer> arraySet = new HashSet<>();

    static void totalInterval(int[][] input){

        int totalInter = 0;

        for (int[] inputPair : input) {

            int first = inputPair[0];
            int second = inputPair[1];

            for (int i = first; i < second; i++) {
                arraySet.add(i);
            }

        }

        System.out.println(arraySet);

//        for (int i = 0; i < arraySet.size(); i ++) {
//            if (arraySet.get(i) == 1)
//                totalInter = totalInter + 1;
//        }

        System.out.println(arraySet.size());
    }
    
    
   

}
