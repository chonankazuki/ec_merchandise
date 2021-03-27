package change.domai.test;

import java.text.ParseException;

import change.domai.common.converter.TimeConverter;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String nowdate = TimeConverter.CurrentDate_jtos();
		System.out.println(nowdate);
		System.out.println(TimeConverter.addDate_jtos(TimeConverter.str_to_date(nowdate),30));

	}
}
