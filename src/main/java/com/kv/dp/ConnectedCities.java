package com.kv.dp;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author karanverma
 *
 *  Solution to problem : https://www.hackerrank.com/contests/hack-it-to-win-it-paypal/challenges/q4-traveling-is-fun
 */
public class ConnectedCities {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public static List<Integer> connectedCities(int n, int g, List<Integer> originCities,
            List<Integer> destinationCities){
        List<Integer> ret = new ArrayList<>();
        
        for(int i=0; i<originCities.size();i++) {
            ret.add(i, 0);
            int o = originCities.get(i);
            List<Integer> odivi = findAllDivisors(o);
            
            int d = destinationCities.get(i);
            List<Integer> ddivi = findAllDivisors(d);
            
            for(int od : odivi) {
                if(od > g) {
                    if(ddivi.contains(od)) {
                        ret.add(i, 1);
                    }
                }
            }
        }
        
        return ret;
    }
    
    static int gcd(int a, int b) 
    { 
        if (a == 0)  
            return b; 
          
        return gcd(b%a,a); 
    } 
    // method to calculate all common divisors 
    // of two given numbers 
    // a, b --> input integer numbers 
    static int commDiv(int a,int b) 
    { 
        // find gcd of a,b 
        int n = gcd(a, b); 
       
        // Count divisors of n. 
        int result = 0; 
        for (int i=1; i<=Math.sqrt(n); i++) 
        { 
            // if 'i' is factor of n 
            if (n%i==0) 
            { 
                // check if divisors are equal 
                if (n/i == i) 
                    result += 1; 
                else
                    result += 2; 
            } 
        } 
        return result; 
    } 
    
    public static List<Integer> findAllDivisors(int x){
        List<Integer> l = new ArrayList<>();
        for(int i =1; i<=x ;i++){
            if(x%i == 0)
                l.add(i);

        }
        return l;
    }

}
