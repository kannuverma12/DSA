package com.kv.lcoptimized.slidingwindow;

import com.kv.lcoptimized.common.Check;

/**
 * LC 340 · Longest Substring with At Most K Distinct Characters (Medium)
 *                                                         [was: lc/L56_LongestSubstringWithKUniqueCharacters]
 *
 * Pattern : variable sliding window with a count table and a "distinct" counter.
 * Optimal : O(n) time, O(alphabet) space.
 * Changed : BUG FIX - the original shrank with `while (map.size() > 2)`, hard-coding k = 2,
 *           so any k != 2 gave wrong answers. Also counts in int[128] instead of a HashMap.
 *
 * L6/L7 follow-ups:
 *  - "Exactly k distinct" substrings count = atMost(k) - atMost(k-1) (LC 992).
 *  - Stream input where you can't re-read s[left] -> keep last index per char in a
 *    LinkedHashMap (LRU order) and evict the least-recent char: O(n log k) or O(n).
 */
public class LC0340_LongestSubstringKDistinct {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[128];
        int distinct = 0;
        int best = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (count[s.charAt(right)]++ == 0) {
                distinct++;
            }
            while (distinct > k) {
                if (--count[s.charAt(left++)] == 0) {
                    distinct--;
                }
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        Check.eq(lengthOfLongestSubstringKDistinct("abcbbbbcccbdddadacb", 2), 10);
        Check.eq(lengthOfLongestSubstringKDistinct("eceba", 2), 3);
        Check.eq(lengthOfLongestSubstringKDistinct("abcdbcbc", 3), 7); // original returned 6
        Check.eq(lengthOfLongestSubstringKDistinct("aa", 0), 0);
    }
}
