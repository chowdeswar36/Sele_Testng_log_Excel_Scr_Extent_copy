package com.cg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver driver) {
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "txtUsername")
	WebElement txt_username;

	@FindBy(name = "txtPassword")
	WebElement txt_password;

	@FindBy(id = "btnLogin")
	WebElement btn_Login;

	public void setUserName(String username) {
		txt_username.sendKeys(username);
	}

	public void setPassword(String password) {
		txt_password.sendKeys(password);
	}

	public void clickLogin() {
		btn_Login.click();
	}
}
