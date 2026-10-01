package com.kv.lcoptimized.slidingwindow;

import com.kv.lcoptimized.common.Check;

/**
 * LC 567 · Permutation in String (Medium)                 [was: lc/medium/CheckIfStringIsPermutationOfOtherString]
 *
 * Pattern : fixed-size window with a running count of "matched" letters.
 * Optimal : O(n) time, O(26) space.
 * Changed : BUG FIX - the loop `i < s2.length() - s1.length()` skipped the last window, so
 *           ("ab", "ab") returned false. It also rebuilt the window counts for every start:
 *           O(n * m). This version moves the window one character at a time.
 *
 * Follow-up: return all start indices (LC 438 Find All Anagrams) - same loop, collect i.
 */
public class LC0567_PermutationInString {

    public static boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        if (m > s2.length()) {
            return false;
        }
        int[] diff = new int[26];              // count in window minus count in s1
        for (int i = 0; i < m; i++) {
            diff[s1.charAt(i) - 'a']--;
            diff[s2.charAt(i) - 'a']++;
        }
        int zeros = 0;
        for (int d : diff) {
            if (d == 0) {
                zeros++;
            }
        }
        for (int i = m; ; i++) {
            if (zeros == 26) {
                return true;
            }
            if (i == s2.length()) {
                return false;
            }
            zeros += update(diff, s2.charAt(i) - 'a', +1);
            zeros += update(diff, s2.charAt(i - m) - 'a', -1);
        }
    }

    /** Applies delta to diff[c] and returns the change in the number of zero entries. */
    private static int update(int[] diff, int c, int delta) {
        int before = diff[c] == 0 ? 1 : 0;
        diff[c] += delta;
        int after = diff[c] == 0 ? 1 : 0;
        return after - before;
    }

    public static void main(String[] args) {
        Check.isTrue(checkInclusion("ab", "eidbaooo"));
        Check.isTrue(!checkInclusion("ab", "eidboaoo"));
        Check.isTrue(checkInclusion("ab", "ab"));
        Check.isTrue(checkInclusion("adc", "dcda"));
    }
}
