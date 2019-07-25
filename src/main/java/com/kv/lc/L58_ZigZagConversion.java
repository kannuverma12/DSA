package com.kv.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 *      P   A   H   N
 *      A P L S I I G
 *      Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows
 */
public class L58_ZigZagConversion {
    public static void main(String[] args) {
        String s = convert("paypalishiring", 3);
        System.out.println("s = "+s);
    }

    /*
    Method 1
    Intuition

    Visit the characters in the same order as reading the Zig-Zag pattern line by line.

    Algorithm

    Visit all characters in row 0 first, then row 1, then row 2, and so on...

    For all whole numbers kk,

    Characters in row 00 are located at indexes k \; (2 \cdot \text{numRows} - 2)k(2⋅numRows−2)
    Characters in row \text{numRows}-1numRows−1 are located at indexes k \; (2 \cdot \text{numRows} - 2) + \text{numRows} - 1k(2⋅numRows−2)+numRows−1
    Characters in inner row ii are located at indexes k \; (2 \cdot \text{numRows}-2)+ik(2⋅numRows−2)+i and (k+1)(2 \cdot \text{numRows}-2)- i(k+1)(2⋅numRows−2)−i.

     */

    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder sb = new StringBuilder();
        // step
        int step = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            //first & last rows
            if (i == 0 || i == numRows - 1) {
                for (int j = i; j < s.length(); j = j + step) {
                    sb.append(s.charAt(j));
                }
                //middle rows
            } else {
                int j = i;
                boolean flag = true;
                int step1 = 2 * (numRows - 1 - i);
                int step2 = step - step1;

                while (j < s.length()) {
                    sb.append(s.charAt(j));
                    if (flag)
                        j = j + step1;
                    else
                        j = j + step2;
                    flag = !flag;
                }
            }
        }

        return sb.toString();
    }

    /*
    Method 2
     */
    public String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

}
