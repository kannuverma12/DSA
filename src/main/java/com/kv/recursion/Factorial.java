package com.kv.recursion;

public class Factorial {
	
	public static void main(String... strings ) {
		long res = factorial(20);
		System.out.println("res = "+res);
	}

	private static long factorial(long i) {
		// TODO Auto-generated method stub
		
		if(i == 0 || i==1)
			return 1;
		else
			return i * factorial(i-1);
		
	}

}
