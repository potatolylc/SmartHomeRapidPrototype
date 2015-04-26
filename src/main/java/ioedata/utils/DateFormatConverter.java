package ioedata.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatConverter {
	private static DateFormatConverter convert = null;
	private DateFormatConverter() {}
	public static DateFormatConverter getConvert() {
		if(convert == null) {
			convert = new DateFormatConverter();
		}
		return convert;
	}
	
	public static String englishLocaleToStandard(String oldDate) {
		String newDate = null;
		DateFormat englishLocaleFormat = new SimpleDateFormat("EEE MMM DD HH:mm:ss zzz yyyy");
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
	
	public static String englishLocaleToStandard(Date oldDate) {
		String newDate = null;
		DateFormat standardFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
		newDate = standardFormat.format(oldDate);
		return newDate;
	}
}
