package com.kv.algos;

/*
 * Given a range, print all numbers having unique digits
 */
public class NumbersHavingUniqueDigits {

	public static void main(String[] args) {

		int l = 10, r = 20;
		printUniqueDigits(l, r);
	}

	
	
	/*
	 * As the problem is pretty simple, the only thing to be done is :-
		1- Find the digits one by one and keep marking visited digits.
		2- If all digits occurs one time only then print that number.
		3- Else not.
	 */
	private static void printUniqueDigits(int l, int r) {
		for(int i = l ; i<=r; i++) {
			int num = i;
			boolean[] visited =  new boolean[10];
			
			// Find digits and maintain its hash
			while(num != 0) {
				if(visited[num %10])
					break;
				visited[num % 10] = true;
				num = num / 10;
				
			}
			// num will be 0 only when above loop
            // doesn't get break that means the
            // number is unique so print it.	
			if(num == 0)
				System.out.println(i+" ");
		}
	}

}
