package com.kv.j8.datetime;

import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previous;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.util.Locale;

public class DataTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate firstSundayOfNextMonth = LocalDate
				  .now()
				  .with(firstDayOfNextMonth())
				  .with(nextOrSame(DayOfWeek.MONDAY));
		System.out.println("firstSundayOfNextMonth = "+firstSundayOfNextMonth);
		
		LocalDate thirdSundayOfNextMonth = LocalDate
											.now()
											.with(firstDayOfNextMonth())
											.with(nextOrSame(DayOfWeek.MONDAY)).plusDays(20);
		System.out.println("thirdSundayOfNextMonth = "+thirdSundayOfNextMonth);			
		
		
		LocalDate thirdFridayOfMonth = LocalDate
										.now()
										.with(lastDayOfMonth())
										.with(previous(DayOfWeek.FRIDAY)).minusDays(14);
		System.out.println("thirdFridayOfMonth = "+thirdFridayOfMonth);
		
		LocalDate thirdFridayOfMonthNew = LocalDate
										.now()
										.with(dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
		System.out.println("thirdFridayOfMonthNew = "+thirdFridayOfMonthNew);		
		
		
		LocalDate lastFridayOfMonth = LocalDate
										.now()
										.with(lastDayOfMonth())
										.with(previous(DayOfWeek.FRIDAY));
		System.out.println("lastFridayOfMonth = "+lastFridayOfMonth);
		
		LocalDate dayAfter3MonthsAnd2Days = LocalDate
												.now()
												.with(firstDayOfMonth()).plusMonths(3).plusDays(2);
		System.out.println("dayAfter3MonthsAnd2Days = "+dayAfter3MonthsAnd2Days);
		
		LocalDate dayAfter3MonthsAnd2DaysNew = LocalDate
												.now()
												.plusMonths(3)
												.plusDays(2);
		System.out.println("dayAfter3MonthsAnd2DaysNew = "+dayAfter3MonthsAnd2DaysNew);
		
		TemporalAdjuster ta = TemporalAdjusters.ofDateAdjuster(
													(LocalDate d) -> d.plusMonths(3).plusDays(2));
		LocalDate dayAfter3MonthsAnd2DaysCustom = LocalDate
													.now()
													.with(ta);
		System.out.println("dayAfter3MonthsAnd2DaysCustom = "+dayAfter3MonthsAnd2DaysCustom);
		
		System.out.println("");
		System.out.println("************** Temporal Query *************");
		System.out.println("");
		
		TemporalQuery<TemporalUnit> tu = TemporalQueries.precision();
		TemporalQuery<LocalDate> ld = TemporalQueries.localDate();
		
		
		// Query LocalDate
		TemporalUnit precision = LocalDate
									.now()
									.query(tu);
		
		LocalDate localDate = LocalDate
									.now()
									.query(ld);
		System.out.println("  Query LocalDate ");
		System.out.println("Precision = "+precision);
		System.out.println("LocalDate = "+localDate);
		
		//Query LocalTime
		precision = LocalTime
							.now()
							.query(tu);
		localDate = LocalTime
						.now()
						.query(ld);
		
		System.out.println("  Query LocalDate ");
		System.out.println("Precision = "+precision);
		System.out.println("LocalDate = "+localDate);
		
		
		//Query ZonedDateTime
		precision = ZonedDateTime
						.now()
						.query(tu);
		localDate = ZonedDateTime
						.now()
						.query(ld);
		
		System.out.println("  Query ZonedDateTime ");
		System.out.println("Precision = "+precision);
		System.out.println("LocalDate = "+localDate);
		
		//ChronoUnit
		System.out.println();
		System.out.println(" ********** ChronoUnit *********");
		System.out.println();
		
		LocalDateTime timeNow = LocalDateTime.now();
		LocalDateTime timeAfterSometime = timeNow.plusHours(4).plusMinutes(11);
		 
		System.out.println("timeNow = "+timeNow);
		System.out.println("timeAfterSometime = "+timeAfterSometime);

		long minutesDiff = ChronoUnit.MINUTES.between(timeNow, timeAfterSometime); // 251
		long hoursDiff = ChronoUnit.HOURS.between(timeNow, timeAfterSometime); // 4
		
		System.out.println("minutesDiff = "+minutesDiff);
		System.out.println("hoursDiff = "+hoursDiff);
		
		//Datetimeformat
		System.out.println();
		System.out.println(" ********** Date Time Format *********");
		System.out.println();
		LocalDate ldd = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
	    System.out.println("DateTimeFormatter = " + fmt.format(ldd));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY", Locale.ENGLISH);
		System.out.println("SimpleDateFormat = "+sdf.format(System.currentTimeMillis()));
	    
		
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss[Z]]");
	    parseMyDate(parser, "2016-12-07");
	    parseMyDate(parser, "2016-12-07T15:31:12");
	    parseMyDate(parser, "2016-12-07T15:31:12-0500");
	    parseMyDate(parser, "2016-12-07Hello");
	    
	    
	    DateTimeFormatter customFormatter = new DateTimeFormatterBuilder()
    	    .appendLiteral("New Year in ")
    	    .appendValue(ChronoField.YEAR)
    	    .appendLiteral(" was on  ")
    	    .appendText(ChronoField.DAY_OF_WEEK,TextStyle.FULL_STANDALONE)
    	    .toFormatter(); 
	    
	    LocalDate ld1  = LocalDate.of(2016, Month.JANUARY, 1); 
	    String formatted = ld1.format(customFormatter); 
	    System.out.println(formatted);

	}
	
	public static void parseMyDate(DateTimeFormatter formatter, String parseText) {
	    try {
	      TemporalAccessor ta = formatter.parseBest(parseText, 
	    		  									OffsetDateTime::from,
	    		  									LocalDateTime::from, 
	    		  									LocalDate::from);
	      if (ta instanceof OffsetDateTime) {
	        OffsetDateTime odt = OffsetDateTime.from(ta);
	        System.out.println("OffsetDateTime: " + odt);
	      } else if (ta instanceof LocalDateTime) {
	        LocalDateTime ldt = LocalDateTime.from(ta);
	        System.out.println("LocalDateTime: " + ldt);
	      } else if (ta instanceof LocalDate) {
	        LocalDate ld = LocalDate.from(ta);
	        System.out.println("LocalDate: " + ld);
	      } else {
	        System.out.println("Parsing returned: " + ta);
	      }
	    } catch (DateTimeParseException e) {
	      System.out.println("Here in Exception : "+e.getMessage());
	      //e.printStackTrace();
	    }
	  }

}
