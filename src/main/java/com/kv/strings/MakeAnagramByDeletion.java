package com.kv.strings;

import java.util.HashMap;
import java.util.Map;

public class MakeAnagramByDeletion {

	public static int numberNeeded(String first, String second) {
		char[] ch1 = first.toCharArray();
		char[] ch2 = second.toCharArray();
		int count = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int j = 0; j < ch2.length; j++) {
			char t = ch2[j];
			if (map.get(t) != null) {
				map.put(t, map.get(t) + 1);
			} else {
				map.put(t, 1);
			}
		}

		for (int j = 0; j < ch1.length; j++) {
			char t = ch1[j];
			if (map.get(t) != null) {
				if (map.get(t) != 0)
					map.put(t, map.get(t) - 1);
				else
					count++;
			} else {
				count++;
			}
		}
		int temp = 0;
		for (Character c : map.keySet()) {
			temp += map.get(c);
		}
		System.out.println(temp + " " + count);
		return temp + count;
	}

	public static void main(String[] args) {
		String input1 = "spot";
		String input2 = "poft";
		System.out.println("No of characters deleted is " + numberNeeded(input1, input2));
	}
}
