package com.kv.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5, 
 *  the result should be:
 *  [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 *  ]
 *  
 *  leetcode : https://leetcode.com/problems/pascals-triangle/submissions/
 */
public class L46_PascalTriangle {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows <= 0)
            return result;
     
        List<Integer> pre = new ArrayList<Integer>();
        pre.add(1);
        result.add(pre);
     
        for (int i = 2; i <= numRows; i++) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
     
            cur.add(1); //first
            for (int j = 0; j < pre.size() - 1; j++) {
                cur.add(pre.get(j) + pre.get(j + 1)); //middle
            }
            cur.add(1);//last
     
            result.add(cur);
            pre = cur;
        }
     
        return result;
    }

}
