package com.kv.hr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author karanverma
 *
 *         Java program to Return the total number of matching pairs of socks
 */
public class CheckPairedSocks {

    public static void main(String[] args) {
        int n = 9;
        // int[] ar = {10, 20, 20, 10, 10, 30, 50, 10, 20};

        // int[] ar = {6,5,2,3,5,2,2,1,1,5,1,3,3,3,5};

        int[] ar = { 4, 5, 5, 5, 6, 6, 4, 1, 4, 4, 3, 6, 6, 3, 6, 1, 4, 5, 5, 5 };
        System.out.println(sockMerchant2(n, ar));
    }

    static int sockMerchant(int n, int[] ar) {
        int[] countingArr = new int[100];
        for (int i = 0; i < ar.length - 1; i++) {
            countingArr[ar[i]]++;
        }
        int count = 0;
        System.out.println(Arrays.toString(countingArr));
        for (int i = 0; i < countingArr.length - 1; i++) {
            if (countingArr[i] != 0 && countingArr[i] % 2 == 0) {
                while (countingArr[i] > 0 && countingArr[i] % 2 == 0) {
                    countingArr[i] = countingArr[i] / 2;
                    count++;
                }

                // System.out.println(countingArr[i]);

            }
        }
        return count;
    }

    static int sockMerchant2(int n, int[] ar) {
        Map<Integer, Integer> map = new HashMap<>();
        int count1 = 0;
        for (int i = 0; i < ar.length - 1; i++) {
            if (map.containsKey(ar[i])) {
                int val = map.get(ar[i]);
                // if(val >= 2) {
                // val = val - 2;
                // count1++;
                // }
                map.put(ar[i], val + 1);
            } else {
                map.put(ar[i], 1);
            }
        }
        System.out.println(map.toString());
        for (Integer val : map.values()) {
            if (val >= 2) {
                int v = val;
                while (v >= 2) {
                    v -= 2;
                    count1++;
                }

            }
        }

        return count1;
    }

}
