package webpages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private static final String URL_PAGE = "https://automaticityacademy.ngrok.app/register";
	
	//WebElements
	@FindBy(xpath="//button[@class='bg-gray-200 text-primary hover:bg-green-500 hover:border-green-500 hover:text-white mr-2 py-2 sm:w-12 px-4 border border-indigo-700 rounded p-button p-component']")
	private WebElement weSignUpButton;
	@FindBy(xpath="//div[.='Deals']")
	private WebElement weNavbarDashboardDeals;
	
	@FindBy(xpath="//input[@id='username']")
	private WebElement weUsernameInput;
	@FindBy(xpath="//div//p[.='The username field is required.']")
	private WebElement weUsernameError;
	@FindBy(xpath="//p[.='The username has already been taken.']")
	private WebElement weTakenUsernameError;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement weEmailInput;
	@FindBy(xpath="//div//p[.='The email field is required.']")
	private WebElement weEmailError;
	@FindBy(xpath="//p[.='The email field format is invalid.']")
	private WebElement weInvalidEmailError;
	@FindBy(xpath="//p[.='The email has already been taken.']")
	private WebElement weTakenEmailError;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement wePasswordInput;
	@FindBy(xpath="//div//p[.='The password field is required.']")
	private WebElement wePasswordError;
	
	@FindBy(xpath="//button[@class='w-full bg-gray-200 text-primary hover:bg-green-500 hover:border-green-500 hover:text-white text-xl mr-2 text-semibold py-2 px-4 border border-indigo-700 rounded p-button p-component']")
	private WebElement weRegisterButton;
	
	public SignUpPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.driver.get(URL_PAGE);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		driver.get(URL_PAGE);
	}
	
	// USERNAME FIELD INPUT
	public void usernameInputString(String inputUsername) {
		weUsernameInput.clear();
		weUsernameInput.sendKeys(inputUsername);
	}
	//NEW RANDOM USERNAME INPUT
	public void newRandomUsernameInput() {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
		weUsernameInput.clear();
		weUsernameInput.sendKeys("NewUserQA" + randomNumber);
	}
	//USERNAME REQUIRED ERROR MESSAGE
	public String getUsernameInputError() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weUsernameError));
		Thread.sleep(1000);
		return weUsernameError.getText();
	}
	//USERNAME TAKEN ERROR MESSAGE
	public String getTakenUsernameInputError() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weTakenUsernameError));
		Thread.sleep(1000);
		return weTakenUsernameError.getText();
	}
	
	// EMAIL FIELD INPUT
	public void emailInputString(String inputEmail) {
		weEmailInput.clear();
		weEmailInput.sendKeys(inputEmail);
	}
	//NEW RANDOM EMAIL INPUT
	public void newRandomEmailInput() {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
		weEmailInput.clear();
		weEmailInput.sendKeys(randomNumber + "newemail@gmail.com");
	}
	// EMAIL REQUIRED INPUT ERROR
	public String getEmailInputError() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weEmailError));
		Thread.sleep(1000);
		return weEmailError.getText();
	}
	// EMAIL INVALID INPUT ERROR
	public String getInvalidEmailInputError() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weInvalidEmailError));
		Thread.sleep(1000);
		return weInvalidEmailError.getText();
	}
	// EMAIL TAKEN INPUT ERROR
	public String getTakenEmailInputError() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weTakenEmailError));
		Thread.sleep(1000);
		return weTakenEmailError.getText();
	}
	
	// PASSWORD FIELD INPUT
	public void passwordInputString(String inputEmail) {
		wePasswordInput.clear();
		wePasswordInput.sendKeys(inputEmail);
	}
	// PASSWORD REQUIRED FIELD INPUT
	public String getPasswordInputError() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(wePasswordError));
		Thread.sleep(1000);
		return wePasswordError.getText();
	}
	// SUBMIT REGISTER BUTTON
	public void submitRegisterBtn() {
		weRegisterButton.click();
	}
	
	// LINK CHECK
	public String checkDashboardLink() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weNavbarDashboardDeals));
		Thread.sleep(5000);
		
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
}
