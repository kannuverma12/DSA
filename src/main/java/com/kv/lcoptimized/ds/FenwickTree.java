package com.kv.lcoptimized.ds;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fenwick / Binary Indexed Tree + LC 315 · Count of Smaller Numbers After Self (Hard)   [NEW]
 *
 * tree[i] covers (i - lowbit(i), i]. add: climb with i += i & -i; prefix: descend with i -= i & -i.
 * Both O(log n), and it's about 10 lines - the fastest structure to write for prefix sums with
 * point updates.
 *
 * LC 315: scan from the right; for each x, answer = count of already-seen values < x =
 * prefix(rank(x) - 1); then add(rank(x), 1). Coordinate-compress values to ranks first.
 * O(n log n). Merge sort counting inversions is the alternative solution to mention.
 * Same idea: count inversions, LC 493 Reverse Pairs, LC 327 Count of Range Sum.
 */
public class FenwickTree {

    private final long[] tree;

    public FenwickTree(int n) {
        tree = new long[n + 1];                     // 1-indexed
    }

    public void add(int i, long delta) {
        for (i++; i < tree.length; i += i & -i) {
            tree[i] += delta;
        }
    }

    /** Sum of positions [0, i]. */
    public long prefix(int i) {
        long s = 0;
        for (i++; i > 0; i -= i & -i) {
            s += tree[i];
        }
        return s;
    }

    public long range(int l, int r) {
        return prefix(r) - (l == 0 ? 0 : prefix(l - 1));
    }

    public static List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        FenwickTree bit = new FenwickTree(sorted.length);
        Integer[] out = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int rank = Arrays.binarySearch(sorted, nums[i]);
            out[i] = rank == 0 ? 0 : (int) bit.prefix(rank - 1);
            bit.add(rank, 1);
        }
        return new ArrayList<>(Arrays.asList(out));
    }

    public static void main(String[] args) {
        FenwickTree ft = new FenwickTree(5);
        for (int i = 0; i < 5; i++) {
            ft.add(i, i + 1);                        // [1, 2, 3, 4, 5]
        }
        Check.eq(ft.prefix(4), 15L);
        Check.eq(ft.range(1, 3), 9L);
        ft.add(2, 10);
        Check.eq(ft.range(2, 2), 13L);
        Check.eq(countSmaller(new int[] {5, 2, 6, 1}), Arrays.asList(2, 1, 1, 0));
        Check.eq(countSmaller(new int[] {-1, -1}), Arrays.asList(0, 0));
    }
}
