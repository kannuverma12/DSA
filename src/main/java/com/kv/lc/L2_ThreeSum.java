package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 * 
 *  Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 *  Find all unique triplets in the array which gives the sum of zero.
 */
public class L2_ThreeSum {

    public static void main(String[] args) {
        int[] arr = { -1, 0, 1, 2, -1, -4 };
        System.out.println(threeSum(arr));
    }

    public static List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (arr == null || arr.length < 3)
            return result;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] > arr[i - 1]) {
                int j = i + 1;
                int k = arr.length - 1;

                while (j < k) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        List<Integer> l = new ArrayList<>();
                        l.add(arr[i]);
                        l.add(arr[j]);
                        l.add(arr[k]);
                        result.add(l);

                        j++;
                        k--;

                        // handle duplicate here
                        while (j < k && arr[j] == arr[j - 1])
                            j++;
                        while (j < k && arr[k] == arr[k + 1])
                            k--;

                    } else if (arr[i] + arr[j] + arr[k] < 0) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }

        }

        return result;
    }

}
