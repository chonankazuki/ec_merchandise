package change.domai.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import change.domai.common.converter.TimeConverter;


@Component
public class Test_moel {

	
	@Value("${server.port}")
	private String test1;
	
	public String getTest1() {
		return test1;
	}
}
