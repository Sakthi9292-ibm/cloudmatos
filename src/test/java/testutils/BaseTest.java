package testutils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static TestProperties pro;
	public BaseTest() {
		pro = new TestProperties();
		//pro.ReadLogFile();
	}
	
	public static void init() {
		String browsername = pro.readConfig("browser");
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromiumdriver().setup();
			driver = new ChromeDriver();
			 
		} else if(browsername.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browsername.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} 
		else if(browsername.equalsIgnoreCase("edge")) {
			//options.addArguments("-private");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		else {
			System.out.println("Please pass the browser in testing.properties file");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(pro.readConfig("implicitWait")), TimeUnit.SECONDS);
		
		//driver.get(pro.readConfig("url"));
	}
	
	public static String getScreenshot(String screenshotName) {
		String datename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts =  (TakesScreenshot) driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination =  System.getProperty("user.dir")+"/Screenshots/"+screenshotName+"_"+datename+".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
	
	

}
