import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public class UtilProp {
	static Properties prop = new Properties();
	
	public static void loadProperty(ServletContext servletContext) {
		String filePath = "WEB-INF/config.properties";
		InputStream is = servletContext.getResourceAsStream(filePath);
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProp(String key) {
		return prop.getProperty(key).trim();
	}
}