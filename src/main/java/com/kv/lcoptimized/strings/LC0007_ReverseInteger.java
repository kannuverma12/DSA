package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 7 · Reverse Integer (Medium)                         [was: lc/medium/L59_ReverseInteger]
 *
 * Pattern : pop/push digits with a pre-multiplication overflow check.
 * Optimal : O(log10 x) time, O(1) space, no 64-bit arithmetic (the problem forbids it).
 * Changed : the original was already correct. The check is simplified to
 *           |rev| > MAX/10: with 10 digits the last digit is at most 2, so the "== MAX/10 and
 *           pop > 7" branch can't trigger.
 */
public class LC0007_ReverseInteger {

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        Check.eq(reverse(123), 321);
        Check.eq(reverse(-123), -321);
        Check.eq(reverse(120), 21);
        Check.eq(reverse(1534236469), 0);
        Check.eq(reverse(-2147483412), -2143847412);
    }
}
