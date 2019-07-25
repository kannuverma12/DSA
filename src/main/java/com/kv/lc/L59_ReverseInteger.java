package com.kv.lc;

public class L59_ReverseInteger {

    public static void main(String[] args) {
        System.out.println("Reverse : "+reverse(123));
    }


    /*
    However, this approach is dangerous, because the statement \text{temp} = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop can cause overflow.

        Luckily, it is easy to check beforehand whether or this statement would cause an overflow.

        To explain, lets assume that \text{rev}rev is positive.

        If temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop causes overflow, then it must be that \text{rev} \geq \frac{INTMAX}{10}rev≥
        10
        INTMAX
        ​

        If \text{rev} > \frac{INTMAX}{10}rev>
        10
        INTMAX
        ​
         , then temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop is guaranteed to overflow.
        If \text{rev} == \frac{INTMAX}{10}rev==
        10
        INTMAX
        ​
         , then temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop will overflow if and only if \text{pop} > 7pop>7
        Similar logic can be applied when \text{rev}rev is negative.
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
