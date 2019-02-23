package com.kv.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author karanverma 
 * 
 *         Given a digit string, return all possible letter
 *         combinations that the number could represent. (Check out your
 *         cell phone to see the mappings) Input:Digit string "23", Output:
 *         ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationOfAPhoneNumberLC {
    /*
     * This problem can be solves by a typical DFS algorithm. DFS problems are very
     * similar and can be solved by using a simple recursion. Check out the index
     * page to see other DFS problems.
     */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(letterCombinations("23").toArray()));
    }

    public static List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> dict = new HashMap<Character, char[]>();
        dict.put('2', new char[] { 'a', 'b', 'c' });
        dict.put('3', new char[] { 'd', 'e', 'f' });
        dict.put('4', new char[] { 'g', 'h', 'i' });
        dict.put('5', new char[] { 'j', 'k', 'l' });
        dict.put('6', new char[] { 'm', 'n', 'o' });
        dict.put('7', new char[] { 'p', 'q', 'r', 's' });
        dict.put('8', new char[] { 't', 'u', 'v' });
        dict.put('9', new char[] { 'w', 'x', 'y', 'z' });

        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[] arr = new char[digits.length()];
        helper(digits, 0, dict, result, arr);

        return result;
    }

    private static void helper(String digits, int index, HashMap<Character, char[]> dict, List<String> result,
            char[] arr) {
        if (index == digits.length()) {
            result.add(new String(arr));
            return;
        }

        char number = digits.charAt(index);
        char[] candidates = dict.get(number);
        for (int i = 0; i < candidates.length; i++) {
            arr[index] = candidates[i];
            helper(digits, index + 1, dict, result, arr);
        }
    }

}
