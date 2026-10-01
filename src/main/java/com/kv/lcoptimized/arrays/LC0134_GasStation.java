package com.kv.lcoptimized.arrays;

import com.kv.lcoptimized.common.Check;

/**
 * LC 134 · Gas Station (Medium)                           [was: lc/medium/GasStation]
 *
 * Pattern : greedy with a proof. If the tank goes negative going from s to i, then no
 *           station in [s, i] can be the start, so jump to i+1. A valid start exists iff
 *           total gas >= total cost.
 * Optimal : O(n) time, O(1) space.
 * Changed : the logic was already optimal; it's written more compactly here. Be ready to
 *           explain the proof - that is what's being graded.
 */
public class LC0134_GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        Check.eq(canCompleteCircuit(new int[] {1, 2, 3, 4, 5}, new int[] {3, 4, 5, 1, 2}), 3);
        Check.eq(canCompleteCircuit(new int[] {2, 3, 4}, new int[] {3, 4, 3}), -1);
    }
}
