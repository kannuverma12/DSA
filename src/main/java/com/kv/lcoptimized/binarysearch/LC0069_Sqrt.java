package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 69 · Sqrt(x) (Easy) + square root to p decimal places  [was: lc/L12_ImplementSqrt]
 *
 * Pattern : binary search on the answer (largest r with r*r <= x); compare mid <= x / mid
 *           to avoid overflow.
 * Optimal : O(log x). Newton's method converges quadratically - good to mention.
 * Changed : the original computed mid*mid in int. That overflows for x above about 2.1e9
 *           (mid ~ 46341), giving wrong answers. The fractional part added one increment at a
 *           time with doubles (up to 9 * p multiply/compare steps per digit); here the
 *           decimals come from one integer sqrt of x * 10^(2p), which is exact.
 */
public class LC0069_Sqrt {

    public static int mySqrt(int x) {
        int lo = 0;
        int hi = x;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2 + 1;   // upper mid (lo < mid <= hi); hi - lo + 1 would overflow at MAX_VALUE
            if (mid <= x / mid) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    /** Square root truncated to `precision` decimals (precision <= 6 keeps x*10^(2p) in a long). */
    public static double sqrt(int x, int precision) {
        long scale = 1;
        for (int i = 0; i < precision; i++) {
            scale *= 10;
        }
        long target = (long) x * scale * scale;
        long lo = 0;
        long hi = Math.max(1, (long) x) * scale;
        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (mid <= target / mid) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return (double) lo / scale;
    }

    /** Newton's iteration on integers: r <- (r + x/r) / 2, starting above the root. */
    public static int mySqrtNewton(int x) {
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        Check.eq(mySqrt(8), 2);
        Check.eq(mySqrt(0), 0);
        Check.eq(mySqrt(1), 1);
        Check.eq(mySqrt(Integer.MAX_VALUE), 46340);
        Check.eq(mySqrtNewton(Integer.MAX_VALUE), 46340);
        Check.eq(mySqrtNewton(15), 3);
        Check.near(sqrt(50, 3), 7.071);
        Check.near(sqrt(10, 4), 3.1622);
    }
}
