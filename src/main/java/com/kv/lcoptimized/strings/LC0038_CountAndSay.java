package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 38 · Count and Say (Medium)                          [was: lc/L77_CountAndSay]
 *
 * Pattern : run-length encode the previous term, n-1 times.
 * Optimal : O(total length generated), about 1.3^n (Conway's constant). There's no closed form.
 * Changed : run-length loop with an explicit run end; the original was correct.
 */
public class LC0038_CountAndSay {

    public static String countAndSay(int n) {
        String cur = "1";
        for (int step = 1; step < n; step++) {
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < cur.length(); ) {
                int j = i;
                while (j < cur.length() && cur.charAt(j) == cur.charAt(i)) {
                    j++;
                }
                next.append(j - i).append(cur.charAt(i));
                i = j;
            }
            cur = next.toString();
        }
        return cur;
    }

    public static void main(String[] args) {
        Check.eq(countAndSay(1), "1");
        Check.eq(countAndSay(4), "1211");
        Check.eq(countAndSay(6), "312211");
    }
}
