package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *  Each element in the array represents your maximum jump length at that position.
 *  Determine if you are able to reach the last index.
 *  
 *  Input: [2,3,1,1,4]
 *  Output: true
 *  Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class L15_JumpGameArray {
    
    /*
     *  We can track the maximum index that can be reached. The key to solve this problem is to find: 
     *      1) when the current position can not reach next position (return false) , and 
     *      2) when the maximum index can reach the end (return true).
     * The largest index that can be reached is: i + A[i].
     */

    public static void main(String[] args) {
        //int[] arr = {3, 2, 1, 0, 4};
        int[] arr = {2, 3, 1, 1, 4};
        System.out.println(canJump(arr));
    }
    
    public static boolean canJump(int[] A) {
        if(A.length <= 1)
            return true;
     
        int max = A[0]; //max stands for the largest index that can be reached.
     
        //System.out.println("max = "+max);
        for(int i=0; i<A.length; i++){
            //if not enough to go to next
            if(max <= i && A[i] == 0) 
                return false;
     
            //update max    
            if(i + A[i] > max){
                max = i + A[i];
            }
     
            //System.out.println("max = "+max+", A.length-1 = "+(A.length-1) );
            //max is enough to reach the end
            if(max >= A.length-1) 
                return true;
        }
     
        return false;    
    }

}
