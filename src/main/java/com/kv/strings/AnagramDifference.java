package com.kv.strings;


/*
 * There were 2 strings given we have to find out the maximum number of modification we can do to make the 2 strings anagram of each other. for eg a = “abb” b = “bcb” so the ans in this case is 1 i.e replace a with c or c with a. In case when both strings were of unequal size return -1.
 */

/*
 * Minimum Number of Manipulations required to make two Strings Anagram Without Deletion of Character
Given two strings s1 and s2, we need to find the minimum number of manipulations required to make two strings anagram without deleting any character.
Note:- The anagram strings have same set of characters, sequence of characters can be different.
 */
public class AnagramDifference {

	public static void main(String[] args) {
		String s1 = "ddcf";
        String s2 = "cedk";
        System.out.println(countManipulations(s1, s2));

	}

	private static int countManipulations(String s1, String s2) {
		int count = 0;
		 
        // store the count of character
        int char_count[] = new int[26];
 
        // iterate though the first String and update count
        for (int i = 0; i < s1.length(); i++) {
        		int x_ = char_count[s1.charAt(i) - 'a'];
        		//increment the count of each character in char_count array
            int x = char_count[s1.charAt(i) - 'a']++; 
            
            //System.out.println("Char = "+s1.charAt(i)+", x : "+x+", x_ : "+x_+", char-a : "+(s1.charAt(i) - 'a'));
        }
 
        // iterate through the second string update char_count. if character is not found in char_count
        // then increase count
        for (int i = 0; i < s2.length(); i++) {
            if (char_count[s2.charAt(i) - 'a']-- <= 0)
                count++;
        }
        return count;
	}

}
