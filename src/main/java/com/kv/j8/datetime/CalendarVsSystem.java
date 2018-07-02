package com.kv.j8.datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CalendarVsSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time = System.currentTimeMillis();
		String dateFormat = "dd-mm-yyyy hh:mm:ss";
		String s = getExpiryDateCalendar(5,dateFormat);
		System.out.println("Time in Calendar : "+(System.currentTimeMillis() - time) + ".s:"+s);
		
		time = System.currentTimeMillis();
		String s2 = getExpiryDateSystem(5,dateFormat);
		System.out.println("Time in System : "+(System.currentTimeMillis() - time)+".s2:"+s2);

	}
	
	public static String getExpiryDateCalendar(int timeInterval, String dateFormat)
	{	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, timeInterval);
		Date date = cal.getTime();             
		SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
		String date1 = format1.format(date);
		format1 = null;
	    return date1;
	}
	
	public static String getExpiryDateSystem(int timeInterval, String dateFormat)
	{	
		
		LocalDate thirdFridayOfMonth = LocalDate
				.now().plusDays(timeInterval);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy['T'HH:mm:ss[Z]]");
		String date1 = thirdFridayOfMonth.format(df);
	    return date1;
	}

}
