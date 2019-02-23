package com.kv.lc;

/**
 * 
 * @author karanverma
 *
 *  Check if a given number is a power of 2
 *  
 *  
 *  There is one more way we can go about it. Suppose we have to check for number 8. 8= 1000 and 8–1=7=0111
 *  if we AND the number with number-1, we will get 0, as in the above case.
 *  But if number=3, number-1=2
 *  3=11 and 2=10, 11 & 10 = 10 != 0
 *  Conclusion: Number is power of 2 if Number & (Number-1)==0
 */
public class CheckIfANumberIsPowerOf2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 8;
        System.out.println("Number "+n+" is power of 2 ? "+checkIfPowerOf2(n));

    }
    
    static boolean checkIfPowerOf2(Integer number) {
        return ((number & (number-1)) == 0);
    }

}
