package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Fetch the bit at position i from left in the binary representation of a number.
 *  
 */
public class CheckIfIthBitIsSet {
    /*
     * Remember, we discussed about shifting operators. Our approach would include doing bitwise AND between 
     * the number and 1 left shifted by given position to extract the bit at that position from the number. 
     * 
     * If we left shift 1 by 1 position, we get 10. Our given number is 1010. What if, we perform an AND operation 
     * between the number 1010 and 10.
     *      1010 & 10 = 10
     * 
     * result is non-zero and this suggests that the bit at position 2 of 1010 must be non-zero because of 
     * which when we AND it with 1, we get 1 at 2nd position in the result.
     * 
     * if the number was 1000 and we AND it with 10, result would be 1000 & 10= 0000, clearly stating that 
     * the bit at that position 2 is unset.
     * 
     * To check if a bit is set or unset at a given position in a number, do this
     * 
     * Conclusion: If number & (1<<(position-1))!=0: bit is set
     */

    
    // Conclusion: If number & (1<<(position-1))!=0: bit is set
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
