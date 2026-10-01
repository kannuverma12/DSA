package com.kv.lcoptimized.ds;

import com.kv.lcoptimized.common.Check;

/**
 * Segment Tree - LC 307 · Range Sum Query - Mutable (Medium)   [NEW]
 *
 * Iterative bottom-up segment tree (size 2n): leaves at [n, 2n), node i = tree[2i] + tree[2i+1].
 *   update : O(log n), query [l, r] : O(log n), build : O(n). No recursion, and short to write.
 *
 * When a Fenwick tree is enough (prefix-invertible ops like sum/xor) use it (see FenwickTree).
 * Segment trees also handle min/max/gcd and, with lazy propagation, range UPDATES
 * (e.g. "add v to [l, r]", LC 699 Falling Squares, LC 715 Range Module, LC 218 Skyline).
 */
public class SegmentTree {

    private final int n;
    private final long[] tree;

    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new long[2 * n];
        for (int i = 0; i < n; i++) {
            tree[n + i] = nums[i];
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int index, int value) {
        int i = index + n;
        tree[i] = value;
        for (i /= 2; i > 0; i /= 2) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    /** Sum of nums[left..right], inclusive. */
    public long sumRange(int left, int right) {
        long sum = 0;
        for (int l = left + n, r = right + n + 1; l < r; l /= 2, r /= 2) {   // half-open [l, r)
            if ((l & 1) == 1) {
                sum += tree[l++];
            }
            if ((r & 1) == 1) {
                sum += tree[--r];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int[] {1, 3, 5});
        Check.eq(st.sumRange(0, 2), 9L);
        st.update(1, 2);
        Check.eq(st.sumRange(0, 2), 8L);
        Check.eq(st.sumRange(1, 1), 2L);

        java.util.Random rnd = new java.util.Random(1);
        int[] a = rnd.ints(257, -1000, 1000).toArray();
        SegmentTree big = new SegmentTree(a);
        int bad = 0;
        for (int q = 0; q < 2000; q++) {
            if (rnd.nextBoolean()) {
                int i = rnd.nextInt(a.length);
                a[i] = rnd.nextInt(2000) - 1000;
                big.update(i, a[i]);
            } else {
                int l = rnd.nextInt(a.length);
                int r = l + rnd.nextInt(a.length - l);
                long expect = 0;
                for (int i = l; i <= r; i++) {
                    expect += a[i];
                }
                if (big.sumRange(l, r) != expect) {
                    bad++;
                }
            }
        }
        Check.eq(bad, 0);
    }
}
