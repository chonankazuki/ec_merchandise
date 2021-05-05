package change.domai.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Component
public class Property {
	public static String getProperty(String type,String key) throws IOException {
		Properties properties = new Properties();
		String file = "";
		//String param_file = "/Users/chounankazuki/git/repository/change/src/setting.properties";
		//String api_file = "/Users/chounankazuki/git/repository/change/src/url_list.properties";
	    String param_file = "static/setting.properties";
	    String api_file = "static/url_list.properties";
		if(type == "param") {
			file = param_file;
		}else if(type == "api") {
			file = api_file;
		}
	    
		try {
			//FileInputStream fis = new FileInputStream(file);
			System.out.println(file);
			System.out.println(ClassLoader.getSystemResourceAsStream(file));
			InputStreamReader stream = new InputStreamReader(ClassLoader.getSystemResourceAsStream(file),"UTF-8");
			BufferedReader buffer = new BufferedReader(stream);
			try {
				properties.load(buffer);
				String val = properties.getProperty(key);
				return val;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
