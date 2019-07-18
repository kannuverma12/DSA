package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given a string s, partition s such that every substring of the partition is a palindrome.
 *  Return all possible palindrome partitioning of s.
 *  
 *  Input: "aab"
 *  Output:
 *  [
 *      ["aa","b"],
 *      ["a","a","b"]
 *  ]
 */
public class DP6_PalindromePartioning {

    public static void main(String[] args) {
        String string = "aab";
        System.out.println("Palindrome partitions using DD : "+Arrays.deepToString(palindromePartitioning(string).toArray()));
        System.out.println("Palindrome partitions usinf DFS : "+Arrays.deepToString(partition(string).toArray()));

    }
    
    // Method 1 - DP
    public static List<String> palindromePartitioning(String s) {

        List<String> result = new ArrayList<String>();

        if (s == null)
            return result;

        if (s.length() <= 1) {
            result.add(s);
            return result;
        }

        int length = s.length();

        int[][] table = new int[length][length];

        // l is length, i is index of left boundary, j is index of right boundary
        for (int l = 1; l <= length; l++) {
            for (int i = 0; i <= length - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (l == 1 || l == 2) {
                        table[i][j] = 1;
                    } else {
                        table[i][j] = table[i + 1][j - 1];
                    }
                    if (table[i][j] == 1) {
                        result.add(s.substring(i, j + 1));
                    }
                } else {
                    table[i][j] = 0;
                }
            }
        }

        return result;
    }

    // Method 1 - DFS
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();

        if (s == null || s.length() == 0) {
            return result;
        }

        List<String> partition = new ArrayList<String>();// track each possible partition
        addPalindrome(s, 0, partition, result);

        return result;
    }

    private static void addPalindrome(String s, int start, List<String> partition, List<List<String>> result) {
        // stop condition
        if (start == s.length()) {
            List<String> temp = new ArrayList<String>(partition);
            result.add(temp);
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (isPalindrome(str)) {
                partition.add(str);
                addPalindrome(s, i, partition, result);
                partition.remove(partition.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

}
