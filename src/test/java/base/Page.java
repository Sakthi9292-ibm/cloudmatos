package base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	
	public static WebDriver driver;
	public WebDriverWait wait;
	
	public Page(WebDriver driver) {
		Page.driver = driver;
		this.wait = new WebDriverWait(driver, 60);
	}

	
	//Abstract Methods
	abstract public void waitForPageTitle(String title);
	abstract public String getTitle();
	abstract public void waitForElementPresent(By locator);
	abstract public WebElement getElement(By locator);
	abstract public WebElement getPageHeader(By locator);
	abstract public List<WebElement> getElements(By locator);
	
	
	//Create generic
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) 
	{
		try 
		{
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(Page.driver);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
