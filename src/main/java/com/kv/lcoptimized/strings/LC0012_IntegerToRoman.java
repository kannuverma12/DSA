package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 12 · Integer to Roman (Medium) + LC 13 · Roman to Integer (Easy)
 *                                  [was: lc/medium/L60_IntegerToRoman, lc/L61_RomanToInteger]
 *
 * intToRoman : greedy over 13 value/symbol pairs (including subtractive forms). O(1) since
 *              the input is at most 3999.
 * romanToInt : add each symbol; subtract it if a larger symbol follows.
 * Changed : parallel arrays instead of a TreeMap rebuilt on every call (floorKey was O(log 13)
 *           plus boxing). A switch replaces HashMap<Character,Integer> lookups.
 */
public class LC0012_IntegerToRoman {

    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < VALUES.length && num > 0; i++) {
            while (num >= VALUES[i]) {
                num -= VALUES[i];
                sb.append(SYMBOLS[i]);
            }
        }
        return sb.toString();
    }

    public static int romanToInt(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int v = value(s.charAt(i));
            if (i + 1 < s.length() && v < value(s.charAt(i + 1))) {
                total -= v;
            } else {
                total += v;
            }
        }
        return total;
    }

    private static int value(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Not a roman numeral: " + c);
        }
    }

    public static void main(String[] args) {
        Check.eq(intToRoman(3), "III");
        Check.eq(intToRoman(58), "LVIII");
        Check.eq(intToRoman(1994), "MCMXCIV");
        Check.eq(romanToInt("XVII"), 17);
        Check.eq(romanToInt("MCMXCIV"), 1994);
        for (int n = 1; n <= 3999; n++) {
            if (romanToInt(intToRoman(n)) != n) {
                Check.eq(romanToInt(intToRoman(n)), n);
            }
        }
    }
}
