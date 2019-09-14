package com.kv.algos;



/*
 * Starting from right, 1 at ith position shows that the ith element of the set is present 
 * as 0 shows that the element is absent. Therefore, what we have to do is just generate 
 * the binary numbers from 0 to 2^n – 1, where n is the length of the set or the numbers of 
 * elements in the set.
 * 
 * 
 */
public class FindAllSubsetOfGivenSet {

	public static void main(String[] args) {
		char set[] = {'a', 'b', 'c'};
        //printSubsets(set);

	}

	//same code present in LC package

//	private static void printSubsets(char[] set) {
//		int n = set.length;
//		System.out.println("n = "+n+" << = "+(1<<n));
//		// Run a loop for printing all 2^n subsets one by one
//		for (int i = 0; i < (1<<n); i++) {
//			System.out.print("{");
//			for (int j = 0; j < n; j++) {
//
//				// (1<<j) is a number with jth bit 1 so when we 'and' them with the
//                // subset number we get which numbers are present in the subset and which are not
//				if ((i & (1 << j)) > 0)
//                    System.out.print(set[j] + "");
//
//			}
//			System.out.println("}");
//
//
//		}
//	}

}
