package testutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	
		FileInputStream fis;
		Properties pro;
		String filepath = "src/test/resources/testing.properties";
		
		public TestProperties() {
			try {
				fis = new FileInputStream(filepath);
				pro=new Properties();
				pro.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public String readConfig(String key) {
			return pro.getProperty(key);
		}
}
		
	

