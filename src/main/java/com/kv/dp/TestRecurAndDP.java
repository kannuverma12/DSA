package com.kv.dp;

public class TestRecurAndDP {
	//static int f[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int rn = fib(10);
		
		System.out.println("rn = "+rn);
		
		int dn = fib2(10);
		System.out.println("dn = "+dn);
		
		

	}
	
	static int fib (int n) {
        if (n < 2)
            return 1;
        return fib(n-1) + fib(n-2);
    }
	
	static int fib2 (int n) {
		int f[] = new int[n];
		f[0] = 0;
		f[1] = 1;
		int i;

        for (i = 2; i < n; i++)
        {
           /* Add the previous 2 numbers in the series
             and store it */
            f[i] = f[i-1] + f[i-2];
            
            System.out.println("i = "+i+", f["+i+"] = "+f[i]);
        }
        System.out.println("n = "+n+", f["+n+"] = "+f[n-1]);

        return f[n-1];
    }

}
