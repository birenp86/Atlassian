package com.atlassian.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static com.atlassian.test.utils.RootTest.E;
import static com.atlassian.test.utils.RootTest.S;
import com.atlassian.test.utils.DFutil;

public class SetRestrictionPage extends DFutil {
	
	WebDriver driver;
	
	By confluenceLogo = By.cssSelector("#logo");
	By allUpdates = By.cssSelector(".nav-item-container-all-updates");
	By listFiles = By.cssSelector(".update-item-title>a");
	By clickMenu = By.cssSelector("#action-menu-link");
	By openRestrictions = By.cssSelector("#action-page-permissions-link");
	By openRestrictionOptions = By.cssSelector(".restrictions-dialog-option");
	By selectRestrictionsOptions = By.cssSelector("#page-restrictions-dialog-selector");
	By saveRestrictionsButton = By.cssSelector("#page-restrictions-dialog-save-button");
	By currentRestriction = By.cssSelector("#content-metadata-page-restrictions");
			
	
	
	public SetRestrictionPage(WebDriver driver){
		this.driver = driver;
	}

	public void clickConfluence() {
		//click confluence button on home page
		log.info(S);
		WebElement wel = driver.findElement(confluenceLogo);
		wel.click();
		log.info(E);
	}

	public void clickAllUpdates() {
		//click on All Updates and refresh
		log.info(S);
		WebElement wel = driver.findElement(allUpdates);
		wel.click();
		log.info(E);
	}

	public String selectFile(String createFile) {
		//select the file from list
		log.info(S);
		List<WebElement> wels = driver.findElements(listFiles);
		log.info(wels.size()+"");
		for(WebElement wel : wels) {
			if(wel.getText().equals(createFile)){
				log.info("true");
				driver.findElement(By.linkText(createFile)).click();
				
				log.info(E);
				break;
			}
		}
		return currentRestrictionTitle();
	}

	public void clickRestrictionMenu() {
		//open menu and select restriction from options
		log.info(S);
		WebElement wel = driver.findElement(clickMenu);
		wel.click();
		wel = driver.findElement(openRestrictions);
		wel.click();
		log.info(E);
	}

	public void selectRestriction(String restriction) {
		//select appropriate restrictions from menu options
		log.info(S);
		WebElement wel = driver.findElement(openRestrictionOptions);
		wel.click();
		Select dropdown = new Select(driver.findElement(selectRestrictionsOptions));
		dropdown.selectByValue(restriction.toLowerCase());
		saveRestrictionsSettings();
		log.info(E);
	}
	
	public String currentRestrictionTitle() {
		log.info(S);
		WebElement wel = driver.findElement(currentRestriction);
		log.info("Current Restriction Title: " + wel.getAttribute("title"));
		log.info(E);
		return wel.getAttribute("title");
	}

	private void saveRestrictionsSettings() {
		//apply and save changed restrictions
		log.info(E);
		WebElement wel = driver.findElement(saveRestrictionsButton);
		wel.click();	
	}
}
