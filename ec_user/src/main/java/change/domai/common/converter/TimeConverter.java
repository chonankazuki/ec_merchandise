package change.domai.common.converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import change.domai.common.Property;
import change.domai.model.Test_moel;

@Component
public class TimeConverter {
	@Autowired
    Environment eivironment;
	
	@Autowired
	Test_moel test;
	@Autowired
	Property prop;
	
	
	public String CurrentDate_jtos() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowdate = new Date();
		System.out.println(nowdate);
		return df.format(nowdate);
	}
	public String addDate_jtos(Date date,int add_val) {
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, add_val);
        date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public String MaxDate_jtos() throws IOException {

		
		return prop.getProperty("param","maxdate");
	}
	
	public Date str_to_date(String strDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(strDate);
		return date;
	}
}
