package com.kv.strings;

public class StringPermutation {

	private static void permutation(String perm, String word) {
	       if (word.isEmpty()) {
	           System.err.println(perm + word);

	       } else {
	           for (int i = 0; i < word.length(); i++) {
	               //System.out.println("first prams:"+perm + word.charAt(i) +"second params::"+);
	               permutation(perm + word.charAt(i),
	                               word.substring(0, i) + word.substring(i + 1, word.length())
	                               );
	           }
	       }

	   }
	
    public static void main(String[] args)
    {
//        String str = "aban";
//        int n = str.length();
//        StringPermutation permutation = new StringPermutation();
//        permutation.permute(str, 0, n-1);
        
        boolean b = isAnagram("abb","ba");
        System.out.println("b  = "+b);
        
        permute("abc",0,2);
        
    }
 
    public static boolean isAnagram(String word, String anagram){ 
    	if(word.length() != anagram.length()){ return false; } 
    	char[] chars = word.toCharArray(); 
    	for(char c : chars){ 
    		int index = anagram.indexOf(c); 
    		if(index != -1){ 
    			anagram = anagram.substring(0,index) + anagram.substring(index +1, anagram.length()); 
    		}else{ 
    			return false; 
    		} 
    	} 
    	return anagram.isEmpty(); 
    }

    /**
     * permutation function
     * @param str string to calculate permutation for
     * @param l starting index
     * @param r end index
     */
    private static void permute(String str, int l, int r)
    {
    	str = str.toLowerCase();
        if (l == r){
        	
        	if((str.startsWith("a") || str.startsWith("e") || str.startsWith("i") || str.startsWith("o") ||str.startsWith("u"))
        			&& !(str.endsWith("a") || str.endsWith("e") || str.endsWith("i") || str.endsWith("o") || str.endsWith("u")))
        		System.out.println(str);
        }
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }
 
    /**
     * Swap Characters at position
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
 
}
