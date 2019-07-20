package com.kv.lc;

import java.util.Arrays;

/**
 *  @author karanverma
 *  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *  Input:
 *  [[1,1,1],[1,0,1],[1,1,1]]
 *  Output: [
 *              [1,0,1],
 *              [0,0,0],
 *              [1,0,1]
 *          ]
 */
public class L20_SetMetrixZeroes {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println("Original Matrix : "+Arrays.deepToString(matrix));
        setZeroes(matrix);
        System.out.println("Matrix after setting zeroes : "+Arrays.deepToString(matrix));
    }
    
    public static void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
 
        //set first row and column zero or not
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0] == 0){
                firstColumnZero = true;
                break;
            }
        }
 
        for(int i=0; i<matrix[0].length; i++){
            if(matrix[0][i] == 0){
                firstRowZero = true;
                break;
            }
        }
 
        //mark zeros on first row and column
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                   matrix[i][0] = 0;
                   matrix[0][j] = 0;
                }
            }
        }
 
        //use mark to set elements
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                   matrix[i][j] = 0;
                }
            }
        }
 
        //set first column and row
        if(firstColumnZero){
            for(int i=0; i<matrix.length; i++)
                matrix[i][0] = 0;
        }
 
        if(firstRowZero){
            for(int i=0; i<matrix[0].length; i++)
                matrix[0][i] = 0;
        }
 
    }

}
