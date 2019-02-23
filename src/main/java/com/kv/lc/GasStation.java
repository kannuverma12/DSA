package com.kv.lc;

/**
 * 
 *  @author karanverma
 *
 *  There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *  You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to 
 *  its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *  Return the starting gas station's index if you can travel around the circuit once in the clockwise 
 *  direction, otherwise return -1.
 *  
 *  Note:
 *  If there exists a solution, it is guaranteed to be unique.
 *  Both input arrays are non-empty and have the same length.
 *  Each element in the input arrays is a non-negative integer.
 *  
 *  Input: 
 *  gas  = [1,2,3,4,5]
 *  cost = [3,4,5,1,2]
 *  Output: 3
 *  
 *  Explanation:
 *  Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 *  Travel to station 4. Your tank = 4 - 1 + 5 = 8
 *  Travel to station 0. Your tank = 8 - 2 + 1 = 7
 *  Travel to station 1. Your tank = 7 - 3 + 2 = 6
 *  Travel to station 2. Your tank = 6 - 4 + 3 = 5
 *  Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 *  Therefore, return 3 as the starting index.
 */
public class GasStation {
    
    /*
     * To solve this problem, we need to understand and use the following 2 facts:
     * 1) if the sum of gas >= the sum of cost, then the circle can be completed.
     * 2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.
     * Proof of fact 2:
     * If gas[A] < cost[A], then A can not even reach B. 
     * So to reach C from A, gas[A] must >= cost[A]. 
     * Given that A can not reach C, we have gas[A] + gas[B] < cost[A] + cost[B], 
     * and gas[A] >= cost[A],
     * Therefore, gas[B] < cost[B], i.e., B can not reach C. 
     * 
     * In the following solution, sumRemaining tracks the sum of remaining to the current index. 
     * If sumRemaining < 0, then every index between old start and current index is bad, and we 
     * need to update start to be the current index. You can use the following example to visualize the solution.
     */

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {1,3,2,4,5};
        
        System.out.println("canc complete : "+canCompleteCircuit(gas, cost));

    }
    
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sumRemaining = 0;   // track current remaining
        int total = 0;          // track total remaining
        int start = 0; 
     
        for (int i = 0; i < gas.length; i++) {
            int remaining = gas[i] - cost[i];
     
            //if sum remaining of (i-1) >= 0, continue 
            if (sumRemaining >= 0) { 
                sumRemaining += remaining;
            //otherwise, reset start index to be current
            } else {
                sumRemaining = remaining;
                start = i;
            }
            total += remaining;
        }
     
        if (total >= 0){
            return start;
        }else{
            return -1;
        }
    }

}
