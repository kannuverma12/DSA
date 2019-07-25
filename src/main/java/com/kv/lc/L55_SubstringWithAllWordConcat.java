package com.kv.lc;


import java.util.*;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in
 * words exactly once and without any intervening characters.
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 */
public class L55_SubstringWithAllWordConcat {

    /*We can use Hashing Technique to solve the above problem. Let’s see the steps :

Declare a map (hash_map) which stores all words of List L corresponding to their occurrences inside list L.
Traverse through all possible substrings in string S which are equal to size_L(total number of characters produced if all the words in list L are concatenated).
Create a temporary map (temp_hash_map) and initialize it with original map(hash_map) for every possible substring.
Extract the words from the substring and if the word is present in temp_hash_map we decrease it’s corresponding count, if it’s not present in temp_hash_map we simply break.
After traversing the substring we traverse temp_hash_map and look for any key which has it’s count > 0. If we found no such key it means that all the words in list L were found in substring and store the given starting index of the substring, if we find a key which has it’s count > 0 it means we did not traversed whole substring because we came across a word which was not in temp_hash_map.

     */

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] arr = {"foo","bar"};
        List<Integer> substr = findSubstring(s, arr);
        System.out.println("SubStr = "+ Arrays.toString(substr.toArray()));

    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        //frequency of words
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (map.containsKey(w)) {
                map.put(w, map.get(w) + 1);
            } else {
                map.put(w, 1);
            }
        }

        int len = words[0].length();

        for (int j = 0; j < len; j++) {
            HashMap<String, Integer> currentMap = new HashMap<>();
            int start = j;  //start index of start
            int count = 0;  //count total qualified words so far

            for (int i = j; i <= s.length() - len; i = i + len) {
                String sub = s.substring(i, i + len);
                if (map.containsKey(sub)) {
                    //set frequency in current map
                    if (currentMap.containsKey(sub)) {
                        currentMap.put(sub, currentMap.get(sub) + 1);
                    } else {
                        currentMap.put(sub, 1);
                    }

                    count++;

                    while (currentMap.get(sub) > map.get(sub)) {
                        String left = s.substring(start, start + len);
                        currentMap.put(left, currentMap.get(left) - 1);

                        count--;
                        start = start + len;
                    }


                    if (count == words.length) {
                        result.add(start); //add to result

                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start + len);
                        currentMap.put(left, currentMap.get(left) - 1);
                        count--;
                        start = start + len;
                    }
                } else {
                    currentMap.clear();
                    start = i + len;
                    count = 0;
                }
            }
        }

        return result;
    }

}
