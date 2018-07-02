package com.kv.algos;

import java.text.SimpleDateFormat;
import java.util.*;

public class Convert12Hrto24Hr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		// System.out.println(s);
		String[] arr = s.split(":");
		String ret = "";
		String pmoram = "";
		if (arr[2].length() > 2)
			pmoram = arr[2].substring(2);
		if (pmoram.equalsIgnoreCase("pm")) {
			Integer n = 0;
			if (!arr[0].equals("12"))
				n = Integer.parseInt(arr[0]) + 12;
			else
				n = Integer.parseInt(arr[0]);
			ret = String.valueOf(n) + ":" + arr[1] + ":" + arr[2].substring(0, 2);
		} else {
			if (arr[0].equals("12"))
				arr[0] = "00";
			ret = arr[0] + ":" + arr[1] + ":" + arr[2].substring(0, 2);
		}

		System.out.println(ret);

		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ssa");
		try {
			Date date = parseFormat.parse(s);
			System.out.println(displayFormat.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
