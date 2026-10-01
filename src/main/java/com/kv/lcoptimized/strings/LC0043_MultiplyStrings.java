package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 43 · Multiply Strings (Medium)                       [was: lc/L8_MultiplyStrings]
 *
 * Pattern : grade-school multiplication; digit i * digit j lands in positions i+j and i+j+1.
 * Optimal : O(m*n) time, O(m+n) space. (Karatsuba is O(n^1.585); mention for huge inputs.)
 * Changed : no string reversals, carries folded into the inner loop, no sb.insert(0, ..)
 *           (that made output construction O((m+n)^2)), and no debug println in the hot loop.
 */
public class LC0043_MultiplyStrings {

    public static String multiply(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int sum = (a.charAt(i) - '0') * (b.charAt(j) - '0') + pos[i + j + 1];
                pos[i + j + 1] = sum % 10;
                pos[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int d : pos) {
            if (!(sb.length() == 0 && d == 0)) {
                sb.append(d);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(multiply("123", "456"), "56088");
        Check.eq(multiply("12", "12"), "144");
        Check.eq(multiply("0", "9999"), "0");
        Check.eq(multiply("999", "999"), "998001");
    }
}
