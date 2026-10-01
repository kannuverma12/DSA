package com.kv.lcoptimized.bits;

import com.kv.lcoptimized.common.Check;

/**
 * Single Number family                                   [was: lc/FindNonDuplicateElementInArray (empty stub),
 *                                                               lc/SingleNumber2]
 *   LC 136 : every element twice except one  -> XOR everything. O(n), O(1).
 *   LC 137 : every element three times except one -> per-bit counting mod 3, done with two
 *            bitmasks (ones, twos) as a 2-bit counter per bit position. O(n), O(1).
 *   LC 260 : two singles, rest twice -> x = a ^ b; split numbers by x's lowest set bit.
 * Changed : the LC 137 original used a 3-variable version with a separate `threes` mask; the
 *           2-mask form below is the same state machine in fewer ops. The LC 136 stub is
 *           implemented, and LC 260 was added as the usual follow-up.
 */
public class LC0136_SingleNumber {

    public static int singleNumber(int[] nums) {
        int x = 0;
        for (int v : nums) {
            x ^= v;
        }
        return x;
    }

    /** LC 137: (twos, ones) goes 00 -> 01 -> 10 -> 00 per bit. */
    public static int singleNumberII(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int v : nums) {
            ones = (ones ^ v) & ~twos;
            twos = (twos ^ v) & ~ones;
        }
        return ones;
    }

    /** LC 260. */
    public static int[] singleNumberIII(int[] nums) {
        int xor = 0;
        for (int v : nums) {
            xor ^= v;
        }
        int diffBit = xor & -xor;
        int a = 0;
        for (int v : nums) {
            if ((v & diffBit) != 0) {
                a ^= v;
            }
        }
        int b = xor ^ a;
        return a < b ? new int[] {a, b} : new int[] {b, a};
    }

    public static void main(String[] args) {
        Check.eq(singleNumber(new int[] {1, 2, 3, 1, 3}), 2);
        Check.eq(singleNumberII(new int[] {2, 2, 3, 2}), 3);
        Check.eq(singleNumberII(new int[] {0, 1, 0, 1, 0, 1, 99}), 99);
        Check.eq(singleNumberII(new int[] {-2, -2, 1, 1, 4, 1, 4, 4, -4, -2}), -4);
        Check.eq(singleNumberIII(new int[] {1, 2, 1, 3, 2, 5}), new int[] {3, 5});
    }
}
