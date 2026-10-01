package com.kv.lcoptimized.strings;

import com.kv.lcoptimized.common.Check;

/**
 * LC 9 · Palindrome Number (Easy)                         [was: lc/easy/P3_CheckIfNumberIsPalindrome]
 *
 * Pattern : reverse only the second half; stop when reversed >= remaining.
 * Optimal : O(log10 n) time, O(1) space, and it cannot overflow.
 * Changed : the original reversed the whole number (can overflow int for e.g. 2147447412
 *           reversed) and had an unused local `num = -121`. Numbers ending in 0 (except 0)
 *           are rejected up front.
 */
public class LC0009_PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int half = 0;
        while (x > half) {
            half = half * 10 + x % 10;
            x /= 10;
        }
        return x == half || x == half / 10;    // odd digit count: drop the middle digit
    }

    public static void main(String[] args) {
        Check.isTrue(isPalindrome(121));
        Check.isTrue(!isPalindrome(-121));
        Check.isTrue(!isPalindrome(10));
        Check.isTrue(isPalindrome(0));
        Check.isTrue(isPalindrome(1221));
        Check.isTrue(!isPalindrome(Integer.MAX_VALUE));
    }
}
