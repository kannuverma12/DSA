package com.kv.lc;

import java.util.Arrays;

/**
 *  @author karanverma
 *  You are given an n x n 2D matrix representing an image.
 *  Rotate the image by 90 degrees (clockwise).
 */
public class L9_RotateImageof2DArray {
    /*
     * By using the relation "matrix[i][j] = matrix[n-1-j][i]", we can loop through the matrix.
     */
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Original : ");
        print2DArray(matrix);
        
        rotate(matrix);
        System.out.println("Rotated : ");
        print2DArray(matrix);
    }
    
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
    
    static void print2DArray(int[][] matrix) {
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
