package com.kv.lc;

public class L79_WildcardMatching {

    public static void main(String[] args) {
        System.out.println("Is MAatching ? " + isValidMatch("aab", "?*"));
    }

    static boolean isValidMatch(String str, String pattern) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;

        while (i < str.length()) {
            if (j < pattern.length() && (pattern.charAt(j) == '?' || pattern.charAt(j) == str
                    .charAt(i))) {
                ++i;
                ++j;
            } else if (j < pattern.length() && pattern.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex + 1;
                iIndex++;
            } else {
                return false;
            }
        }

        while (j < pattern.length() && pattern.charAt(j) == '*') {
            ++j;
        }

        return j == pattern.length();
    }
}
