package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All strings of length k over a character set (GfG)      [was: lc/DP10_PrintAllKLengthStrings]
 *
 * Pattern : k nested loops written as recursion (or as counting in base |set|).
 * Complexity: O(|set|^k * k) - output-bound.
 * Changed : a shared char[] buffer instead of `prefix + set[i]` at every node; returns the list
 *           instead of printing. Also an iterative "odometer" version with no recursion.
 */
public class AllKLengthStrings {

    public static List<String> allKLength(char[] set, int k) {
        List<String> out = new ArrayList<>();
        fill(set, new char[k], 0, out);
        return out;
    }

    private static void fill(char[] set, char[] buf, int pos, List<String> out) {
        if (pos == buf.length) {
            out.add(new String(buf));
            return;
        }
        for (char c : set) {
            buf[pos] = c;
            fill(set, buf, pos + 1, out);
        }
    }

    /** Odometer: increment the rightmost digit, carrying left. */
    public static List<String> allKLengthIterative(char[] set, int k) {
        List<String> out = new ArrayList<>();
        int[] digits = new int[k];
        char[] buf = new char[k];
        while (true) {
            for (int i = 0; i < k; i++) {
                buf[i] = set[digits[i]];
            }
            out.add(new String(buf));
            int i = k - 1;
            while (i >= 0 && ++digits[i] == set.length) {
                digits[i--] = 0;
            }
            if (i < 0) {
                return out;
            }
        }
    }

    public static void main(String[] args) {
        Check.eq(allKLength(new char[] {'a', 'b'}, 3),
                Arrays.asList("aaa", "aab", "aba", "abb", "baa", "bab", "bba", "bbb"));
        Check.eq(allKLengthIterative(new char[] {'a', 'b'}, 3), allKLength(new char[] {'a', 'b'}, 3));
        Check.eq(allKLength(new char[] {'a', 'b', 'c', 'd'}, 2).size(), 16);
    }
}
