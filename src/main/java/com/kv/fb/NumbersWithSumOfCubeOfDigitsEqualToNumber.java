package com.kv.fb;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  find all numbers the sum of cube of each digits is the number itself.
 *  ex:153=1^3+5^3+3^3
 */
public class NumbersWithSumOfCubeOfDigitsEqualToNumber {
    public static void main(String[] args) {
//        System.out.print("Enter the limit : ");
//        int n = (new Scanner(System.in)).nextInt();
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            if (i == findSOC(i)) {
//                list.add(i);
//            }
//        }
//        System.out.println(list);
        
        FindNarcisisitcNumbers();
    }

    private static int findSOC(int i) {
        if (i == 0)
            return 0;
        return findSOC(i / 10) + cube(i % 10);
    }

    private static int cube(int i) {
        return i * i * i;
    }
    
    public static void FindNarcisisitcNumbers() {
        int n = 3;
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(0);
        int power = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = power; j < power * 10 ; j++) {
                int temp = j;
                int sum = 0;
                while (temp != 0) {
                    sum += Math.pow(temp % 10, i);
                    temp /= 10;
                }
                if (sum == j)
                    nums.add(j);
            }
            power *= 10;
        }
        for (int nu : nums)
            System.out.println(nu);
    }
}
