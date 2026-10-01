package com.kv.lcoptimized.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Minimal assertion helper so every solution's main() doubles as a self-test.
 * Run {@link RunAll} to execute every main() in this package tree.
 */
public final class Check {

    private static int passed;
    private static int failed;

    private Check() {
    }

    public static void eq(Object actual, Object expected) {
        if (Objects.deepEquals(actual, expected)) {
            passed++;
            return;
        }
        fail("expected=" + str(expected) + " actual=" + str(actual));
    }

    public static void isTrue(boolean condition) {
        eq(condition, true);
    }

    public static void near(double actual, double expected) {
        if (Math.abs(actual - expected) < 1e-5) {
            passed++;
            return;
        }
        fail("expected=" + expected + " actual=" + actual);
    }

    /** Order-insensitive comparison of two collections (elements compared by their deep string form). */
    public static void eqUnordered(Collection<?> actual, Collection<?> expected) {
        eq(canonical(actual), canonical(expected));
    }

    public static int passed() {
        return passed;
    }

    public static int failed() {
        return failed;
    }

    private static List<String> canonical(Collection<?> c) {
        List<String> out = new ArrayList<>();
        for (Object o : c) {
            out.add(str(o));
        }
        Collections.sort(out);
        return out;
    }

    private static void fail(String msg) {
        failed++;
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        System.out.println("  FAIL " + caller.getClassName() + ":" + caller.getLineNumber() + " " + msg);
    }

    private static String str(Object o) {
        String s = Arrays.deepToString(new Object[] {o});
        return s.substring(1, s.length() - 1);
    }
}
