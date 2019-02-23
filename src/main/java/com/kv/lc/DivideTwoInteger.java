package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Divide two integers without using multiplication, division and mod operator. 
 *  If it is overflow, return MAX_INT.
 */
public class DivideTwoInteger {
    /*
     * This problem can be solved based on the fact that any number can be converted to the format 
     * of the following:
     * num = a_0*2^0 + a_1*2^1 + a_2*2^2 + ... + a_n*2^n
     */
    
    /*
     *  dividend = quotient * divisor + remainder
     *  
     *  As every number can be represented in base 2(0 or 1), represent the quotient in binary form by 
     *  using shift operator as given below :
     *  1. Determine the most significant bit in the quotient. This can easily be calculated by iterating 
     *     on the bit position i from 31 to 1.
     *  2. Find the first bit for which divisor << i is less than dividend and keep updating the ith bit 
     *     position for which it is true.
     *  3. Add the result in temp variable for checking the next position such that 
     *     (temp + (divisor << i) ) is less than dividend.
     *  4. Return the final answer of quotient after updating with corresponding sign
     * 
     * 
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Divide result = " + divideUsingBits(10, 2));
        
        System.out.println("Divide result 2 = " + division(10, 2));
    }

    public static int divideUsingBits(int dividend, int divisor) {
        // handle special cases
        if (divisor == 0)
            return Integer.MAX_VALUE;
        if (divisor == -1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        // get positive values
        long pDividend = Math.abs((long) dividend);
        long pDivisor = Math.abs((long) divisor);

        int result = 0;
        while (pDividend >= pDivisor) {
            // calculate number of left shifts
            int numShift = 0;
            while (pDividend >= (pDivisor << numShift)) {
                numShift++;
            }

            // dividend minus the largest shifted divisor
            result += 1 << (numShift - 1);
            pDividend -= (pDivisor << (numShift - 1));
        }

        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return result;
        } else {
            return -result;
        }
    }
    
    static int division(int divident, int divisor) {
        int curr = 1;
        int result = 0;
        if (divisor > divident)
            return 0;

        else if (divisor == divident)
            return 1;

        while (divisor < divident) {
            divisor <<= 1;
            curr <<= 1;
        }

        divisor >>= 1;
        curr >>= 1;

        while (curr != 0) {
            if (divident >= divisor) {
                divident -= divisor;
                result |= curr;
            }
            curr >>= 1;
            divisor >>= 1;
        }
        return result;
    }

}
