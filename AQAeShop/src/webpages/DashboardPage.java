package webpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private static final String URL_PAGE = "https://automaticityacademy.ngrok.app/login";
	
	//WebElements
	@FindBy(xpath="//input[@id='email']")
	private WebElement weEmailInput;
	@FindBy(xpath="//input[@id='password']")
	private WebElement wePasswordInput;
	@FindBy(xpath="//span[@class='p-button-label p-c']")
	private WebElement weSigninBtn;

	@FindBy(xpath="//button[@class='inline-flex items-center py-2 border border-transparent text-sm leading-4 font-medium rounded-md text-primary hover:text-gray-600 focus:outline-none transition ease-in-out duration-150 p-button p-component']")
	private WebElement weShopNavBtn;
	@FindBy(xpath="//button[@class='border-2 border-gray-200 bg-indigo-400 text-white overflow-visible -right-0 -top-0 w-fit px-2 h-fit shadow-none hover:bg-white hover:text-indigo-300 p-button p-component']")
	private WebElement weClearItemsFromCartBtn;

	@FindBy(xpath="//div[@class='justify-end mr-4 z-0 products-container']")
	private WebElement weItemsDiv;

	
	@FindBy(xpath="//div[@class='Toastify__toast-body']/div[.='Product added successfully!']")
	private WebElement wePopupAddSuccess;
	@FindBy(xpath="//div[@class='p-tooltip-text']")
	private WebElement wePopupOutOfStock;
	@FindBy(xpath="//div[@class='z-10 text-3xl font-semibold sm:mt-12 md:mt-12 lg:mt-16']")
	private WebElement weEmptyCartMessage;

	@FindBy(xpath="//button[@class='bg-gray-200 text-primary hover:text-white hover:bg-green-500 hover:border-green-500 text-lg mb-8 mt-2 text-semibold px-8 border border-indigo-700 rounded p-button p-component']")
	private WebElement weCheckoutBtn;

	@FindBy(xpath="//button[@class='inline-flex items-center px-3 py-2 border border-transparent text-sm leading-4 font-medium rounded-md text-primary hover:text-gray-600 focus:outline-none transition ease-in-out duration-150']")
	private WebElement weHamburgerBtn;
	@FindBy(xpath="//button[@class='block w-full px-4 py-2 text-start text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:outline-none focus:bg-gray-100 transition duration-150 ease-in-out']")
	private WebElement weLogoutBtn;
	
	public DashboardPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.driver.get(URL_PAGE);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void loginToDashboard() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weSigninBtn));
		Thread.sleep(1000);
		
		weEmailInput.sendKeys("userqa91@gmail.com");
		wePasswordInput.sendKeys("qatester123");
		weSigninBtn.click();
		wait.until(ExpectedConditions.visibilityOf(weItemsDiv));
	}

	public void addItemToTheCart(int rowNumber) throws InterruptedException {
	    wait.until(ExpectedConditions.visibilityOf(weItemsDiv));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weItemsDiv);
	    Thread.sleep(1000); // wait to scroll

	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'p-button')]")));

	    String xpath = "(//div[@class='justify-end mr-4 z-0 products-container']//button[not(contains(@class, 'p-disabled'))])[" + rowNumber + "]";
	    WebElement weIconBuyItemBtn = driver.findElement(By.xpath(xpath));

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//svg"))); 
	    Thread.sleep(2000); // wait to scroll

	    wait.until(ExpectedConditions.visibilityOf(weIconBuyItemBtn));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weIconBuyItemBtn);
	    wait.until(ExpectedConditions.elementToBeClickable(weIconBuyItemBtn));
	    Thread.sleep(1000); // wait to click
	    weIconBuyItemBtn.click();
	}

	public String popupSuccessText() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(wePopupAddSuccess));
		String successPopup = wePopupAddSuccess.getText();
		System.out.println(successPopup);
		wait.until(ExpectedConditions.invisibilityOf(wePopupAddSuccess));		
		return successPopup;
	}
	
	public String noOfItemsInCartText() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weShopNavBtn));
		Thread.sleep(1000);
		return weShopNavBtn.getText();
	}
	
	public void clickShopNavbarButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weShopNavBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weShopNavBtn);
		Thread.sleep(1000);
		weShopNavBtn.click();
		Thread.sleep(5000);
	}
	
	public void clickClearItemsFromCartButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weClearItemsFromCartBtn));
		Thread.sleep(1000);
		weClearItemsFromCartBtn.click();
		wait.until(ExpectedConditions.visibilityOf(weEmptyCartMessage));
	}

	
	public String emptyCartText() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weEmptyCartMessage));
		Thread.sleep(1000);
		return weEmptyCartMessage.getText();
	}

    public int countItemsInCart() {
        List<WebElement> items = driver.findElements(By.xpath("//section/div[@class='flex align-middle w-full max-w-lg']"));
        return items.size();
    }
    
    public void manipulateItemInCart(int itemRow, String Operation) throws InterruptedException {
    	int opp = 0;

        if(Operation.equals("plus")) {
            opp = 3;
        } else if(Operation.equals("minus")) {
            opp = 2;
        } else if(Operation.equals("delete")) {
            opp = 1;
        }
        
		WebElement weAddItemBtn = driver.findElement(By.xpath("//section[@class='flex-1 overflow-y-auto px-2 py-2']//div["+ itemRow +"]//div[1]//button["+ opp +"]"));
		wait.until(ExpectedConditions.visibilityOf(weAddItemBtn));
		Thread.sleep(2000);
		weAddItemBtn.click();
		Thread.sleep(3000);
    }
	
	public void clickCheckoutButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weCheckoutBtn));
		Thread.sleep(1000);
		weCheckoutBtn.click();
	}
	
	public void clickHamburgerButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weHamburgerBtn));
		Thread.sleep(1000);
		weHamburgerBtn.click();
	}
	
	public void clickLogoutButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(weLogoutBtn));
		Thread.sleep(1000);
		weLogoutBtn.click();
	}

}
