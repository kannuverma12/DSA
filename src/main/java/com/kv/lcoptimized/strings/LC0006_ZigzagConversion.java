package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 6 · Zigzag Conversion (Medium)                       [was: lc/medium/L58_ZigZagConversion]
 *
 * Pattern : direct index arithmetic. Cycle = 2(rows-1). Row r takes indices k*cycle + r and,
 *           for middle rows, (k+1)*cycle - r.
 * Optimal : O(n) time, O(n) output, no per-row builders.
 * Changed : one formula instead of alternating step1/step2 flags; dropped the List<StringBuilder>
 *           variant (same complexity, more allocation).
 */
public class LC0006_ZigzagConversion {

    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        int cycle = 2 * (numRows - 1);
        StringBuilder sb = new StringBuilder(s.length());
        for (int r = 0; r < numRows; r++) {
            for (int k = r; k < s.length(); k += cycle) {
                sb.append(s.charAt(k));
                int diag = k + cycle - 2 * r;
                if (r != 0 && r != numRows - 1 && diag < s.length()) {
                    sb.append(s.charAt(diag));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
        Check.eq(convert("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
        Check.eq(convert("A", 1), "A");
    }
}
