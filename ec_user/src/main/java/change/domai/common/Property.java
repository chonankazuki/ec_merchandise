package change.domai.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.stereotype.Component;

import change.domai.model.Test_moel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


@Component
public class Property {
	@Autowired
    Environment eivironment;
	@Autowired
	Test_moel test;
	static String property_path_name = "sever.property.filepath";
	static String setting = "setting.properties";
	static String url_api = "url_list.properties";
	
	public  String getProperty(String type,String key) throws IOException {
		Properties properties = new Properties();
		//GetEnvironment getenv = new GetEnvironment();
		//Environment env = getenv.getEnvironment();
		String file = "";
		
		//String param_file = "/Users/chounankazuki/git/repository/change/src/setting.properties";
		//String api_file = "/Users/chounankazuki/git/repository/change/src/url_list.properties";
		System.out.println("port:"+test.getTest1());
		System.out.println("envは");
		System.out.println(eivironment);
		System.out.println(eivironment.getProperty(property_path_name));
		
		String prop_file_path = eivironment.getProperty(property_path_name);
		
	    String param_file = prop_file_path + "/" + setting;
	    String api_file = prop_file_path + "/" + url_api;
		if(type == "param") {
			file = param_file;
		}else if(type == "api") {
			file = api_file;
		}
	    /*
		try {
			//FileInputStream fis = new FileInputStream(file);
			System.out.println(file);

			//System.out.println(ClassLoader.getSystemResourceAsStream(file));
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
	*/
		FileInputStream in = null;
	    try {
	      in = new FileInputStream(file);
	      properties.load(in);
	    } finally {
	      if (in != null) {
	        in.close();
	      }
	    }
	    String val = properties.getProperty(key);
	    System.out.println(val);
		return val;

	}

		
}
