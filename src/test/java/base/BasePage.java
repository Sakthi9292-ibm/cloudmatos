package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("Failed while waiting for title to be loaded: "+title);
			e.printStackTrace();
		}
	}
	
	@Override
	public String getTitle() {
		return driver.getTitle();
	}
	
	@Override
	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}catch (Exception e) {
			System.out.println("Failed while waiting for the element: "+locator.toString());
			e.printStackTrace();
		}
	}

	public void waitForElementVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		}catch (Exception e) {
			System.out.println("Failed while waiting for the Visibility of element: "+locator.toString());
			e.printStackTrace();
		}
	}
	@Override
	public WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element=null;
		try {
			element=driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Error ocurred while locating element: "+element.toString());
			e.printStackTrace();
		}
		return element;
	}
	
	@Override
	public List<WebElement> getElements(By locator) {
		waitForElementPresent(locator);
		List<WebElement> element = null;
		try {
			element = driver.findElements(locator);
		} catch (Exception e) {
			System.out.println("Error ocurred while locating elements: "+element.toString());
			e.printStackTrace();
		}
		return element;
	}
	
	@Override
	public WebElement getPageHeader(By locator) {
		waitForElementPresent(locator);
		WebElement element = driver.findElement(locator);
		return element;
	}
	
	public void setValue(By element, String value) {
		getElement(element).clear();
		getElement(element).sendKeys(value);
	}
	
	public void doClick(By element) {
		getElement(element).click();
	}
	
	public void selectDropdownByValue(By element, String value) {
		Select dd = new Select(getElement(element));
		dd.selectByValue(value);
	}
	
	public void selectDropdownByText(By element, String text) {
		Select dd = new Select(getElement(element));
		dd.selectByVisibleText(text);
	}

	public String decrypt(String str){
	    str = str.replace("-", "");
	    String result = "";
	    for (int i = 0; i < str.length(); i+=3) {
	      String hex =  str.substring(i+1, i+3);
	      result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(str.charAt(i)))));
	    }
	    return result;
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
