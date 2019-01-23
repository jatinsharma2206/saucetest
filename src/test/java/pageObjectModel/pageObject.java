package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class pageObject {
	WebDriver driver;

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//input[@value='LOGIN']")
	WebElement loginbutton;

	@FindBy(xpath = "//div[@class='inventory_list']//div[2]//div[3]//button[1]")
	WebElement BikeLight;

	@FindBy(xpath = "//div//div[@class='inventory_list']//div[5]//div[3]//button[1]")
	WebElement Onesie;

	@FindBy(xpath = "//*[contains(@class,'svg-inline--fa fa-shopping-cart fa-w-18 fa-3x')]")
	WebElement cart;

	@FindBy(xpath = "//div[contains(text(),'Sauce Labs Bike Light')]")
	WebElement BikeLight_In_Cart;

	@FindBy(xpath = "//div[contains(text(),'Sauce Labs Onesie')]")
	WebElement Onesie_In_Cart;

	public pageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterUserName(String User) {
		username.clear();
		username.sendKeys(User);
	}

	public void enterPassword(String pass) {
		password.clear();
		password.sendKeys(pass);
	}

	public void clickLogin() {
		loginbutton.click();
	}

	public void click_BikeLight() {
		BikeLight.click();
	}

	public void click_Onesie() {
		Onesie.click();
	}

	public void click_cartbutton() {
		cart.click();
	}

	public void validateBikeLigh() throws InterruptedException {
		try {
			Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]")));
			System.out.println("\nTest Case Passed: Sauce Labs Bike Light is pressent in cart");
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println("\nTest Case Failed: Sauce Labs Bike Light is not added to cart");
		}
	}

	public void validateOnesie() throws InterruptedException {
		try {
			Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Sauce Labs Onesie')]")));
			System.out.println("Test Case Passed: Sauce Labs Onesie is pressent in cart\n");
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println("Test Case Failed: Sauce Labs Onesie is not added to cart\n");
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
