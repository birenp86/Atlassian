package com.atlassian.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.atlassian.test.utils.DFutil;
import static com.atlassian.test.utils.RootTest.E;
import static com.atlassian.test.utils.RootTest.S;

public class ConfluenceHomePage extends DFutil {

	WebDriver driver;

	By createPageButton = By.cssSelector("#quick-create-page-button");
	By toolBar = By.cssSelector(".aui-toolbar2-primary.toolbar-primary");
	By pageHeading = By.cssSelector("#content-title");
	By pageComment = By.cssSelector("#versionComment");
	By saveButton = By.cssSelector("#rte-button-publish");
	By titleText = By.cssSelector("#title-text>a");

	public ConfluenceHomePage(WebDriver driver){
		this.driver = driver;
	}

	public boolean createNewPage() {
		//click on Create button
		log.info(S);
		boolean clicked = findAndClick(driver, createPageButton);
		if(clicked) {
			WebElement wel = driver.findElement(toolBar);
			return wel.isDisplayed();
		}
		return false;
	}

	public void enterPageContent(String pageTitle) {
		//Enter PageTitle
		log.info(S);
		setPageTitle(pageTitle);
		log.info(E);
	}

	public void saveNewPage() {
		//Save new page
		log.info(S);
		findAndClick(driver, saveButton);
		log.info(E);
	}
	
	public String getPageTitle() {
		//returns pageTitle
		log.info(S);
		WebElement wel = driver.findElement(titleText);
		log.info(E);
		return wel.getText();
	}

	private void setPageTitle(String pageTitle) {
		//sets page title
		log.info(S);
		WebElement wel = driver.findElement(pageHeading);
		clearAndInput(wel, pageTitle);	
		wel.sendKeys(Keys.RETURN);
		log.info(E);
	}
}
