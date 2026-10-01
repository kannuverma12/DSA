package com.kv.lcoptimized.binarysearch;

import com.kv.lcoptimized.common.Check;

/**
 * LC 50 · Pow(x, n) (Medium)                              [was: lc/medium/L11_ImplementPowerOperation]
 *
 * Pattern : binary (fast) exponentiation over the bits of n.
 * Optimal : O(log n) time, O(1) space (iterative).
 * Changed : the original labels were backwards. pow2 ("most effective") is O(n) linear
 *           recursion and overflows the stack for large n. pow3 ignores negative n and
 *           takes int x. pow1 ("taking longest time") was actually the right algorithm.
 *           Here n is widened to long so n = Integer.MIN_VALUE can be negated safely.
 *
 * L6/L7 follow-ups: modular pow (a^b mod m) for hashing / Rabin-Karp; matrix fast exponentiation
 * for Fibonacci / linear recurrences in O(k^3 log n) (see dp/LC0070_ClimbingStairs).
 */
public class LC0050_Pow {

    public static double myPow(double x, int n) {
        long e = n;
        if (e < 0) {
            x = 1 / x;
            e = -e;
        }
        double result = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                result *= x;
            }
            x *= x;
            e >>= 1;
        }
        return result;
    }

    /** (base^exp) % mod without overflow for mod < 2^31. */
    public static long modPow(long base, long exp, long mod) {
        long result = 1 % mod;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Check.near(myPow(2, 10), 1024);
        Check.near(myPow(2.1, 3), 9.261);
        Check.near(myPow(2, -2), 0.25);
        Check.near(myPow(1, Integer.MIN_VALUE), 1);
        Check.eq(modPow(2, 30, 1_000_000_007L), 73741817L);
    }
}
