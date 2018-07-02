package com.kv.strings;

import java.util.HashMap;



import java.util.Map;



/*
 * 
 * We start scanning the sentence from left. As we find a valid word, we need to check whether 
 * rest of the sentence can make valid words or not. Because in some situations the first found 
 * word from left side can leave a remaining portion which is not further separable. So in that 
 * case we should come back and leave the current found word and keep on searching for the next word.
 *  And this process is recursive because to find out whether the right portion is separable or not, 
 *  we need the same logic. So we will use recursion and backtracking to solve this problem. To keep 
 *  track of the found words we will use a stack. Whenever the right portion of the string does not make 
 *  valid words, we pop the top string from stack and continue finding.
 * 
 * 
 */
public class MakeSentenceFromALongWord {
	private static Map<String, Integer> dictionary = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String longWord = "ramisagoodboy";
		
		
		
		dictionary.put("ram", 0);
		dictionary.put("is", 0);
		dictionary.put("a", 0);
		dictionary.put("good", 0);
		dictionary.put("boy", 0);
		
		wordbreak(longWord);
		
		//makeSentenceFromLongWord(longWord);

	}

	private static boolean makeSentenceFromLongWord(String longWord) {
		int size = longWord.length();
		int start = 0;
		int end = size;
		for(int i=0;i<size;i++) {
			if(dictionary.get(longWord.substring(start, i)) != null && makeSentenceFromLongWord(longWord.substring(i, end-1))) {
				System.out.println(longWord.substring(start, i));
				return true;
				
			}else {
				makeSentenceFromLongWord(longWord.substring(start, i));
			}
			
		}
		return false;
		
	}
	
	
	private static void wordbreak(String str) {
		wordbreakUtil(str, str.length(),"");
		
	}

	private static void wordbreakUtil(String str, int n, String result) {
		// TODO Auto-generated method stub
		
		for (int i=1; i<=n; i++) {
			System.out.println("i = "+i+", n = "+n);
			String prefix = str.substring(0, i);
			
			if(dictionary.get(prefix) != null) {
				System.out.println("prefix "+prefix);
				if(i == n) {
					result += prefix;
					System.out.println("Result : "+result);
					return;
					
					
				}
				System.out.println("str = "+str);
				wordbreakUtil(str.substring(i, n-i), n-i,
	                    result + prefix + " ");
				
			}
			
		}
		
	}

}
