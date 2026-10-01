package com.kv.lcoptimized.bits;

import com.kv.lcoptimized.common.Check;

/**
 * Core bit tricks, in one place.
 *   [was: lc/CheckIfANumberIsPowerOf2, lc/CheckIfIthBitIsSet, lc/FindNumberOfSetBitsInANumber,
 *         lc/FindTotalNumberOfBitsInANumber, lc/ToggleAllBits - four of these were comment-only stubs]
 *
 * Changed : BUG FIX - isPowerOfTwo returned true for 0 and for Integer.MIN_VALUE (n & (n-1) == 0
 *           holds for both). The stubs are now implemented. bitLength uses
 *           32 - numberOfLeadingZeros instead of floor(log(n)/log(2)) + 1, which suffers from
 *           floating-point error (e.g. at n = 2^29 - 1 on some JVMs) and is undefined for 0.
 */
public final class BitTricks {

    private BitTricks() {
    }

    /** LC 231. */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /** LC 342: power of two AND the bit is at an even position. */
    public static boolean isPowerOfFour(int n) {
        return isPowerOfTwo(n) && (n & 0x55555555) != 0;
    }

    /** Bit i counted from the least-significant bit, 0-indexed. */
    public static boolean isBitSet(int n, int i) {
        return ((n >>> i) & 1) == 1;
    }

    /** LC 191 · Brian Kernighan: n & (n-1) clears the lowest set bit; loops popcount times. */
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;                                  // Integer.bitCount(n) in production
    }

    /** LC 338 · Counting Bits for 0..n in O(n): bits[i] = bits[i >> 1] + (i & 1). */
    public static int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    /** Number of bits needed to write n in binary (0 for n == 0). */
    public static int bitLength(int n) {
        return 32 - Integer.numberOfLeadingZeros(n);
    }

    /** Toggle all 32 bits. */
    public static int toggleAllBits(int n) {
        return ~n;                                     // == n ^ 0xFFFFFFFF
    }

    /** LC 476 · Number Complement: toggle only the significant bits. */
    public static int toggleSignificantBits(int n) {
        if (n == 0) {
            return 1;
        }
        int mask = -1 >>> Integer.numberOfLeadingZeros(n);
        return n ^ mask;
    }

    /** Lowest set bit (used by Fenwick trees and LC 260). */
    public static int lowestSetBit(int n) {
        return n & -n;
    }

    /** LC 190 · Reverse Bits. */
    public static int reverseBits(int n) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r = (r << 1) | (n & 1);
            n >>>= 1;
        }
        return r;                                      // Integer.reverse(n) in production
    }

    public static void main(String[] args) {
        Check.isTrue(isPowerOfTwo(8));
        Check.isTrue(!isPowerOfTwo(0));
        Check.isTrue(!isPowerOfTwo(Integer.MIN_VALUE));
        Check.isTrue(!isPowerOfTwo(6));
        Check.isTrue(isPowerOfFour(16));
        Check.isTrue(!isPowerOfFour(8));
        Check.isTrue(isBitSet(0b1010, 1));
        Check.isTrue(!isBitSet(0b1010, 2));
        Check.eq(countSetBits(11), 3);
        Check.eq(countSetBits(-1), 32);
        Check.eq(countBits(5), new int[] {0, 1, 1, 2, 1, 2});
        Check.eq(bitLength(8), 4);
        Check.eq(bitLength(0), 0);
        Check.eq(bitLength((1 << 29) - 1), 29);
        Check.eq(toggleAllBits(0), -1);
        Check.eq(toggleSignificantBits(5), 2);
        Check.eq(lowestSetBit(12), 4);
        Check.eq(reverseBits(0b00000010100101000001111010011100), 0b00111001011110000010100101000000);
    }
}
