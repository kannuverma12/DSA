package com.kv.lc;

public class MedianOfTwoSortedArray {

    public static void main(String[] args) {
        int arr1[] = {900};  
        int arr2[] = {5, 8, 10, 20};  
        
        System.out.println(getMedian(arr1, arr2, arr1.length, arr2.length));
        
    }
    
    /* This function returns median of ar1[] and ar2[].  
    Assumption in this function:  Both ar1[] and ar2[] are sorted arrays */
    static int getMedian(int arr1[], int arr2[], int n, int m)  
    {  
        int i = 0; /* Current index of input array ar1[] */
        int j = 0; /* Current index of input array ar2[] */
        int count;  
        int median1 = -1, median2 = -1;  
      
        // Since there are (n+m) elements, There are following two cases 
        // if n+m is odd then the middle index is median i.e. (m+n)/2 
        if((m + n) % 2 == 1) { 
            for (count = 0; count <= (n + m)/2; count++) { 
                if(i != n && j != m){ 
                    median1 = (arr1[i] > arr2[j]) ? arr2[j++] : arr1[i++]; 
                } 
                else if(i < n){ 
                    median1 = arr1[i++]; 
                } 
                // for case when j<m, 
                else{ 
                    median1 = arr1[j++]; 
                } 
            } 
            return median1; 
        } 
          
        // median will be average of elements  at index ((m+n)/2 - 1) and (m+n)/2 
        // in the array obtained after merging ar1 and ar2 
        else { 
            for (count = 0; count <= (n + m)/2; count++) { 
                median2 = median1; 
                if(i != n && j != m){ 
                    median1 = (arr1[i] > arr2[j]) ? arr2[j++] : arr1[i++]; 
                } 
                else if(i < n){ 
                    median1 = arr1[i++]; 
                } 
                // for case when j<m, 
                else{ 
                    median1 = arr1[j++]; 
                } 
            } 
            return (median1 + median2)/2; 
        } 
    } 

}
