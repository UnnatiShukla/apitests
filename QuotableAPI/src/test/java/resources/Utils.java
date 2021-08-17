package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpecification()  throws IOException
	{
		if(reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("APITestLogs.txt"));
			reqSpec = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return reqSpec;
			
		}
		return reqSpec;
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		
		return properties.getProperty(key);
	}

}
