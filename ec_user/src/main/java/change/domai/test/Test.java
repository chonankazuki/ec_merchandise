package change.domai.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import change.domai.common.Property;
import change.domai.common.converter.TimeConverter;
import change.domai.model.Test_moel;

public class Test {

	@Autowired
	TimeConverter tm;
	
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		//String nowdate = tm.CurrentDate_jtos();
		//System.out.println(nowdate);
		//System.out.println(tm.addDate_jtos(tm.str_to_date(nowdate),30));
		
		String env = System.getenv("test_propfile");
        System.out.println(env);
        
        Map env2 = System.getenv();
        System.out.println(env2);
        
        //Property prop = new Property();
        //System.out.println(Property.getProperty("param", "maxdate"));

	}
}
