package com.kv.lcoptimized.dp;

import com.kv.lcoptimized.common.Check;

/**
 * LC 91 · Decode Ways (Medium)                            [was: lc/DP17_DecodeWays]
 *
 * Pattern : Fibonacci-like DP. ways[i] = ways[i-1] (if s[i-1] != '0')
 *                                      + ways[i-2] (if s[i-2..i-1] is in 10..26).
 * Optimal : O(n) time, O(1) space (two rolling variables).
 * Changed : the original special-cased the first two characters with nested ifs, parsed
 *           substrings with Integer.parseInt in the loop (allocating), and kept an O(n) array.
 *
 * Follow-up: LC 639 adds '*' wildcards - same recurrence with multiplicities, mod 1e9+7.
 */
public class LC0091_DecodeWays {

    public static int numDecodings(String s) {
        int prev2 = 1;                                  // ways for prefix of length i-2
        int prev1 = s.charAt(0) == '0' ? 0 : 1;         // ways for prefix of length i-1
        for (int i = 2; i <= s.length(); i++) {
            int cur = 0;
            if (s.charAt(i - 1) != '0') {
                cur += prev1;
            }
            int two = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (two >= 10 && two <= 26) {
                cur += prev2;
            }
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Check.eq(numDecodings("12"), 2);
        Check.eq(numDecodings("226"), 3);
        Check.eq(numDecodings("06"), 0);
        Check.eq(numDecodings("20"), 1);
        Check.eq(numDecodings("100"), 0);
        Check.eq(numDecodings("11106"), 2);
    }
}
