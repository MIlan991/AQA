package test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import webpages.LogInPage;
import webpages.SignUpPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLogIn {
	
	private static ChromeDriver driver;
	private static LogInPage logInPage;
	private static SignUpPage signUpPage;
	
	String validEmail = "userqa91@gmail.com";
	String invalidEmail = "invalidEmail";
	String wrongEmail = "invalid2323@gmail.com";
	
	String validPassword = "qatester123";
	String invalidPassword = "asdfsfs";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/chile91/Desktop/webdriver/chromedriver");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		logInPage = new LogInPage(driver, wait);
		signUpPage = new SignUpPage(driver, wait);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		logInPage.openPage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc03LoginWithEmptyFields() throws InterruptedException {
		logInPage.emailInputString("");
		logInPage.passwordInputString("");
		logInPage.submitSigninBtn();
		
		String EmailErrorMessage = logInPage.emailErrorMessage();
		assertEquals("The email field is required.", EmailErrorMessage);
		
		String PasswordErrorMessage = logInPage.passwordErrorMessage();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc04LoginWithIncorrectEmailFormat() throws InterruptedException {
		logInPage.emailInputString(invalidEmail);
		logInPage.passwordInputString("");
		logInPage.submitSigninBtn();
		
		String EmailErrorMessage = logInPage.emailErrorMessage();
		assertEquals("The email field must be a valid email address.", EmailErrorMessage);
		
		String PasswordErrorMessage = logInPage.passwordErrorMessage();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc05LoginWithInvalidEmailFormat() throws InterruptedException {
		logInPage.emailInputString(wrongEmail);
		logInPage.passwordInputString("");
		logInPage.submitSigninBtn();
		
		String PasswordErrorMessage = logInPage.passwordErrorMessage();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc06LoginWithValidEmailFormat() throws InterruptedException {
		logInPage.emailInputString(validEmail);
		logInPage.passwordInputString("");
		logInPage.submitSigninBtn();
		
		String PasswordErrorMessage = logInPage.passwordErrorMessage();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc07LoginWithEmptyEmailFieldAndIncorrectPassword() throws InterruptedException {
		logInPage.emailInputString("");
		logInPage.passwordInputString(invalidPassword);
		logInPage.submitSigninBtn();
		
		String EmailErrorMessage = logInPage.emailErrorMessage();
		assertEquals("The email field is required.", EmailErrorMessage);
	}

	@Test
	public void tc08LoginWithEmptyEmailFieldAndValidPassword() throws InterruptedException {
		logInPage.emailInputString("");
		logInPage.passwordInputString(validPassword);
		logInPage.submitSigninBtn();
		
		String EmailErrorMessage = logInPage.emailErrorMessage();
		assertEquals("The email field is required.", EmailErrorMessage);
	}

	@Test
	public void tc09LoginWithIncorrectEmailFormatAndInvalidPassword() throws InterruptedException {
		logInPage.emailInputString(invalidEmail);
		logInPage.passwordInputString(invalidPassword);
		logInPage.submitSigninBtn();
		
		String EmailErrorMessage = logInPage.emailErrorMessage();
		assertEquals("The email field must be a valid email address.", EmailErrorMessage);
	}

	@Test
	public void tc10LoginWithInvalidEmailAddressAndInvalidPassword() throws InterruptedException {
		logInPage.emailInputString(wrongEmail);
		logInPage.passwordInputString(invalidPassword);
		logInPage.submitSigninBtn();
		
		String LoginErrorMessage = logInPage.loginErrorMessage();
		assertEquals("The email address or password you entered is invalid", LoginErrorMessage);
	}

	@Test
	public void tc11LoginWithValidEmailAddressAndInvalidPassword() throws InterruptedException {
		logInPage.emailInputString(validEmail);
		logInPage.passwordInputString(invalidPassword);
		logInPage.submitSigninBtn();
		
		String LoginErrorMessage = logInPage.loginErrorMessage();
		assertEquals("The email address or password you entered is invalid", LoginErrorMessage);
	}

	@Test
	public void tc12LoginWithIncorrectEmailAddressAndValidPassword() throws InterruptedException {
		logInPage.emailInputString(invalidEmail);
		logInPage.passwordInputString(validPassword);
		logInPage.submitSigninBtn();
		
		String EmailErrorMessage = logInPage.emailErrorMessage();
		assertEquals("The email field must be a valid email address.", EmailErrorMessage);
	}

	@Test
	public void tc13LoginWithInvalidEmailAddressAndValidPassword() throws InterruptedException {
		logInPage.emailInputString(wrongEmail);
		logInPage.passwordInputString(validPassword);
		logInPage.submitSigninBtn();
		
		String LoginErrorMessage = logInPage.loginErrorMessage();
		assertEquals("The email address or password you entered is invalid", LoginErrorMessage);
	}

	@Test
	public void tc14LoginWithValidEmailAddressAndValidPassword() throws InterruptedException {
		logInPage.emailInputString(validEmail);
		logInPage.passwordInputString(validPassword);
		logInPage.submitSigninBtn();
		
		String currentUrl = signUpPage.checkDashboardLink();
		
		assertEquals("https://automaticityacademy.ngrok.app/dashboard", currentUrl);
	}
	
}
