package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 273 · Integer to English Words (Hard)                [was: lc/ConvertIntegerToWords]
 *
 * Pattern : process in chunks of 3 digits (thousand, million, billion). Each chunk is
 *           handled by one helper for numbers under 1000.
 * Optimal : O(number of digits).
 * Changed : the original used the Indian system (crore/lakh), had typos ("forteen",
 *           "ninteen"), had a second method with logic inside an exception handler, and
 *           concatenated strings with stray spaces. Both numbering systems now share one
 *           helper for numbers under 1000.
 */
public class LC0273_IntegerToEnglishWords {

    private static final String[] BELOW_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
        "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
        "Seventy", "Eighty", "Ninety"};

    /** International: Billion / Million / Thousand. */
    public static String numberToWords(int num) {
        return spell(num, new long[] {1_000_000_000L, 1_000_000L, 1_000L},
                new String[] {"Billion", "Million", "Thousand"});
    }

    /** Indian: Crore / Lakh / Thousand (what the original file implemented). */
    public static String numberToWordsIndian(long num) {
        return spell(num, new long[] {10_000_000L, 100_000L, 1_000L},
                new String[] {"Crore", "Lakh", "Thousand"});
    }

    private static String spell(long num, long[] units, String[] names) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units.length; i++) {
            if (num >= units[i]) {
                long chunk = num / units[i];
                // Indian crore can exceed 999 -> recurse on it with the same system.
                sb.append(chunk < 1000 ? below1000((int) chunk) : spell(chunk, units, names))
                        .append(' ').append(names[i]).append(' ');
                num %= units[i];
            }
        }
        sb.append(below1000((int) num));
        return sb.toString().trim();
    }

    private static String below1000(int n) {
        StringBuilder sb = new StringBuilder();
        if (n >= 100) {
            sb.append(BELOW_20[n / 100]).append(" Hundred ");
            n %= 100;
        }
        if (n >= 20) {
            sb.append(TENS[n / 10]).append(' ');
            n %= 10;
        }
        if (n > 0) {
            sb.append(BELOW_20[n]);
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Check.eq(numberToWords(123), "One Hundred Twenty Three");
        Check.eq(numberToWords(12345), "Twelve Thousand Three Hundred Forty Five");
        Check.eq(numberToWords(1234567891), "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");
        Check.eq(numberToWords(1000010), "One Million Ten");
        Check.eq(numberToWords(0), "Zero");
        Check.eq(numberToWordsIndian(10120000), "One Crore One Lakh Twenty Thousand");
        Check.eq(numberToWordsIndian(12345678901L), "One Thousand Two Hundred Thirty Four Crore Fifty Six Lakh Seventy Eight Thousand Nine Hundred One");
    }
}
