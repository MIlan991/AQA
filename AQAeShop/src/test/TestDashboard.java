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

import webpages.CheckoutPage;
import webpages.DashboardPage;
import webpages.LogInPage;
import webpages.SignUpPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDashboard {
	
	private static ChromeDriver driver;
	private static DashboardPage dashboardPage;
	private static LogInPage logInPage;
	private static SignUpPage signUpPage;
	private static CheckoutPage checkoutPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/chile91/Desktop/webdriver/chromedriver");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));

		dashboardPage = new DashboardPage(driver, wait);
		logInPage = new LogInPage(driver, wait);
		signUpPage = new SignUpPage(driver, wait);
		checkoutPage = new CheckoutPage(driver, wait);

		logInPage.openPage();
		dashboardPage.loginToDashboard();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc03AddOneItemToTheCart() throws InterruptedException {
		System.out.println("TC 03");
		int numberOfItemsInCart = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc03 Before: " + numberOfItemsInCart);
		dashboardPage.addItemToTheCart(2);
		
		// POPUP MESSAGE CHECK
		String PopupMessageSuccess = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupMessageSuccess);
		
		// Inciement of number of items in cart
		String NoOfItemsDisplayed = dashboardPage.noOfItemsInCartText();
		String numberOfItemsInCartString = String.valueOf(numberOfItemsInCart + 1);
		
		System.out.println("tc03 Displayed: " + NoOfItemsDisplayed);
		System.out.println("tc03 Actual Number: " + numberOfItemsInCartString);
		assertEquals(NoOfItemsDisplayed, numberOfItemsInCartString);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}

	@Test
	public void tc04DeleteItemFromTheCart() throws InterruptedException {
		System.out.println("TC 04");
		int numberOfItemsInCart = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc04 New Page: " + numberOfItemsInCart);
		
		// OPEN SIDEBAR
		dashboardPage.clickShopNavbarButton();
		
		//DELETE ITEM
		dashboardPage.manipulateItemInCart(1, "delete");
		int numberOfItemsInCartDivAfterFirstDelete = dashboardPage.countItemsInCart();
		assertEquals(0, numberOfItemsInCartDivAfterFirstDelete);
		
		String EmptyCartMessage = dashboardPage.emptyCartText();
		assertEquals("No items in cart. Add some!", EmptyCartMessage);
		System.out.println("tc04 Cart Message: " + EmptyCartMessage);
		dashboardPage.clickShopNavbarButton();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}

	@Test
	public void tc05AddTwoItemsToTheCartThenDeleteThemOneByOne() throws InterruptedException {
		System.out.println("TC 05");
		int numberOfItemsInCart = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc05 Before: " + numberOfItemsInCart);
		
		// ADD FIRST ITEM TO THE CART
		dashboardPage.addItemToTheCart(1);
		
		// POPUP MESSAGE CHECK
		String PopupMessageSuccess = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupMessageSuccess);
		
		int numberOfItemsInCart1 = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc05 First add: " + numberOfItemsInCart1);
		
		// ADD SECOND ITEM TO THE CART
		dashboardPage.addItemToTheCart(3);
		
		// POPUP SECOND MESSAGE CHECK
		String PopupSecondMessageSuccess = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupSecondMessageSuccess);
		
		int numberOfItemsInCart2 = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc05 Second add: " + numberOfItemsInCart2);

		// OPEN SIDEBAR
		dashboardPage.clickShopNavbarButton();
		
		// ADDED ITEMS CHECK
		int numberOfItemsInCartDiv = dashboardPage.countItemsInCart();
		assertEquals(2, numberOfItemsInCartDiv);
		System.out.println("tc05 Divs: " + numberOfItemsInCartDiv);

		//CLEAR ITEMS FROM CART
		dashboardPage.clickClearItemsFromCartButton();
		
		// EMPTY CART CHECK
		int numberOfItemsInCartDivClear = dashboardPage.countItemsInCart();
		assertEquals(0, numberOfItemsInCartDivClear);
		String EmptyCartMessage = dashboardPage.emptyCartText();
		assertEquals("No items in cart. Add some!", EmptyCartMessage);

		// CLOSE SIDEBAR
		dashboardPage.clickShopNavbarButton();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}
	
	@Test
	public void tc06AddFourItemsToTheCart() throws InterruptedException {
		System.out.println("TC 06");
		int numberOfItemsInCart = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc06 Before: " + numberOfItemsInCart);
		
		// ADD FIRS ITEM FIRS TIME
		dashboardPage.addItemToTheCart(3);
		// POPUP MESSAGE CHECK no1
		String PopupMessageSuccess1 = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupMessageSuccess1);

		int numberOfItemsInCart1Add = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc06 Add 1 : " + numberOfItemsInCart1Add);
		
		// ADD FIRST ITEM SECOND TIME
		dashboardPage.addItemToTheCart(3);
		// POPUP MESSAGE CHECK no2
		String PopupMessageSuccess2 = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupMessageSuccess2);
		
		int numberOfItemsInCart2Add = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc06 Add 2 : " + numberOfItemsInCart2Add);
		
		// ADD Third ITEM TO THE CART
		dashboardPage.addItemToTheCart(4);
		// POPUP MESSAGE CHECK no3
		String PopupSecondMessageSucces3 = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupSecondMessageSucces3);
		
		int numberOfItemsInCart3Add = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc06 Add 3 : " + numberOfItemsInCart3Add);
		
		// ADD Fourth ITEM TO THE CART
		dashboardPage.addItemToTheCart(2);
		// POPUP MESSAGE CHECK no4
		String PopupMessageSuccess4 = dashboardPage.popupSuccessText();
		assertEquals("Product added successfully!", PopupMessageSuccess4);

		int numberOfItemsInCart4Add = Integer.parseInt(dashboardPage.noOfItemsInCartText());
		System.out.println("tc06 Add 4 : " + numberOfItemsInCart4Add);
		
		dashboardPage.clickShopNavbarButton();
		
		int numberOfItemsInCartDiv = dashboardPage.countItemsInCart();
		assertEquals(3, numberOfItemsInCartDiv);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}

	@Test
	public void tc07ItemMaintenanceInCartAndSubmitCheckout() throws InterruptedException {
		System.out.println("TC 07");

		dashboardPage.manipulateItemInCart(3, "plus");
		dashboardPage.manipulateItemInCart(1, "minus");
		dashboardPage.manipulateItemInCart(2, "minus");
		int numberOfItemsInCartDiv2 = dashboardPage.countItemsInCart();
		assertEquals(2, numberOfItemsInCartDiv2);
		
		System.out.println("tc06 Divs: " + numberOfItemsInCartDiv2);
		
		dashboardPage.clickCheckoutButton();
		
		String currentUrl = signUpPage.checkDashboardLink();
		
		assertEquals("https://automaticityacademy.ngrok.app/checkout", currentUrl);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}

	@Test
	public void tc08FinalizePurchese() throws InterruptedException {
		System.out.println("TC 08");
		checkoutPage.clickNextpage1Button();
		checkoutPage.clickNextpage2Button();
		checkoutPage.clickNextpage3Button();
		
		checkoutPage.clickPlaceYourOrderButton();
		
		String currentUrl = signUpPage.checkDashboardLink();
		
		assertEquals("https://automaticityacademy.ngrok.app/dashboard", currentUrl);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}

	@Test
	public void tc09Logout() throws InterruptedException {
		System.out.println("TC 09");
		dashboardPage.clickHamburgerButton();
		dashboardPage.clickLogoutButton();
		
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals("https://automaticityacademy.ngrok.app/", currentUrl);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}
	
}
