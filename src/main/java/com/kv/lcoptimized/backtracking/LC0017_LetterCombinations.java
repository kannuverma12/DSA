package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 17 · Letter Combinations of a Phone Number (Medium)  [was: lc/DP18_LetterCombinationOfAPhoneNumberLC]
 *
 * Pattern : DFS filling one char[] slot per digit.
 * Optimal : O(4^n * n) time (output size), O(n) recursion.
 * Changed : static String[] keypad indexed by digit instead of a HashMap<Character,char[]>
 *           rebuilt on every call. The char[] buffer approach was kept (it was good).
 */
public class LC0017_LetterCombinations {

    private static final String[] KEYPAD = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String digits) {
        List<String> out = new ArrayList<>();
        if (!digits.isEmpty()) {
            dfs(digits, 0, new char[digits.length()], out);
        }
        return out;
    }

    private static void dfs(String digits, int i, char[] buf, List<String> out) {
        if (i == digits.length()) {
            out.add(new String(buf));
            return;
        }
        for (char c : KEYPAD[digits.charAt(i) - '0'].toCharArray()) {
            buf[i] = c;
            dfs(digits, i + 1, buf, out);
        }
    }

    public static void main(String[] args) {
        Check.eq(letterCombinations("23"), Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        Check.eq(letterCombinations("").size(), 0);
        Check.eq(letterCombinations("79").size(), 16);
    }
}
