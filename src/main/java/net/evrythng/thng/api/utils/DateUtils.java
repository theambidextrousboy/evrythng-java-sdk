package net.evrythng.thng.api.utils;

import java.util.Date;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateUtils {

	public static DateTimeFormatter ISO8601_FORMATTER = ISODateTimeFormat.dateTimeNoMillis();
	
	public static Date fromISO8601(String date) {
		return ISO8601_FORMATTER.parseDateTime(date).toDate();
	}
	
	public static String toISO8601(Date date) {
		return ISO8601_FORMATTER.print(date.getTime());
	}
}
