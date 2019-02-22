package com.kv.fb;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  I/P [8, 3, 2, [5, 6, [9]], 6]
 *  O/P 8+3+2+2*(5+6+3*(9))+6 => 95
 */
public class SumNestedList {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();

        list.add(8);
        list.add(3);
        list.add(2);

        List<Object> inner1 = new ArrayList<>();
        inner1.add(5);
        inner1.add(6);

        List<Object> inner2 = new ArrayList<>();
        inner2.add(9);
        inner1.add(inner2);

        list.add(inner1);

        list.add(6);

        System.out.println(calculate(list, 1));
    }

    public static int calculate(List<Object> a, int multiplier) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) instanceof ArrayList) {
                List l = (ArrayList) a.get(i);
                if (l.size() >= 1) {
                    multiplier++;
                    sum += multiplier * ((int) calculate(l, multiplier));
                }
            } else {
                sum += ((int) a.get(i));
            }
        }
        return sum;
    }

}
