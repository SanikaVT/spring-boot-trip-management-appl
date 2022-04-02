package com.tripmanagement.asdc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utility {
    

    public static String convertDate(String datetime) throws ParseException
	{
		datetime = datetime.replace("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM HH:mm");
        Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(datetime);
		return  sdf.format(date);
	}
    
    public static String getCurrentTime() {
		long millis = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date resultdate = new Date(millis);
		sdf.setTimeZone(TimeZone.getTimeZone("America/Halifax"));
		return sdf.format(resultdate);
	}
}
