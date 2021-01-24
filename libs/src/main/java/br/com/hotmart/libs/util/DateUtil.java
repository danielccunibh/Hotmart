package br.com.hotmart.libs.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	public static final String DDMMYYY = "dd/MM/yyyy";

	public static final String HHMMSS = "HH:mm:ss";

	public static long getDaysBetweenDates(final Date d1, final Date d2) {
		final long diffTimeMilis = d2.getTime() - d1.getTime();

		return TimeUnit.SECONDS.convert(diffTimeMilis, TimeUnit.MILLISECONDS);
	}

	public static Date getDate000000(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date getDate235959(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	public static String getDateFormated(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);

	}

	public static Date addDate(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);

		return cal.getTime();
	}

}
