package com.kv.hr;

/**
 * 
 * @author karanverma
 *
 */
public class CountingValleys {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    static int countingValleys(int n, String s) {
        int count = 0;
        int valley = 0;
        boolean bellowSea = false;
        char[] arr = s.toCharArray();
        for( int i =0;i< arr.length-1;i++){
            char c = arr[i];
            if(c == 'U' )
                count++;
            
            if(c == 'D')
                count--;
            
            if(!bellowSea && count<0){
                valley++;
                bellowSea = true;
            }
            if(count>=0)
                bellowSea = false;

        }
        return valley;

    }

}
