package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private static final String URL_PAGE = "https://automaticityacademy.ngrok.app/login";
	
	//WebElements
	@FindBy(xpath="//input[@id='email']")
	private WebElement weEmailInput;
	@FindBy(xpath="//form//div[1]//div[1]//div[1]//p")
	private WebElement weEmailErrorMessage;

	@FindBy(xpath="//input[@id='password']")
	private WebElement wePasswordInput;
	@FindBy(xpath="//form//div[2]//div[1]//p")
	private WebElement wePasswordErrorMessage;

	@FindBy(xpath="//form//div[4]//p")
	private WebElement weLoginErrorMessage;
	@FindBy(xpath="//span[@class='p-button-label p-c']")
	private WebElement weSigninBtn;
	
	public LogInPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.driver.get(URL_PAGE);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		driver.get(URL_PAGE);
	}
	
	// EMAIL FIELD INPUT
	public void emailInputString(String emailInput) {
		weEmailInput.clear();
		weEmailInput.sendKeys(emailInput);
	}
	// EMAIL ERROR MESSAGE
	public String emailErrorMessage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weEmailErrorMessage));
		Thread.sleep(1000);
		return weEmailErrorMessage.getText();
	}
	
	// PASSWORD FIELD INPUT
	public void passwordInputString(String passwordInput) {
		wePasswordInput.clear();
		wePasswordInput.sendKeys(passwordInput);
	}
	// PASSWORD ERROR MESSAGE
	public String passwordErrorMessage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(wePasswordErrorMessage));
		Thread.sleep(1000);
		return wePasswordErrorMessage.getText();
	}

	// INVALID LOGIN ERROR MESSAGE
	public String loginErrorMessage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weLoginErrorMessage));
		Thread.sleep(1000);
		return weLoginErrorMessage.getText();
	}
	
	// SUBMIT SINGIN BUTTON
	public void submitSigninBtn() {
		weSigninBtn.click();
	}

}

