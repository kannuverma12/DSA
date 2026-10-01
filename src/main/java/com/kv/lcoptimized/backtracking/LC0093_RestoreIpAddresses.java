package com.kv.lcoptimized.backtracking;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 93 · Restore IP Addresses (Medium)                   [was: lc/RestoreIpAddress]
 *
 * Pattern : 4-level backtracking, segment length 1..3, with length pruning: the remaining
 *           characters must fit in the remaining segments (between 1 and 3 chars each).
 * Complexity: at most 3^4 = 81 leaves, so O(1) for valid-length input.
 * Changed : builds the answer directly (no List<List<String>> then join), parses digits
 *           manually instead of Integer.valueOf(substring), and prunes by remaining length on
 *           both sides.
 */
public class LC0093_RestoreIpAddresses {

    public static List<String> restoreIpAddresses(String s) {
        List<String> out = new ArrayList<>();
        if (s.length() >= 4 && s.length() <= 12) {
            dfs(s, 0, 0, new int[4], out);
        }
        return out;
    }

    private static void dfs(String s, int pos, int seg, int[] parts, List<String> out) {
        int remaining = s.length() - pos;
        if (seg == 4) {
            if (remaining == 0) {
                out.add(parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3]);
            }
            return;
        }
        if (remaining < 4 - seg || remaining > 3 * (4 - seg)) {
            return;
        }
        int value = 0;
        for (int len = 1; len <= 3 && pos + len <= s.length(); len++) {
            value = value * 10 + (s.charAt(pos + len - 1) - '0');
            if (value > 255 || (len > 1 && s.charAt(pos) == '0')) {
                break;
            }
            parts[seg] = value;
            dfs(s, pos + len, seg + 1, parts, out);
        }
    }

    public static void main(String[] args) {
        Check.eq(restoreIpAddresses("25525511135"), Arrays.asList("255.255.11.135", "255.255.111.35"));
        Check.eq(restoreIpAddresses("0000"), Arrays.asList("0.0.0.0"));
        Check.eq(restoreIpAddresses("101023"),
                Arrays.asList("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"));
        Check.eq(restoreIpAddresses("10026249"),
                Arrays.asList("10.0.26.249", "100.2.6.249", "100.2.62.49", "100.26.2.49", "100.26.24.9"));
    }
}
