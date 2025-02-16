package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	//private static final String URL_PAGE = "https://automaticityacademy.ngrok.app/checkout";
	
	//WebElements
	@FindBy(xpath="//button[@class='bg-gray-200 text-primary hover:bg-green-500 hover:border-green-500 hover:text-white text-xl mt-4 text-semibold mr-4 ml-4 border border-indigo-700 rounded p-button p-component']")
	private WebElement weFirstNextstepButton;
	@FindBy(xpath="//button[@class='bg-gray-200 text-primary hover:bg-green-500 hover:border-green-500 hover:text-white text-lg text-semibold border border-indigo-700 rounded mt-6 px-4 p-button p-component']")
	private WebElement weSecondNextstepButton;
	@FindBy(xpath="//button[@class='bg-gray-200 text-primary hover:bg-green-500 hover:border-green-500 hover:text-white text-lg text-semibold border border-indigo-700 rounded mt-6 px-4 p-button p-component']")
	private WebElement weThirdNextstepButton;
	@FindBy(xpath="//button[@class='bg-white text-primary hover:text-white hover:bg-green-500 hover:border-green-500 text-lg mt-2 text-semibold px-2 py-2 border border-indigo-700 rounded p-button p-component']")
	private WebElement wePlaceYourOrderButton;
	
	
	public CheckoutPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		//this.driver.get(URL_PAGE);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void clickNextpage1Button() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weFirstNextstepButton));
		Thread.sleep(1000);
		weFirstNextstepButton.click();
	}
	
	public void clickNextpage2Button() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weSecondNextstepButton));
		Thread.sleep(1000);
		weSecondNextstepButton.click();
	}
	
	public void clickNextpage3Button() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weThirdNextstepButton));
		Thread.sleep(1000);
		weThirdNextstepButton.click();
	}
	
	public void clickPlaceYourOrderButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(wePlaceYourOrderButton));
		Thread.sleep(1000);
		wePlaceYourOrderButton.click();
	}

}
