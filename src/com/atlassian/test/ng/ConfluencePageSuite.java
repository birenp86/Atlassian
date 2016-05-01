package com.atlassian.test.ng;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.atlassian.test.pages.ConfluenceHomePage;
import com.atlassian.test.pages.LoginPage;
import com.atlassian.test.pages.SetRestrictionPage;
import com.atlassian.test.utils.Env;
import com.atlassian.test.utils.RootTest;

public class ConfluencePageSuite extends RootTest	{

	String user1 = Env.user1;
	String userKey1 = Env.user1key;
	String userName1 = Env.username1;
	String createdFile = null;

	@BeforeClass
	public void setEnv() {
		try {
			//set Environment and login based on properties file
			log.info(S);
			driver = super.setTestEnv();
			driver.get(HTTPS + Env.bouncerFrontEnd);
			driver.manage().window().maximize();
			LoginPage loginPage = new LoginPage(driver);
			loginPage.userLogin(user1, userKey1);
			Boolean loggedUserIn = loginPage.verifyLogin();
			Assert.assertTrue(loggedUserIn);
			log.info(E);
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
			log.info(F);
			Assert.fail(e.getLocalizedMessage() + "\n" + e.getStackTrace());
		}
	}

	@Parameters({"pageTitle"})
	@Test(enabled = true)
	public void testCreateNewPage(@Optional("Atlassian Test Page Default Title") String pageTitle) {
		try {
			// create new page
			log.info(S);
			ConfluenceHomePage homePage = new ConfluenceHomePage(driver);
			boolean goAhead = homePage.createNewPage();
			Assert.assertTrue(goAhead);
			createdFile = pageTitle  + " " + System.currentTimeMillis();
			homePage.enterPageContent(createdFile);
			homePage.saveNewPage();
			String expectedTitle = homePage.getPageTitle();
			Assert.assertEquals(createdFile, expectedTitle);
			Thread.sleep(5000);
			log.info(E);
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
			log.info(F);
			Assert.fail(e.getLocalizedMessage() + "\n" + e.getStackTrace());
		}
	}

	@Parameters({"restriction"})
	@Test (dependsOnMethods = "testCreateNewPage")
	public void testSetRestrictions(@Optional("viewEdit") String restriction) {
		try {
			//set restrictions based on parameters
			log.info(S);
			SetRestrictionPage restPage = new SetRestrictionPage(driver);
			//refresh the page to get latest instance
			driver.navigate().refresh();
			restPage.clickConfluence();
			Thread.sleep(2000);
			restPage.clickAllUpdates();
			Thread.sleep(1000);
			restPage.selectFile(createdFile);
			restPage.clickRestrictionMenu();
			restPage.selectRestriction(restriction);

			log.info(E);
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
			log.info(F);
			Assert.fail(e.getLocalizedMessage() + "\n" + e.getStackTrace());
		}
	}
}
