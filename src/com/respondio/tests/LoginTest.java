package com.respondio.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.respondio.pages.LoginPage;
import com.respondio.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64//chromedriver.exe"); 
        driver = new ChromeDriver();
        
		//Setting Webdriver Capabilities
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
        driver.get("https://app.respond.io/user/login");
        loginPage = new LoginPage(driver);

        extent = ExtentReportManager.getExtentReports();
    }

    @Test (priority = 2)
    public void testValidLogin() throws InterruptedException {
    	
    	test = ExtentReportManager.createTest("Valid Login Test");
        
        loginPage.setEmail("testprivate0307@gmail.com");
        test.info("Entered valid email");
        
        loginPage.setPassword("Rolus@112");
        test.info("Entered valid password");
        
        loginPage.clickLoginButton();
        test.info("Clicked login button");
        
        Thread.sleep(2000);
        
        // Add assertion to verify successful login
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        test.pass("Login successful with valid credentials.");
    }

    @Test (priority = 1)
    public void testInvalidLogin() throws InterruptedException {
        test = ExtentReportManager.createTest("Invalid Login Test");
    	
        loginPage.setEmail("invalid_email@test.com");
        test.info("Entered invalid email");
        
        loginPage.setPassword("invalid_password");
        test.info("Entered invalid password");
        
        loginPage.clickLoginButton();
        test.info("Clicked login button");
        
        Thread.sleep(4000);
        
        // Add assertion to verify login failure message
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        test.fail("Login failed with invalid credentials.");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
        ExtentReportManager.flushReport();
    }

    public WebDriver getDriver() {
        return driver;
	}
}
