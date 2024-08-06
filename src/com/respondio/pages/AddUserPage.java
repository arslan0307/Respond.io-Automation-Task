package com.respondio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage {
    WebDriver driver;
    
    @FindBy(css = "div[class='dls-shrink dls-me-12'] button[type='button']")
    WebElement addUserButton;

    @FindBy(xpath = "//input[@placeholder='Email Address']")
    WebElement emailField;

    @FindBy(xpath = "//div[@role='combobox']")
    WebElement accessLevelDropdown;

    @FindBy(xpath = "//span[(text()='Add')]")
    WebElement addButton;
    
    @FindBy(xpath = "//div[text()='Manager - new_user@test.com']")
    WebElement confirmNewUser;
    
    
    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddUserButton() {
        addUserButton.click();
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public void selectAccessLevel(String accessLevel) {
        accessLevelDropdown.sendKeys(accessLevel);
    }

    public void clickAddButton() {
        addButton.click();
    }
    
    public boolean confirmNewUser() {
    	confirmNewUser.isDisplayed();
    	return true;
    }
}
