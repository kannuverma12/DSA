package com.kv.lc;

import java.util.Arrays;

/*
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * Example:
 * Input: 3,
 * Output:
 * [
 *      [ 1, 2, 3 ],
 *      [ 8, 9, 4 ],
 *      [ 7, 6, 5 ]
 * ]
 */
public class L14_SpiralMatrix2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[][] matrix = generateMatrix(3);
        print2DArray(matrix);

    }
    
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int k = 1;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        while (k <= n * n) {
            for (int i = left; i <= right; i++) {
                result[top][i] = k;
                k++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[i][right] = k;
                k++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                result[bottom][i] = k;
                k++;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                result[i][left] = k;
                k++;
            }
            left++;
        }

        return result;
    }
    
    static void print2DArray(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
