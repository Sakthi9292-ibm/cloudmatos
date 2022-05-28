package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class LoginPage{
	
	WebDriver driver;

	WebDriverWait wait;
	BasePage basepage;

	public LoginPage(WebDriver driv) {
		PageFactory.initElements(driv, this);
		driver = driv;
		wait = new WebDriverWait(driver, 120);
		basepage = new BasePage(driver);
	}
	

	private By LoginPage_login = By.xpath("//p[contains(text(),'Login')]");
	private By LoginPage_emailtext = By.xpath("//label[contains(text(),'Your Email')]");
	private By LoginPage_emailtextbox = By.xpath("(//input[@type='text'])[1]");
	private By LoginPage_organisation = By.xpath("(//input[@type='text'])[2]");
	private By LoginPage_password = By.xpath("//input[@type='password']");
	private By LoginPage_rememberme_checkbox = By.xpath("//input[@type='checkbox']");
	private By LoginPage_rememberme_text=By.xpath("//p[contains(text(),'Remember me')]");
	private By LoginPage_Forgot_password = By.xpath("//p[contains(text(),'Forgot password?')]");
	private By LoginPage_Signin = By.xpath("//button[@type='submit']");
	private By LoginPage_donthaveaccounttext=By.xpath("//p[contains(text(),'t have an account')]");
	private By LoginPage_accountclickhere=By.xpath("//p[contains(text(),'Click here')]");
	private By LoginPage_withgoogle=By.xpath("//p[contains(text(),'Login with Google')]");
	private By LoginPage_alert_title=By.xpath("//h4");
	private By loginPgae_alert_text=By.xpath("//div[@class='message']");
	
	public void loginPage_enterEmail(String email) {
		driver.findElement(LoginPage_emailtextbox).sendKeys(email);
		
	}
	
	public String loginPage_getEmailfromtextbox() {
		
		String email = driver.findElement(LoginPage_emailtextbox).getText();
		return email;
	}

	public void loginPage_enterPassword(String pass) {
		driver.findElement(LoginPage_password).sendKeys(pass);
	}
	
	public String loginPage_getPassword() {
		String pass = driver.findElement(LoginPage_password).getText();
		return pass;
	}
	
	public void loginPage_enterOrganisation(String org) {
		driver.findElement(LoginPage_organisation).sendKeys(org);
	}

	public String loginPage_getOrganisation() {
		String org = driver.findElement(LoginPage_organisation).getText();
		return org;
	}
	
	public void loginPage_checkRememberMe() {
		driver.findElement(LoginPage_rememberme_checkbox).clear();
		driver.findElement(LoginPage_rememberme_checkbox).click();
	}
	
	public void loginPage_uncheckRememberMe() {
		driver.findElement(LoginPage_rememberme_checkbox).clear();
	}

	public void loginPage_clickForgorPassword() {
		driver.findElement(LoginPage_Forgot_password).click();
	}

	public void loginPage_clicksignin() {
		driver.findElement(LoginPage_Signin).click();
	}

	public void loginPage_newaccount_clickhere() {
		driver.findElement(LoginPage_accountclickhere);
	}
	
	public void loginPage_googlesignin() {
		driver.findElement(LoginPage_withgoogle);
	}

	public void loginPage_clearemailtext() {
		driver.findElement(LoginPage_emailtextbox).clear();
	}
	
	public void loginPage_clearpassword() {
		driver.findElement(LoginPage_password).clear();
	}
	
	public void LoginPage_login_url(String url) {
		driver.get(url);
	}
	
	public String loginPage_get_alert_text() throws InterruptedException {
		
		Thread.sleep(1500);
		String text=driver.findElement(loginPgae_alert_text).getText();
		System.out.println(driver.findElement(loginPgae_alert_text).getText());
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPage_alert_title));
		System.out.println("done");
		System.out.println("=--"+text);
		return text;
		
	}
}


