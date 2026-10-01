package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 8 · String to Integer (atoi) (Medium)                [was: lc/medium/L38_ConvertStringToInteger]
 *
 * Steps: skip leading spaces -> optional sign -> digits until non-digit -> clamp.
 * Optimal : O(n) time, O(1) space.
 * Changed : the original "best solution" toInt() crashed on "" (charAt(0)), ignored spaces and
 *           '+', and overflowed silently. atoi() used a double accumulator, which loses
 *           precision past 2^53. Here, overflow is checked before each multiply.
 *
 * Interviewers grade this on edge cases; list them before coding:
 * "", "   ", "+-1", "-0042", "2147483648", "-91283472332", "3.14", "words 987".
 */
public class LC0008_StringToInteger {

    public static int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i++) == '-' ? -1 : 1;
        }
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int d = s.charAt(i++) - '0';
            if (result > (Integer.MAX_VALUE - d) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + d;
        }
        return sign * result;
    }

    public static void main(String[] args) {
        Check.eq(myAtoi("42"), 42);
        Check.eq(myAtoi("   -042"), -42);
        Check.eq(myAtoi("1337c0d3"), 1337);
        Check.eq(myAtoi("0-1"), 0);
        Check.eq(myAtoi("words and 987"), 0);
        Check.eq(myAtoi(""), 0);
        Check.eq(myAtoi("+-12"), 0);
        Check.eq(myAtoi("2147483648"), Integer.MAX_VALUE);
        Check.eq(myAtoi("-2147483648"), Integer.MIN_VALUE);
        Check.eq(myAtoi("-91283472332"), Integer.MIN_VALUE);
    }
}
