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

import webpages.SignUpPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSignUp {
	
	private static ChromeDriver driver;
	private static SignUpPage signUpPage;
	
	String validUsername = "UserQA";
	String validEmail = "userqa91@gmail.com";
	String invalidEmail = "invalidEmail";
	String validPassword = "qatester123";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/chile91/Desktop/webdriver/chromedriver");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		signUpPage = new SignUpPage(driver, wait);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		signUpPage.openPage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc02SignupWithEmptyUsernameEmailAndPasswordInput() throws InterruptedException {
		signUpPage.usernameInputString("");
		signUpPage.emailInputString("");
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getUsernameInputError();
		assertEquals("The username field is required.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getEmailInputError();
		assertEquals("The email field is required.", EmailErrorMessage);
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc03SignupWithValidUsernameAndEmptyEmailAndPasswordInput() throws InterruptedException {
		signUpPage.usernameInputString(validUsername);
		signUpPage.emailInputString("");
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();

		
		String UsernameErrorMessage = signUpPage.getTakenUsernameInputError();
		assertEquals("The username has already been taken.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getEmailInputError();
		assertEquals("The email field is required.", EmailErrorMessage);
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc04SignupWithTakenValidEmailAndEmptyUsernameAndPasswordInput() throws InterruptedException {
		signUpPage.usernameInputString("");
		signUpPage.emailInputString(validEmail);
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getUsernameInputError();
		assertEquals("The username field is required.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getTakenEmailInputError();
		assertEquals("The email has already been taken.", EmailErrorMessage);
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc05SignupWithValidPasswordAndEmptyUsernameAndEmailInput() throws InterruptedException {
		signUpPage.usernameInputString("");
		signUpPage.emailInputString("");
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getUsernameInputError();
		assertEquals("The username field is required.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getEmailInputError();
		assertEquals("The email field is required.", EmailErrorMessage);
	}

	@Test
	public void tc06SignupWithValidUsernameAndEmailInputAndEmptyPasswordInput() throws InterruptedException {
		signUpPage.usernameInputString(validUsername);
		signUpPage.emailInputString(validEmail);
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getTakenUsernameInputError();
		assertEquals("The username has already been taken.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getTakenEmailInputError();
		assertEquals("The email has already been taken.", EmailErrorMessage);
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}

	@Test
	public void tc07SignupWithValidUsernameAndPasswordInputAndEmptyEmailInput() throws InterruptedException {
		signUpPage.usernameInputString(validUsername);
		signUpPage.emailInputString("");
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getTakenUsernameInputError();
		assertEquals("The username has already been taken.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getEmailInputError();
		assertEquals("The email field is required.", EmailErrorMessage);
	}

	@Test
	public void tc08SignupWithValidEmailAndPasswordInputAndEmptyUsernameInput() throws InterruptedException {
		signUpPage.usernameInputString("");
		signUpPage.emailInputString(validEmail);
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getUsernameInputError();
		assertEquals("The username field is required.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getTakenEmailInputError();
		assertEquals("The email has already been taken.", EmailErrorMessage);
	}

	@Test
	public void tc09SignupWithValidUsernameAndPasswordAndInvalidEmail() throws InterruptedException {
		signUpPage.usernameInputString(validUsername);
		signUpPage.emailInputString(invalidEmail);
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getTakenUsernameInputError();
		assertEquals("The username has already been taken.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getInvalidEmailInputError();
		assertEquals("The email field format is invalid.", EmailErrorMessage);
	}
	
	@Test
	public void tc10SignupWithNewUsernameAndEmptyEmailAndPasswordInput() throws InterruptedException {
		signUpPage.newRandomUsernameInput();
		signUpPage.emailInputString("");
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();
		
		String EmailErrorMessage = signUpPage.getEmailInputError();
		assertEquals("The email field is required.", EmailErrorMessage);
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}
	
	@Test
	public void tc11SignupWithNewEmailAndEmptyUsernameAndPasswordInput() throws InterruptedException {
		signUpPage.usernameInputString("");
		signUpPage.newRandomEmailInput();
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getUsernameInputError();
		assertEquals("The username field is required.", UsernameErrorMessage);
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}
	
	@Test
	public void tc12SignupWithNewUsernameAndPasswordLeaveEmailInputEmpty() throws InterruptedException {
		signUpPage.newRandomUsernameInput();
		signUpPage.emailInputString("");
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String EmailErrorMessage = signUpPage.getEmailInputError();
		assertEquals("The email field is required.", EmailErrorMessage);
	}
	
	@Test
	public void tc13SignupWithNewEmailAndPasswordLeaveUsernameInputEmpty() throws InterruptedException {
		signUpPage.usernameInputString("");
		signUpPage.newRandomEmailInput();
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getUsernameInputError();
		assertEquals("The username field is required.", UsernameErrorMessage);
	}
	
	@Test
	public void tc14SignupWithNewEmailAndPasswordLeaveUsernameInputEmpty() throws InterruptedException {
		signUpPage.newRandomUsernameInput();
		signUpPage.newRandomEmailInput();
		signUpPage.passwordInputString("");
		signUpPage.submitRegisterBtn();
		
		String PasswordErrorMessage = signUpPage.getPasswordInputError();
		assertEquals("The password field is required.", PasswordErrorMessage);
	}
	
	@Test
	public void tc15SignupWithExistingAccount() throws InterruptedException {
		signUpPage.usernameInputString(validUsername);
		signUpPage.emailInputString(validEmail);
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String UsernameErrorMessage = signUpPage.getTakenUsernameInputError();
		assertEquals("The username has already been taken.", UsernameErrorMessage);
		
		String EmailErrorMessage = signUpPage.getTakenEmailInputError();
		assertEquals("The email has already been taken.", EmailErrorMessage);
	}
	
	@Test
	public void tc16SignupWithNewValidUsernameEmailAndPassword() throws InterruptedException {
		signUpPage.newRandomUsernameInput();
		signUpPage.newRandomEmailInput();
		signUpPage.passwordInputString(validPassword);
		signUpPage.submitRegisterBtn();
		
		String currentUrl = signUpPage.checkDashboardLink();
		
		assertEquals("https://automaticityacademy.ngrok.app/dashboard", currentUrl);
	}
	



}
