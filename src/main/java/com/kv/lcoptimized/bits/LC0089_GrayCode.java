package com.kv.lcoptimized.bits;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 89 · Gray Code (Medium)                              [was: lc/medium/L28_GrayCode]
 *
 * Pattern : closed form g(i) = i ^ (i >> 1). Adjacent i differ by a carry chain; XOR with the
 *           shift leaves exactly one bit changed.
 * Optimal : O(2^n) time (output), O(1) extra.
 * Changed : the original used a recursive reflect-and-prefix construction (also correct, but
 *           recursive and harder to state). Both are worth knowing: reflection is how you
 *           PROVE the property.
 */
public class LC0089_GrayCode {

    public static List<Integer> grayCode(int n) {
        List<Integer> out = new ArrayList<>(1 << n);
        for (int i = 0; i < (1 << n); i++) {
            out.add(i ^ (i >> 1));
        }
        return out;
    }

    public static void main(String[] args) {
        Check.eq(grayCode(2), Arrays.asList(0, 1, 3, 2));
        Check.eq(grayCode(0), Arrays.asList(0));
        List<Integer> g = grayCode(10);
        boolean oneBit = true;
        for (int i = 1; i < g.size(); i++) {
            oneBit &= Integer.bitCount(g.get(i) ^ g.get(i - 1)) == 1;
        }
        Check.isTrue(oneBit);
    }
}
