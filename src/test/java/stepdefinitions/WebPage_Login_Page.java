package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import testutils.BaseTest;

public class WebPage_Login_Page extends BaseTest {
	LoginPage loginpage;

	@Before
	public void setUp() {
		if(driver!=null) {
		init();
		loginpage = new LoginPage(driver);
		}
	}

	@Given("user is in the webpage {string}")
	public void user_is_in_the_webpage(String string) {
		loginpage.LoginPage_login_url(string);
	}

	@When("user enters the Email {string} in Email field")
	public void user_enters_the_email_in_email_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_clearemailtext();
		loginpage.loginPage_enterEmail(string);
	}

	@When("user enters the {string} in the password field")
	public void user_enters_the_in_the_password_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_clearpassword();
		loginpage.loginPage_enterPassword(string);
	}

	@When("user clicks signin button")
	public void user_clicks_signin_button() {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_clicksignin();
	}

	@Then("user should not get the {string}")
	public void user_should_not_get_the(String string) throws InterruptedException {

		assertNotEquals(string, loginpage.loginPage_get_alert_text());

	}

	@Then("user should get the {string}")
	public void user_should_get_the(String string) throws InterruptedException {
		assertEquals(string, loginpage.loginPage_get_alert_text());
	}

	@When("user enters the {string} in Email field")
	public void user_enters_the_in_email_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_enterEmail(string);
	}

	@When("user clears text in the password field")
	public void user_clears_text_in_the_password_field() {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_clearpassword();
	}

	@When("user enters the {string} in password field")
	public void user_enters_the_in_password_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_clearpassword();
		loginpage.loginPage_enterPassword(string);
	}

	@When("user clears text in the username field")
	public void user_clears_text_in_the_username_field() {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_clearemailtext();
	}

	@When("user clicks the remember me check box")
	public void user_clicks_the_remember_me_check_box() {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_checkRememberMe();
	}

	@Then("user should be able to click it")
	public void user_should_be_able_to_click_it() {
		// Write code here that turns the phrase above into concrete actions
		loginpage.loginPage_checkRememberMe();
	}
	
	@After
	public static void tearDown() {
	//	LogUtils.endTestCase(BaseTest.class.getName());
		if(driver!=null) {
			driver.quit();
		}
	}
}
