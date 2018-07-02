package com.kv.dp;


/**
 * 
 * @author karan.verma
 *
 */

/*
 * Problem Statement: On a positive integer, you can perform any one of the following 3 steps. 
 * 1.) Subtract 1 from it. ( n = n - 1 )  , 
 * 2.) If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2  )  , 
 * 3.) If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3  ). 
 * Now the question is, given a positive integer n, find the minimum number of steps that takes n to 1

	eg: 1.)For n = 1 , output: 0       
		2.) For n = 4 , output: 2  ( 4  /2 = 2  /2 = 1 )    
		3.)  For n = 7 , output: 3  (  7  -1 = 6   /3 = 2   /2 = 1 )
 */

public class MinimumStepsToTakeNTo1 {
	static int[] cache;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		cache =  new int[n+1];
		for(int i = 0; i<=n; i++) {
			cache[i] = -1;
		}
		
		int steps = getMinSteps(n);
		System.out.println("Min steps = "+steps);
		
		int stepsDP = getMinStepsDP(n);
		
		System.out.println("Min steps DP = "+stepsDP);

	}

	// DP - Bottom-Up DP
	private static int getMinStepsDP(int n) {
		int[] dpCache = new int[n+1];
		int i;
		dpCache[1] = 0;
		for(i=2; i<= n; i++) {
			dpCache[i] = 1 + dpCache[i-1];
			if(i % 2 == 0)
				dpCache[i] = Math.min(dpCache[i] , 1+dpCache[i/2]);
			if(i % 3 == 0)
				dpCache[i] = Math.min(dpCache[i] , 1+dpCache[i/3]);
		}
		return dpCache[n];
	}


	// Memoization methods - Top Down
	private static int getMinSteps(int n) {
		if(n == 0) return 0;
		if(n == 1) return 0;
		
		if(cache[n] != -1) 
			return cache[n];
		
		int r = 1 + getMinSteps(n-1);
		
		if(n % 2 == 0)
			r = Math.min(r, 1 + getMinSteps(n/2));
		if(n % 3 == 0)
			r = Math.min(r, 1 + getMinSteps(n/3));

		cache[n] = r;
		
		return r;
 	}

}
