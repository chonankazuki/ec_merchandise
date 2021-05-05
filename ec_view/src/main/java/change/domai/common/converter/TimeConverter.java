package change.domai.common.converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import change.domai.common.Property;
@Component
public class TimeConverter {

	public static String CurrentDate_jtos() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowdate = new Date();
		System.out.println(nowdate);
		return df.format(nowdate);
	}
	public static String addDate_jtos(Date date,int add_val) {
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, add_val);
        date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String MaxDate_jtos() throws IOException {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date nowdate = new Date();
		//return df.format(nowdate);
		//Property prop = new Property();
		
		return Property.getProperty("param","maxdate");
	}
	
	public static Date str_to_date(String strDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(strDate);
		return date;
	}
}
