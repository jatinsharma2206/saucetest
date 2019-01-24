package com.saucedemo.saucetest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjectModel.pageObject;;

public class SauceDemo_Test {
	private WebDriver driver;
	Properties prop = new Properties();
	pageObject saucedemo;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {
		
		//Chrome driver is setup as per mac os. This Should be corrected for windows uncomment below line for windows.
		//System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "./chromedriver");
		driver = new ChromeDriver();
		// Using env.properties file to parameterize ProjectUrl to avoid hard
		// coding values.
		FileInputStream fis = new FileInputStream("./env.properties");
		prop.load(fis);
		saucedemo = new pageObject(driver);
	}

	// Using dataProvider to test different sets of data. Excel utility can also
	// be used for this.
	@Test(dataProvider = "loginData")
	public void saucedemoTesting(String username, String password) throws InterruptedException {

		driver.get(prop.getProperty("ProjectUrl"));
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println("Testing for User : " + username);

		// Using Page Object Model so that the test looks cleaner. This helps in
		// enhancing test maintenance and reducing code duplication.
		// If the UI elements changes, We just need to make change in pageObject
		// class for this test.

		saucedemo.enterUserName(username);
		saucedemo.enterPassword(password);
		saucedemo.clickLogin();
		Thread.sleep(500);

		saucedemo.click_BikeLight();
		saucedemo.click_Onesie();
		saucedemo.click_cartbutton();
		Thread.sleep(500);

		saucedemo.validateBikeLigh();
		saucedemo.validateOnesie();
		Thread.sleep(500);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

	// More data set can be added in DataProvider for validating more test
	// cases.

	@DataProvider(name = "loginData")
	public Object[][] loginData1() {
		Object[][] login_Data = new Object[][] { { "standard_user", "secret_sauce" } };
		return login_Data;

	}

}
