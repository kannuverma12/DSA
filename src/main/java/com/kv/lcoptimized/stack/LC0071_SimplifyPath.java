package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * LC 71 · Simplify Path (Medium)                          [was: lc/medium/L19_SimplifyPath]
 *
 * Pattern : split on '/', push names, pop on "..", ignore "" and ".".
 * Optimal : O(n) time and space.
 * Changed : the original trimmed trailing slashes with repeated substring() (O(n^2) worst case),
 *           stored "/name" tokens, and then walked the stack backwards with a "back" counter.
 *           A deque read from the bottom is much simpler.
 */
public class LC0071_SimplifyPath {

    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String part : path.split("/")) {
            if (part.equals("..")) {
                stack.pollLast();
            } else if (!part.isEmpty() && !part.equals(".")) {
                stack.addLast(part);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (Iterator<String> it = stack.iterator(); it.hasNext(); ) {
            sb.append('/').append(it.next());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(simplifyPath("/home/"), "/home");
        Check.eq(simplifyPath("/a/./b/../../c/"), "/c");
        Check.eq(simplifyPath("/../"), "/");
        Check.eq(simplifyPath("/home//foo/../fcs"), "/home/fcs");
        Check.eq(simplifyPath("/.../a/../b"), "/.../b");
    }
}
