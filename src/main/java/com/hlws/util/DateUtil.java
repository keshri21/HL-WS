package com.hlws.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat DD_MM_YYYY_FORMATTER = new SimpleDateFormat("dd/mm/yyyy");
	
	public static synchronized String format(Date date) {
		return null == date ? null : DD_MM_YYYY_FORMATTER.format(date);  
	}
	
	public static Date getDate(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	public static synchronized String addAndFormat(int days) {
		Date date = new Date();
		date.setDate(date.getDate()+days);
		return DD_MM_YYYY_FORMATTER.format(date);
	}
	
	public static int currYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
}
