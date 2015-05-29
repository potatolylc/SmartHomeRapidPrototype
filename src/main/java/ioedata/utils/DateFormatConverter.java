package ioedata.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class helps to change the date format.
 * @author ajou
 *
 */
public class DateFormatConverter {
	private static DateFormatConverter convert = null;
	private DateFormatConverter() {}
	public static DateFormatConverter getConvert() {
		if(convert == null) {
			convert = new DateFormatConverter();
		}
		return convert;
	}
	
	/**
	 * Convert String date format from English locale to standard format.
	 * @param oldDate
	 * @return
	 */
	public static String englishLocaleToStandard(String oldDate) {
		String newDate = null;
		DateFormat englishLocaleFormat = new SimpleDateFormat("EEE MMM DD HH:mm:ss zzz yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = englishLocaleFormat.parse(oldDate);
			newDate = englishLocaleToStandard(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * Convert Date date format from English locale to standard format.
	 * @param oldDate
	 * @return
	 */
	public static String englishLocaleToStandard(Date oldDate) {
		String newDate = null;
		DateFormat standardFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss", Locale.KOREA);
		newDate = standardFormat.format(oldDate);
		return newDate;
	}
}
