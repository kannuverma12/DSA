package com.kv.lc;

/**
 *  @author karanverma
 *  Implement pow(x, n), which calculates x raised to the power n (xn).
 *  Input: 2.00000, 10
 *  Output: 1024.00000
 *  Note :  -100.0 < x < 100.0, n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class L11_ImplementPowerOperation {
    
    /*
     * This is a great example to illustrate how to solve a problem during a technical interview. 
     * The first and second solution exceeds time limit; the third and fourth are accepted.
     */

    public static void main(String[] args) {

        long t = System.currentTimeMillis();
        
        System.out.println("Pow 1 : "+pow1(2, 10)+", Pow1 time : "+(System.currentTimeMillis()-t));
        
        t = System.currentTimeMillis();
        System.out.println("Pow 2 : "+pow2(2, 10)+", Pow2 time : "+(System.currentTimeMillis()-t));
        
        t = System.currentTimeMillis();
        System.out.println("Pow 3 : "+pow3(2, 10)+", Pow3 time : "+(System.currentTimeMillis()-t));
    }
    
    // Most effective
    public static double pow2(int x, int n) {
        if (x == 0)
            return 0;
        if (n == 0)
            return 1;
        if (n < 0)
            return 1 / pow2(x, -n);
        return x * pow2(x, n - 1);
    }
    
    //taking longest time
    static double pow1(double x, int n) {
        if (n == 0)
            return 1;
        double half = pow1(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else if (n > 0)
            return half * half * x;
        else
            return half * half / x;
    }

    

    public static double pow3(int x, int n) {
        double result = 1;

        while (n > 0) {
            if (n % 2 != 0) {
                result = result * x;
            }
            x = x * x;
            n = n / 2;
        }

        return result;
    }

}
