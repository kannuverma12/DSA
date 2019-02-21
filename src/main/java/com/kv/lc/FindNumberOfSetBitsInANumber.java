package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Find the number of set bits in a binary representation of a number.
 *  
 *  
 *  First approach you can make use of is to count every set bit of the number by making use of shifting operations.

Second approach: You can solve this by using AND operation.

There is an Algorithm known as Brian Kernighan’s Algorithm. It states that if we perform AND operation of the number with number-1, we actually unset it’s rightmost bit. So keep on doing this until number becomes zero.

number = number & (number-1), till number != 0 and increment the counter at each step.

Example:

Number = 11 = 1011

Number-1=10 = 1010

count =0

Number = 1011 & 1010 => 1010 and count=1

Number =1010 & 1001 => 1000 and count=2

Number = 1000 & 0111=> 0000 and count=3

As Number is now Zero. Therefore stop.
 */
public class FindNumberOfSetBitsInANumber {
    
    /*
     * Conclusion: number of set bits in a number: number = number & (number-1), till number != 0 and increment the counter at each step. Finally return the counter.
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
