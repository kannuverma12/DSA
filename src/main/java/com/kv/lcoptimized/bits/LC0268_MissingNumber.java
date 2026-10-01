package com.kv.lcoptimized.bits;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 268 · Missing Number + CtCI 17.4 "Missing Number" (bit-fetch only)
 *                                                         [was: lc/L66_FindMissingElementBinary - fully commented out]
 *
 * LC 268 : XOR of indices 0..n and all values leaves the missing one. O(n), O(1), no overflow
 *          (a sum formula also works but can overflow in other languages).
 * CtCI   : you may only read bit j of A[i]. Look at the least significant bit: the missing
 *          number's LSB is whichever parity is under-represented. Recurse on that half only.
 *          Total bit reads = n + n/2 + n/4 + ... = O(n).
 * Changed : the original code was pseudocode in comments (it wouldn't compile); here it's
 *           implemented, with the fetch cost counted to demonstrate O(n).
 */
public class LC0268_MissingNumber {

    public static int missingNumber(int[] nums) {
        int x = nums.length;
        for (int i = 0; i < nums.length; i++) {
            x ^= i ^ nums[i];
        }
        return x;
    }

    /** Counts calls to fetch() so tests can verify O(n) bit reads. */
    static int fetches;

    static int fetch(int value, int bit) {
        fetches++;
        return (value >>> bit) & 1;
    }

    public static int missingByBitFetch(List<Integer> input, int n) {
        return find(input, 0);
    }

    private static int find(List<Integer> input, int bit) {
        if (input.isEmpty()) {
            return 0;                                      // remaining high bits of the answer are 0
        }
        List<Integer> ones = new ArrayList<>(input.size() / 2 + 1);
        List<Integer> zeros = new ArrayList<>(input.size() / 2 + 1);
        for (int v : input) {
            if (fetch(v, bit) == 0) {
                zeros.add(v);
            } else {
                ones.add(v);
            }
        }
        // In the full range 0..n, #zeros-at-this-bit >= #ones; the missing value's bit is the
        // side that ended up with fewer than it should.
        if (zeros.size() <= ones.size()) {
            return find(zeros, bit + 1) << 1;          // missing bit is 0
        }
        return (find(ones, bit + 1) << 1) | 1;          // missing bit is 1
    }

    public static void main(String[] args) {
        Check.eq(missingNumber(new int[] {3, 0, 1}), 2);
        Check.eq(missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}), 8);
        int wrong = 0;
        for (int n : new int[] {1, 2, 7, 8, 100, 1000}) {
            for (int missing = 0; missing <= n; missing++) {
                List<Integer> in = new ArrayList<>();
                for (int v = 0; v <= n; v++) {
                    if (v != missing) {
                        in.add(v);
                    }
                }
                fetches = 0;
                if (missingByBitFetch(in, n) != missing || fetches > 2 * n + 2) {
                    wrong++;
                }
            }
        }
        Check.eq(wrong, 0);
    }
}
