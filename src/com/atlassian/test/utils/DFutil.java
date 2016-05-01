package com.atlassian.test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.atlassian.test.utils.RootTest.E;
import static com.atlassian.test.utils.RootTest.S;

public class DFutil {
	public static Logger log = (Logger) LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	public Env env = Env.instance();

	public void clearAndInput(WebElement wel, String inputString) {
		log.info(S);
		wel.clear();
		wel.sendKeys(inputString);
		log.info(E);
	}

	public boolean findAndClick(WebDriver driver, By webElement) {
		log.info(S);
		WebElement wel = driver.findElement(webElement);
		if(wel.isDisplayed()) {
			wel.click();
			log.info(E + "- True");
			return true;
		}
		log.info(E + "- False");
		return false;
	}
}
