package com.respondio.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.respondio.pages.AddUserPage;
import com.respondio.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class AddUserTest {
    WebDriver driver;
    AddUserPage addUserPage;
    ExtentReports extent;
    ExtentTest test;
    LoginTest loginTest = new LoginTest();
    
    
    @BeforeMethod
    public void setup() throws InterruptedException {

        // Set up the Chrome WebDriver
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64//chromedriver.exe"); 

        // Initialize LoginTest and perform login
        loginTest = new LoginTest();
        
        // Sets up the WebDriver and opens the login page
        loginTest.setup();  
        
        // Performs the login operation
        loginTest.testValidLogin();  
        
        // Retrieve the WebDriver instance from LoginTest
        driver = loginTest.getDriver();  
        
        // Initialize AddUserPage with the logged-in driver
        addUserPage = new AddUserPage(driver);

        // Navigate to the user profile page
        driver.navigate().to("https://app.respond.io/space/241235/settings/users");
        
        Thread.sleep(4000);

        // Initialize the extent reports
        extent = ExtentReportManager.getExtentReports();
    }

    @Test (priority = 3)
    public void testAddUser() throws InterruptedException {
    
        test = ExtentReportManager.createTest("Add User Test");
        
        addUserPage.clickAddUserButton();
        test.info("Clicked on Add User button");
        
        addUserPage.setEmail("new_user@test.com");
        test.info("Entered email for new user");
        
//      addUserPage.selectAccessLevel("Manager");
        test.info("Selected access level as Manager by default");
        
        addUserPage.clickAddButton();
        test.info("Clicked on Add button to submit new user");
        
        Thread.sleep(2000);
        
        // Assertions and ExtentReports logging
        boolean isUserAdded = addUserPage.confirmNewUser();
        AssertJUnit.assertTrue(isUserAdded);
        test.pass("New user added successfully");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
        ExtentReportManager.flushReport();
    }
}
