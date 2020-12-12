package change.domai.common.converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import change.domai.common.Property;
@Component
public class TimeConverter {

	public String CurrentDate_jtos() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowdate = new Date();
		System.out.println(nowdate);
		return df.format(nowdate);
	}
	
	public String MaxDate_jtos() throws IOException {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date nowdate = new Date();
		//return df.format(nowdate);
		Property prop = new Property();
		
		return prop.getProperty("maxdate");
	}
}
