package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {
	
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ssz";

	public DateTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Date date = new Date();
		long l = 1520964000000l;
		date.setTime(l);
		System.out.println(date);
		
		/*Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		System.out.println(c.getTime());
*/		
		
		
		//System.out.println(date);
		
	/*	SimpleDateFormat format = new SimpleDateFormat(DATE_FULL_STR);
		System.setProperty("user.timezone","Europe/London"); 
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
		System.out.println(format.format(c.getTime()));
		*/

	}
	
	

}
