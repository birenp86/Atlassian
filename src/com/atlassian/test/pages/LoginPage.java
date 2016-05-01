package com.atlassian.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.atlassian.test.utils.DFutil;
import static com.atlassian.test.utils.RootTest.E;
import static com.atlassian.test.utils.RootTest.S;

public class LoginPage extends DFutil{

	WebDriver driver;

	By userName = By.cssSelector("#username");
	By password = By.cssSelector("#password");
	By loginButton = By.cssSelector("#login");
	By toolBar = By.cssSelector(".aui-header");

	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	public void userLogin(String user, String userKey) throws InterruptedException {
		//login flow
		log.info(S);
		setUsername(user);
		setPassword(userKey);
		clickLogin();
		log.info(E	);
	}

	private void setUsername(String user) {
		//set user name
		log.info(S);
		WebElement wel = driver.findElement(userName);
		clearAndInput(wel, user);
		log.info(E);
	}

	private void setPassword(String userKey) {
		//set password
		log.info(S);
		WebElement wel = driver.findElement(password);
		clearAndInput(wel, userKey);
		log.info(E);
	}
	
	public Boolean verifyLogin() {
		// verify login, check toolbar
		WebElement wel = driver.findElement(toolBar);
		return wel.isDisplayed();
	}

	private void clickLogin() throws InterruptedException {
		//click login button
		WebElement wel = driver.findElement(loginButton);
		wel.click();
		//wait for successful login
		Thread.sleep(3000);
		log.info(E);
	}
}
