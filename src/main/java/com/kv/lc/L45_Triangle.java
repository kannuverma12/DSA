package com.kv.lc;

import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent 
 *  numbers on the row below.
 *  For example, given the following triangle
 *  [
 *       [2],
 *      [3,4],
 *     [6,5,7],
 *    [4,1,8,3]
 *  ]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class L45_Triangle {

    public static void main(String[] args) {
        
        

    }

    //We can actually start from the bottom of the triangle.
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] total = new int[triangle.size()];
        int l = triangle.size() - 1;

        for (int i = 0; i < triangle.get(l).size(); i++) {
            total[i] = triangle.get(l).get(i);
        }

        // iterate from last second row
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
                total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j + 1]);
            }
        }

        return total[0];
    }

}
