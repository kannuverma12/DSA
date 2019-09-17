package com.kv.lc;

public class L73_RemoveElement {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        System.out.println("Original length : " + arr.length);
        System.out.println("Length after removing  : " + removeElement(arr, 2));
    }

    static int removeElement(int[] A, int elem) {
        int i = 0;
        int j = 0;

        while (j < A.length) {
            if (A[j] != elem) {
                A[i] = A[j];
                i++;
            }

            j++;
        }

        return i;
    }
}
