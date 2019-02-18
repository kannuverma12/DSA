package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Given an array with n objects colored red, white or blue, sort them in-place so that objects 
 *  of the same color are adjacent, with the colors in the order red, white and blue.
 *  Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *  Note: You are not suppose to use the library's sort function for this problem.
 *  
 *  Input: [2,0,2,1,1,0]
 *  Output: [0,0,1,1,2,2]
 *  
 *  Follow up:
 *  A rather straight forward solution is a two-pass algorithm using counting sort.
 *  First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total 
 *  number of 0's, then 1's and followed by 2's.
 *  Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors0s1s2s {
    /*
     * link for understaing 
     * http://www.cs.miami.edu/home/burt/learning/Csc517.101/workbook/countingsort.html
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    // Method 1 - Counting sort
    public void sortColors(int[] nums) {
        if(nums==null||nums.length<2){
            return;
        }
     
        int[] countArray = new int[3];
        for(int i=0; i<nums.length; i++){
            countArray[nums[i]]++;
        }
     
        for(int i=1; i<=2; i++){
            countArray[i]=countArray[i-1]+countArray[i];
        }
     
        int[] sorted = new int[nums.length];
        for(int i=0;i<nums.length; i++){
            int index = countArray[nums[i]]-1;
            countArray[nums[i]] = countArray[nums[i]]-1;
            sorted[index]=nums[i];
        }
     
        System.arraycopy(sorted, 0, nums, 0, nums.length);
    }
    
    /*
     * In solution 1, two arrays are created. One is for counting, and the other is for storing 
     * the sorted array (space is O(n)). We can improve the solution so that it only uses constant 
     * space. Since we already get the count of each element, we can directly project them to the 
     * original array, instead of creating a new one.
     */
    // Method 2
    public void sortColors2(int[] nums) {
        if(nums==null||nums.length<2){
            return;
        }
     
        int[] countArray = new int[3];
        for(int i=0; i<nums.length; i++){
            countArray[nums[i]]++;
        }
     
        int j = 0;
        int k = 0;
        while(j<=2){
            if(countArray[j]!=0){
                nums[k++]=j;
                countArray[j] = countArray[j]-1;
            }else{
                j++;
            }
        }
    }

}
