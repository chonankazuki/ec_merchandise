package change.domai.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ErrorMessage {
	public static String getErrorMsg(String key) throws IOException {
		Properties properties = new Properties();
		//String file = "/Users/chounankazuki/Documents/workspace-spring-tool-suite-4-4.6.1.RELEASE/ec_user/src/error_message.properties";
		String file = "static/error_message.properties";
		try {
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
