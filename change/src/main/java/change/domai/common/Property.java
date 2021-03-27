package change.domai.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
	public String getProperty(String key) throws IOException {
		Properties properties = new Properties();
		String file = "/Users/chounankazuki/git/repository/change/src/setting.properties";
		
		try {
			FileInputStream fis = new FileInputStream(file);
			try {
				properties.load(fis);
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
