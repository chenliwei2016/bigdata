package win.chenliwei.simplespringmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	private String datePattern;

	public StringToDateConverter(String datePattern) {
		this.datePattern = datePattern;
		System.out.println("Instantiating .... convert with pattern: " + datePattern);
	}

	@Override
	public Date convert(String arg0) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
			dateFormat.setLenient(false);
			return dateFormat.parse(arg0);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Ivalid date format, please use this pattern:" + this.datePattern);
		}
	}
	
	
}
