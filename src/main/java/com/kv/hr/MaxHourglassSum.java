package com.kv.hr;

/**
 * 
 * @author karanverma
 *
 *  Find Max Hourglass sum
 *  https://www.hackerrank.com/challenges/2d-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
public class MaxHourglassSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int[][] arr = {{1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0}};
        System.out.println("Hourglass sum = "+hourglassSum(arr));

    }
    
    static int hourglassSum(int[][] arr) {
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            for(int j=0; j<arr[i].length;j++){
                if(i+2 < arr.length && j+2 < arr[i].length){
                    int r1 = arr[i][j] + arr[i][j+1] + arr[i][j+2];
                    int r2 = arr[i+1][j+1];
                    int r3 = arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];

                    int hourglassSum = r1 + r2 + r3;

                    if(hourglassSum > max)
                        max = hourglassSum;

                }
            }
        }
        return max;

    }

}
