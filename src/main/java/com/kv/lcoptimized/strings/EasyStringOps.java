package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * Warm-up string problems, grouped in one file.
 *   LC 709  · To Lower Case          [was: lc/L64_ToLowerCaseImplementation]
 *   LC 1119 · Remove Vowels          [was: lc/L62_RemoveVowelFromAString]
 *
 * Changed : toLowerCase printed a debug line and had redundant range checks. It now uses
 *           c | 32 on 'A'..'Z' (ASCII upper/lower differ only in bit 5). removeVowels used
 *           replaceAll with a regex, which compiles the pattern on every call; a single
 *           StringBuilder pass is O(n) with no regex engine.
 */
public class EasyStringOps {

    public static String toLowerCase(String s) {
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 'A' && a[i] <= 'Z') {
                a[i] |= 32;
            }
        }
        return new String(a);
    }

    public static String removeVowels(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ("aeiouAEIOU".indexOf(c) < 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Check.eq(toLowerCase("KaRaN-42"), "karan-42");
        Check.eq(removeVowels("leetcodeisacommunityforcoders"), "ltcdscmmntyfrcdrs");
        Check.eq(removeVowels("AEIOU"), "");
    }
}
