package com.kv.dp;

/**
 * 
 * @author karan.verma
 * 
 * Kadane’s algorithm for 1D array can be used to reduce the time complexity to O(n^3). 
 * The idea is to fix the left and right columns one by one and find the maximum sum 
 * contiguous rows for every left and right column pair. We basically find top and bottom 
 * row numbers (which have maximum sum) for every fixed left and right column pair. To find 
 * the top and bottom row numbers, calculate sun of elements in every row from left to right 
 * and store these sums in an array say temp[]. So temp[i] indicates sum of elements from left 
 * to right in row i. If we apply Kadane’s 1D algorithm on temp[], and get the maximum sum subarray 
 * of temp, this maximum sum would be the maximum possible sum with left and right as boundary 
 * columns. To get the overall maximum sum, we compare this sum with the maximum sum so far.
 */
public class MaxSumRectangleIn2DMatrix {

	public static void main(String[] args) {

        findMaxSubMatrix(new int[][] {
                            {1, 2, -1, -4, -20},
                            {-8, -3, 4, 2, 1},
                            {3, 8, 10, 1, 3},
                            {-4, -1, 1, 7, -6}
                            });

	}
	
	/**
     * To find maxSum in 1d array return {maxSum, left, right}
     */
    public static int[] kadane(int[] a) {
        //result[0] == maxSum, result[1] == start, result[2] == end;
        int[] result = new int[]{Integer.MIN_VALUE, 0, -1};
        int currentSum = 0;
        int localStart = 0;
     
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            if (currentSum < 0) {
                  currentSum = 0;
                localStart = i + 1;
              } else if (currentSum > result[0]) {
                result[0] = currentSum;
                result[1] = localStart;
                result[2] = i;
              }
        }
         
        //all numbers in a are negative
        if (result[2] == -1) {
            result[0] = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] > result[0]) {
                    result[0] = a[i];
                    result[1] = i;
                    result[2] = i;
                }
            }
        }
         
        return result;
      }
 
    /**
     * To find and print maxSum, (left, top),(right, bottom)
     */
    public static void findMaxSubMatrix(int[][] a) {
        int cols = a[0].length;
        int rows = a.length;
        int[] currentResult;
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
         
        for (int leftCol = 0; leftCol < cols; leftCol++) {
            int[] tmp = new int[rows];
     
              for (int rightCol = leftCol; rightCol < cols; rightCol++) {
         
                for (int i = 0; i < rows; i++) {
                      tmp[i] += a[i][rightCol];
                }
                currentResult = kadane(tmp);
                if (currentResult[0] > maxSum) {
                    maxSum = currentResult[0];
                    left = leftCol;
                    top = currentResult[1];
                    right = rightCol;
                    bottom = currentResult[2];
                }
            }
        }
              System.out.println("MaxSum: " + maxSum + 
                                ", range: [(" + left + ", " + top + 
                                  ")(" + right + ", " + bottom + ")]");
    }

}
