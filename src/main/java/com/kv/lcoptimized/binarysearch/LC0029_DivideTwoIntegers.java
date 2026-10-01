package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 29 · Divide Two Integers (Medium)                    [was: lc/DivideTwoInteger]
 *
 * Pattern : long division in base 2 - subtract divisor << k for the largest k that fits.
 * Optimal : O(log n) - scan quotient bits from 31 down to 0, one subtraction attempt each.
 * Changed : the original rebuilt the shift count from 0 on every outer iteration (O(log^2 n)),
 *           and the second method only handled positives and overflowed `divisor <<= 1`.
 *           Widening to long sidesteps |MIN_VALUE| having no int representation.
 */
public class LC0029_DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;                       // the only overflow case
        }
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long q = 0;
        for (int bit = 31; bit >= 0; bit--) {
            if ((a >> bit) >= b) {                         // b << bit fits into a
                a -= b << bit;
                q |= 1L << bit;
            }
        }
        return (dividend < 0) ^ (divisor < 0) ? (int) -q : (int) q;
    }

    public static void main(String[] args) {
        Check.eq(divide(10, 3), 3);
        Check.eq(divide(7, -3), -2);
        Check.eq(divide(Integer.MIN_VALUE, -1), Integer.MAX_VALUE);
        Check.eq(divide(Integer.MIN_VALUE, 1), Integer.MIN_VALUE);
        Check.eq(divide(Integer.MIN_VALUE, 2), -1073741824);
        Check.eq(divide(0, 5), 0);
    }
}
