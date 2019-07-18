package com.kv.fb;

import java.util.Arrays;

/**
 * 
 * @author karanverma
 *  Given two sorted arrays, merge them.
 *  
 *  Input : l1=[1,2,3,4] 
 *          l2=[1,3,6,7,null,null,null,null] 
 *  output: l2=[1,1,2,3,3,4,6,7]
 *  
 */
public class MergeTwoSortedArrays {

    public static void main(String[] args) {
        merge();
    }

    public static void merge() {

        int[] a = { 1, 2, 3, 4, 5 };
        int[] b = { 1, 1, 2, 2, 3, 3, 4, 4, 5 };
        int[] c = new int[a.length + b.length];

        int i = 0, j = 0, end = 0;
        while (i < a.length && j < b.length)
            c[end++] = (a[i] <= b[j]) ? a[i++] : b[j++];
        while (i < a.length)
            c[end++] = a[i++];
        while (j < b.length)
            c[end++] = b[j++];

        System.out.println(Arrays.toString(c));
    }

}
