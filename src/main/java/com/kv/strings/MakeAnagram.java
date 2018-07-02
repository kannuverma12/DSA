package com.kv.strings;

import java.util.Scanner;

public class MakeAnagram {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		for (int a0 = 0; a0 < t; a0++) {
			String str = in.next();
			String aString = "";
			// line = br.readLine();
			String bString = "";

			int[] counts = new int[26];
			char a = 'a';

			if (str.length() % 2 == 0) {
				aString = str.substring(0, str.length() / 2);

				bString = str.substring(str.length() / 2);

				char[] charArray = aString.toCharArray();

				for (int i = 0; i < charArray.length; i++) {
					// System.out.println("charArray[i] = "+charArray[i]);
					counts[charArray[i] - a]++;
				}
				// System.out.println("After adding from a string");
				for (int i = 0; i < counts.length; i++) {
					// System.out.println(i+" "+ counts[i]+" ");
				}
				charArray = bString.toCharArray();
				for (int i = 0; i < charArray.length; i++) {
					// System.out.println("In b charArray[i] = "+charArray[i]);
					if (counts[charArray[i] - a] > 0)
						counts[charArray[i] - a]--;
				}

				// System.out.println("After adding from b string");
				for (int i = 0; i < counts.length; i++) {
					// System.out.println(i+" "+ counts[i]+" ");
				}

				int sum = 0;
				for (int i = 0; i < counts.length; i++) {
					sum += Math.abs(counts[i]);

				}
				if (sum > aString.length() || sum > bString.length())
					sum = aString.length();
				System.out.println(sum);
			} else {
				System.out.println("-1");
			}
		}

	}

}
