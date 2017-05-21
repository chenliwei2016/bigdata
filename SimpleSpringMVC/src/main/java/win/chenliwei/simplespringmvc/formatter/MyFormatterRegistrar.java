package win.chenliwei.simplespringmvc.formatter;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class MyFormatterRegistrar implements FormatterRegistrar {
	private String datePattern;

	public MyFormatterRegistrar(String datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public void registerFormatters(FormatterRegistry arg0) {
		arg0.addFormatter(new DateFormatter(this.datePattern));
		
	}
	

}
