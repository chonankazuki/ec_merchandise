package change.domai.common;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class GetEnvironment {
	@Autowired
	Environment eivironment;
	
	public Environment getEnvironment() {
		System.out.println("test");
		return eivironment;
		
	}
	
}
